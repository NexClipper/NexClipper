/*
Copyright 2019 NexClipper.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package nexserver

import (
	"database/sql"
	"fmt"
	"github.com/jinzhu/gorm"
	"log"
	"sync"
	"time"
)

func Migrate(host string, port int, user string, password string, dbname string, sslmode string) error {
	dbConnStr := fmt.Sprintf(
		"host=%s port=%d user=%s password=%s dbname=%s sslmode=%s",
		host, port, user, password, dbname, sslmode)

	db, err := gorm.Open("postgres", dbConnStr)
	if err != nil {
		return fmt.Errorf("failed to connect database: %v", err)
	}

	defer func() {
		err := db.Close()
		if err != nil {
			log.Printf("Failed database closing: %v\n", err)
		}
	}()

	db.AutoMigrate(
		&Cluster{}, &Agent{}, &Node{},
		&Container{}, &Process{},
		&MetricEndpoint{}, &MetricName{}, &MetricLabel{}, &MetricType{},
		&Metric{}, &K8sMetric{},
		&Event{}, &K8sEvent{}, &K8sLabel{},
		&K8sCluster{}, &K8sNamespace{}, &K8sNode{},
		&K8sObject{}, &K8sDeployment{}, &K8sStatefulSet{}, &K8sDaemonSet{},
		&K8sReplicaSet{}, &K8sPod{}, &K8sContainer{}, &K8sObjectTag{},
		&Setting{}, &K8sConnector{}, &IncidentBasicRule{})
	db.Exec("select create_hypertable('metrics', 'ts', chunk_time_interval => interval '1 day');")
	db.Exec("select create_hypertable('events', 'ts', chunk_time_interval => interval '1 day');")
	db.Exec("select create_hypertable('k8s_metrics', 'ts', chunk_time_interval => interval '1 day');")
	db.Exec("select create_hypertable('k8s_events', 'ts', chunk_time_interval => interval '1 day');")

	return nil
}

func (s *NexServer) ConnectDatabase() (*gorm.DB, error) {
	dbConf := s.config.Database
	dbConnStr := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s sslmode=%s",
		dbConf.Host, dbConf.Port, dbConf.User, dbConf.Password, dbConf.DbName, dbConf.SslMode)
	db, err := gorm.Open("postgres", dbConnStr)
	if err != nil {
		return nil, err
	}

	s.db = db

	s.dbLock["CLUSTER"] = &sync.RWMutex{}
	s.dbLock["AGENT"] = &sync.RWMutex{}
	s.dbLock["NODE"] = &sync.RWMutex{}
	s.dbLock["PROCESS"] = &sync.RWMutex{}
	s.dbLock["CONTAINER"] = &sync.RWMutex{}
	s.dbLock["METRIC_NAME"] = &sync.RWMutex{}
	s.dbLock["ENDPOINT"] = &sync.RWMutex{}
	s.dbLock["TYPE"] = &sync.RWMutex{}
	s.dbLock["LABEL"] = &sync.RWMutex{}

	s.db.Table("agents").Updates(map[string]interface{}{"online": false})

	return s.db, nil
}

func (s *NexServer) findRemoteAgent(machineId string) *Agent {
	var agent Agent

	result := s.db.Where("machine_id=?", machineId).First(&agent)
	if result.Error != nil {
		return nil
	}

	return &agent
}

func (s *NexServer) findNodeByAgent(agent *Agent) *Node {
	var node Node

	result := s.db.Where("agent_id=?", agent.ID).First(&node)
	if result.Error != nil {
		return nil
	}

	return &node
}

func (s *NexServer) findCluster(clusterName string) *Cluster {
	var cluster Cluster

	s.dbLock["CLUSTER"].Lock()

	result := s.db.Where("name=?", clusterName).First(&cluster)
	if result.Error != nil {
		cluster = Cluster{
			Name: clusterName,
		}

		s.db.Create(&cluster)
	}

	s.dbLock["CLUSTER"].Unlock()
	return &cluster
}

func (s *NexServer) findMetricEndpoint(endpoint string) *MetricEndpoint {
	var metricEndpoint MetricEndpoint

	s.dbLock["ENDPOINT"].Lock()

	result := s.db.Where("path=?", endpoint).First(&metricEndpoint)
	if result.Error != nil {
		metricEndpoint = MetricEndpoint{
			Path: endpoint,
		}

		s.db.Create(&metricEndpoint)
	}

	s.dbLock["ENDPOINT"].Unlock()
	return &metricEndpoint
}

func (s *NexServer) findMetricType(typeName string) *MetricType {
	var metricType MetricType

	s.dbLock["TYPE"].Lock()

	result := s.db.Where("name=?", typeName).First(&metricType)
	if result.Error != nil {
		metricType = MetricType{
			Name: typeName,
		}

		s.db.Create(&metricType)
	}

	s.dbLock["TYPE"].Unlock()
	return &metricType
}

func (s *NexServer) findMetricName(name string, metricType *MetricType) *MetricName {
	var metricName MetricName

	s.dbLock["METRIC_NAME"].Lock()

	result := s.db.Where("name=?", name).First(&metricName)
	if result.Error != nil {
		metricName = MetricName{
			Name:   name,
			TypeID: metricType.ID,
		}

		s.db.Create(&metricName)
	}

	s.dbLock["METRIC_NAME"].Unlock()
	return &metricName
}

func (s *NexServer) findMetricLabel(label string) *MetricLabel {
	var metricLabel MetricLabel

	s.dbLock["LABEL"].Lock()

	result := s.db.Where("label=?", label).First(&metricLabel)
	if result.Error != nil {
		metricLabel = MetricLabel{
			Label: label,
		}

		s.db.Create(&metricLabel)
	}

	s.dbLock["LABEL"].Unlock()
	return &metricLabel
}

func (s *NexServer) findNode(hostName string, clusterId uint) *Node {
	var node Node

	result := s.db.Where("host=? AND cluster_id=?", hostName, clusterId).First(&node)
	if result.Error != nil {
		return nil
	}

	return &node
}

func (s *NexServer) findNodeById(id, clusterId uint) *Node {
	var node Node

	result := s.db.Where("id=? AND cluster_id=?", id, clusterId).First(&node)
	if result.Error != nil {
		return nil
	}

	return &node
}

func (s *NexServer) findContainer(containerName string, nodeId, clusterId uint) *Container {
	var container Container

	result := s.db.Where(
		"name=? AND node_id=? AND cluster_id=?",
		containerName, nodeId, clusterId).First(&container)
	if result.Error != nil {
		return nil
	}

	return &container
}

func (s *NexServer) findProcess(processName string, pid int32, nodeId, clusterID uint) *Process {
	var process Process

	result := s.db.Where(
		"name=? AND p_id=? AND node_id=? AND cluster_id=?",
		processName, pid, nodeId, clusterID).First(&process)
	if result.Error != nil {
		return nil
	}

	return &process
}

func (s *NexServer) QueryRowsWithTime(q *gorm.DB) (*sql.Rows, error, time.Duration) {
	queryStart := time.Now()
	rows, err := q.Rows()
	queryTime := time.Since(queryStart)

	return rows, err, queryTime
}
