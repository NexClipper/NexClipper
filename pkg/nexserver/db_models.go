package nexserver

import (
	"github.com/jinzhu/gorm"
	"github.com/jinzhu/gorm/dialects/postgres"
	"time"
)

type Cluster struct {
	gorm.Model

	Name        string `gorm:"size:128"`
	Description string
	Disabled    bool

	Agents []Agent
	Nodes  []Node
}

type Agent struct {
	gorm.Model

	Online     bool
	Version    string `gorm:"size:32"`
	Ipv4       string `gorm:"size:16"`
	Ipv6       string `gorm:"size:40"`
	PublicIpv4 string `gorm:"size:16"`
	PublicIpv6 string `gorm:"size:40"`

	LastContact time.Time
	Disabled    bool
	Uuid        string `gorm:"size:36;unique_index"`
	MachineID   string `gorm:"size:70;unique_index"`
	Description string

	ClusterID uint `gorm:"index"`
	Node      Node
}

type Node struct {
	gorm.Model

	Host            string `gorm:"size:128"`
	Ipv4            string `gorm:"size:16"`
	Ipv6            string `gorm:"size:40"`
	PublicIpv4      string `gorm:"size:16"`
	PublicIpv6      string `gorm:"size:40"`
	Os              string `gorm:"size:64"`
	Platform        string `gorm:"size:64"`
	PlatformFamily  string `gorm:"size:64"`
	PlatformVersion string `gorm:"size:64"`
	Info            postgres.Jsonb
	Uuid            string `gorm:"size:36;unique_index"`
	Description     string
	Disabled        bool

	AgentID   uint `gorm:"index"`
	ClusterID uint `gorm:"index"`

	Containers []Container
	Processes  []Process
}

type Container struct {
	gorm.Model

	Type        string `gorm:"size:32"`
	ContainerID string `gorm:"size:128;index"`
	Name        string `gorm:"size:256"`
	Image       string `gorm:"size:128"`
	Info        postgres.Jsonb

	ClusterID uint `gorm:"index"`
	NodeID    uint `gorm:"index"`
	Processes []Process
}

type Process struct {
	gorm.Model

	Name string `gorm:"size:256"`
	PID  int32  `gorm:"index"`
	Cmd  string
	Info postgres.Jsonb

	ClusterID   uint `gorm:"index"`
	NodeID      uint `gorm:"index"`
	ContainerID uint `gorm:"index"`
}

type MetricEndpoint struct {
	gorm.Model

	Path string `gorm:"size:256;unique_index"`

	Metrics []Metric
	Events  []Event
}

type MetricName struct {
	gorm.Model

	Name string `gorm:"size:256;unique_index"`
	Help string

	TypeID uint `gorm:"index"`

	Metrics []Metric
	Events  []Event
}

type MetricLabel struct {
	gorm.Model

	Label string `gorm:"size:256;unique_index"`

	Metrics []Metric
	Events  []Event
}

type MetricType struct {
	gorm.Model

	Name string `gorm:"size:32;unique_index"`

	MetricNames []MetricName
}

type Metric struct {
	Ts    time.Time `gorm:"index"`
	Value float64   `gorm:"index"`

	EndpointID uint `gorm:"index"`
	TypeID     uint `gorm:"index"`
	NameID     uint `gorm:"index"`
	LabelID    uint `gorm:"index"`

	ClusterID   uint `gorm:"index"`
	NodeID      uint `gorm:"index"`
	ProcessID   uint `gorm:"index"`
	ContainerID uint `gorm:"index"`
}

type K8sMetric struct {
	Ts    time.Time
	Value float64

	EndpointID uint
	TypeID     uint
	NameID     uint
	LabelID    uint

	K8sClusterID   uint
	K8sNodeID      uint
	K8sNamespaceID uint
	K8sPodID       uint
	K8sContainerID uint
}

type Event struct {
	Ts    time.Time
	Value string

	Acked   bool
	AckedTs time.Time

	EndpointID uint
	TypeID     uint
	NameID     uint
	LabelID    uint

	ClusterID   uint
	AgentID     uint
	NodeID      uint
	ProcessID   uint
	ContainerID uint
	PodID       uint
}

type K8sEvent struct {
	Ts    time.Time
	Value string

	EndpointID uint
	TypeID     uint
	NameID     uint
	LabelID    uint

	ClusterID   uint
	NamespaceID uint
	NodeID      uint
	PodID       uint
	ContainerID uint
}

type Setting struct {
	gorm.Model

	Name  string `gorm:"size:128;index"`
	Value string
}

type K8sConnector struct {
	gorm.Model

	Name        string `gorm:"size:128;index"`
	Status      string `gorm:"size:32"`
	Method      string `gorm:"size:32"`
	InCluster   bool
	BearerToken string
	KubeConfig  string
}

type K8sCluster struct {
	gorm.Model

	Name           string `gorm:"size:128:index"`
	AgentClusterID uint
}

type K8sObject struct {
	gorm.Model

	K8sClusterID uint

	ApiVersion string `gorm:"size:128"`
	Kind       string `gorm:"size:128"`
	Name       string `gorm:"size:256"`

	Metadata postgres.Jsonb
	Spec     postgres.Jsonb
	Status   postgres.Jsonb
}

type K8sNode struct {
	gorm.Model

	Name string `gorm:"size:128"`

	K8sClusterID uint
	K8sObjectID  uint
}

type K8sNamespace struct {
	gorm.Model

	Name string `gorm:"size:128"`

	K8sClusterID uint
	K8sObjectID  uint
}

type K8sStatefulSet struct {
	gorm.Model

	Name string `gorm:"size:256"`

	K8sClusterID   uint
	K8sNamespaceID uint
	K8sObjectID    uint
}

type K8sDeployment struct {
	gorm.Model

	Name string `gorm:"size:256"`

	K8sClusterID   uint
	K8sNamespaceID uint
	K8sObjectID    uint
}

type K8sReplicaSet struct {
	gorm.Model

	Name string `gorm:"size:256"`

	K8sClusterID    uint
	K8sNamespaceID  uint
	K8sDeploymentID uint
	K8sObjectID     uint
}

type K8sDaemonSet struct {
	gorm.Model

	Name string `gorm:"size:256"`

	K8sClusterID   uint
	K8sNamespaceID uint
	K8sObjectID    uint
}

type K8sPod struct {
	gorm.Model

	Name string `gorm:"size:256"`
	Qos  string `gorm:"size:32"`

	K8sClusterID   uint
	K8sNamespaceID uint
	K8sObjectID    uint
}

type K8sContainer struct {
	gorm.Model

	Name          string `gorm:"size:256"`
	Image         string `gorm:"size:256"`
	ContainerType string `gorm:"size64"`
	ContainerId   string `gorm:"size:256"`

	K8sClusterID   uint
	K8sNamespaceID uint
	K8sPodID       uint
}

type K8sLabel struct {
	gorm.Model

	Label string `gorm:"size:256;index"`

	K8sObjectID uint
}

type K8sObjectTag struct {
	gorm.Model

	K8sObjectID uint
	K8sLabelID  uint
}

type IncidentBasicRule struct {
	gorm.Model

	Name        string
	Description string
	Query       string
}
