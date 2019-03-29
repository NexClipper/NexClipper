# NexClipper

NexClipper is the container and container orchestration monitoring and performance management solution specialized in Docker, DC/OS, Mesosphere, Kubernetes. NexClipper Cloud especilly supports machine learning based predictive, forecasting, anormaly detection.

There are two different versions of NexClipper: NexClipper Cloud and NexClipper.

Please note that previsous NexClipper Light project which was host level container monitoring tool was moved to [NexLight](https://github.com/NexClipper/NexClipper/tree/master/NexLight) directory.

![](docs/images/logo1.png)

## NexClipper Cloud

NexClipper Cloud is an online SaaS to monitor and manage performance of the container cluster -  Docker, DC/OS and Kubernetes.
NexClipper Cloud features the following capabilities:
- Fullstack dashboard (Infrastructure, DC/OS, Kubernetes)
- Container Cluster (DC/OS, Kuberentes)
- Service Performance (for API)
- Infrastruture Monitoring (Container, Host, Resource)
- Incidents Management
- AI Analytics (Forecasting, Anomaly detection, Metric correlation)

For more details visit  https://www.nexclipper.com/
For beta service, visit https://server.nexclipper.com

## NexClipper  

NexClipper is an open source software to monitor and manage performance of the container cluster -  Docker and Kubernetes.
NexClipper features the following capabilities:
- Fullstack dashboard (Infrastructure, Kubernetes)
- Container Cluster (Kuberentes)
- Infrastruture Monitoring (Container, Host, Resource)
- Incidents Management

## Architecture Overview

![](docs/images/NexClipper_Architecture.png)

## NexClipper Dashboard

![](docs/images/NexClipper_dashboard.png)

## Install

There are various ways of installing NexClipper.

### Prerequisites

- Installed Kubernetes Cluster (Master Node, Worker Node 1 more)
- An SSH key pair on your local Linux/macOS/BSD machine.
- ***Create namespace `nexclipper'***

### Prepare deployment

kubectl이 설치된 클러스터에서 진행

먼저, `yaml` 디렉토리 밑의 모든 파일을 다운받아서 원하는 디렉토리에 저장한다.

> #### redis

- create
```sh
  $ kubectl create -f <yaml/redis/deployment.yaml>
  $ kubectl create -f <yaml/redis/service.yaml>
```

> #### mysql(or mariaDB)

- 클러스터 환경에 맞게 저장경로 변경
```yaml
// yaml/mysql/deployment.yaml
...
volumes:
  - name: mysql-data
    hostPath:
      path: /nfs/mysql        # 본인 클러스터 환경에 맞춰서 변경
...
```

- create
```sh
  $ kubectl create -f <yaml/mysql/deployment.yaml>
  $ kubectl create -f <yaml/mysql/service.yaml>
```

- [특정 DB사용시 수정해야 하는 부분](https://github.com/NexClipper/NexClipper/blob/dev/docs/option/mysql.md})

> #### influxdb

- 클러스터 환경에 맞게 저장경로 변경
```yaml
// yaml/influx/deployment.yaml
...
volumes:
  - name: influx-data
    hostPath:
      path: /nfs/influxdb        # 본인 클러스터 환경에 맞춰서 변경
...
```

- create
```sh
  $ kubectl create -f <yaml/influxdb/deployment.yaml>
  $ kubectl create -f <yaml/influxdb/service.yaml>
```

> #### rabbitmq (or kafka)

- create
```sh
  $ kubectl create -f <yaml/rabbitmq/deployment.yaml>
  $ kubectl create -f <yaml/rabbitmq/service.yaml>
```

- [kafka로 설치시 수정해야 하는 부분](https://github.com/NexClipper/NexClipper/blob/dev/docs/option/kafka.md)


### NexClipper service deployment

> #### workflow

- create
```sh
  $ kubectl create -f <yaml/workflow/deployment.yaml>
```

- [kafka를 사용하거나 다른 DB를 사용할때 수정해야 하는 부분](https://github.com/NexClipper/NexClipper/blob/dev/docs/option/workflow.md)

> #### collector

- create
```sh
  $ kubectl create -f <yaml/collector/deployment.yaml>
  $ kubectl create -f <yaml/collector/service.yaml>
```

- [kafka를 사용하거나 다른 DB를 사용할때 수정해야 하는 부분](https://github.com/NexClipper/NexClipper/blob/dev/docs/option/collector.md)


> #### nexservice

- create
```sh
  $ kubectl create -f <yaml/nexservice/deployment.yaml>
  $ kubectl create -f <yaml/nexservice/service.yaml>
```

- [특정 DB사용시 수정해야 하는 부분](https://github.com/NexClipper/NexClipper/blob/dev/docs/option/nexservice.md)


### NexClipper Agent daemonset/deployment

- Install the agent on the cluster node (master) to be monitored as follows
  - role of daemonset: get host and docker container's information
  - role of deployment: get kubernetes cluster's information

- endpoint 변경
```yaml
// yaml/nexclipper-agent/nexclipper-agent.yaml
...
kind: DaemonSet
...
env:
  - name: agent_endpoint
    value: 192.168.0.180:32100      # <k8s master ip>:<해당 서비스의 nodeport>
...
kind: Deployment
...
env:
  - name: agent_endpoint
    value: 192.168.0.180:32100      # <k8s master ip>:<해당 서비스의 nodeport>
...
```

- create
```sh
  $ kubectl create -f <yaml/nexclipper-agent/nexclipper-agent.yaml>
```

### 웹으로 연결
```
  https://<k8s master ip>:32200
```

## Licensing

NexClipper is licensed under the Apache License, Version 2.0. See [LICENSE](https://github.com/NexClipper/NexClipper/blob/master/LICENSE) for the full license text.

## Contact

Email: nexclipper@nexclipper.com

Homepage: https://www.nexclipper.com/

Facebook : https://www.facebook.com/nexclipper/

Linkedin: https://www.linkedin.com/company/nexcloud/

Twitter: https://twitter.com/NexClipper

