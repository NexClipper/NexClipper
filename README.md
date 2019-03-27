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
- Installed Apache Kafka on kubernetes Cluster (including Zookeeper)
- An SSH key pair on your local Linux/macOS/BSD machine.
- Create namespace `nexclipper'

### Prepare deployment

On Kubernetes Master

> #### redis

      $ kubectl create -f <yaml/redis/deployment.yaml>
      $ kubectl create -f <yaml/redis/service.yaml>

> #### mysql

본인이 원하는 db가 있을시에 변경해야 할 부분들.

- load.sql 파일에 `USE 'defaultdb'` 부분을 변경한다.
- deployment.yaml에서 env 변경

`hostPath` 각자 클러스터 환경에 맞게 변경

      $ kubectl create -f <yaml/mysql/deployment.yaml>
      $ kubectl create -f <yaml/mysql/service.yaml>

> #### influxdb

`hostPath` 각자 클러스터 환경에 맞게 변경

      $ kubectl create -f <yaml/influxdb/deployment.yaml>
      $ kubectl create -f <yaml/influxdb/service.yaml>

> #### rabbitmq

      $ kubectl create -f <yaml/rabbitmq/deployment.yaml>
      $ kubectl create -f <yaml/rabbitmq/service.yaml>

### Nexclipper service deployment
First, create an arbitrary folder on the master of the cluster.

      eg. mkdir -p /tmp/nexclipper/yaml

Download all `yaml`, subfolders, and files

> #### workflow

deployment.yaml 파일 수정
- replicas
      - 개수는 2~4개
      
- env
  - kafka 정보 수정
  - MYSQL은 제공되는 mysql yaml 파일을 사용한다면 수정 안해도 되지만 본인이 사용하는 db가 따로 있으면 수정이 필요함
  - BROKER 정보도 제공되는 rabbitmq를 사용한다면 수정 안해도 되지만 본인이 사용하는 broker가 따로 있으면 수정이 필요함
```
  $ kubectl create -f <yaml/workflow/deployment.yaml>
```
> #### collector

deployment.yaml 파일에 env 수정
- kafka 정보 수정
- MYSQL은 제공되는 mysql yaml 파일을 사용한다면 수정 안해도 되지만 본인이 사용하는 db가 따로 있으면 수정이 필요함
- BROKER 정보도 제공되는 rabbitmq를 사용한다면 수정 안해도 되지만 본인이 사용하는 broker가 따로 있으면 수정이 필요함.

```
  $ kubectl create -f <yaml/collector/deployment.yaml>
  $ kubectl create -f <yaml/collector/service.yaml>
```
> #### nexservice

deployment.yaml 파일에 env 수정

      $ kubectl create -f <yaml/nexservice/deployment.yaml>
      $ kubectl create -f <yaml/nexservice/service.yaml>


### Nexclipper Agent daemonset/deployment
Install the agent on the cluster node (master) to be monitored as follows
- daemonset: get host and docker container's information
- deployment: get kubernetes cluster's information

daemonset과 deployment의 env에서 agent_endpoint의 ip를 본인 클러스터의  ip로 변경해야 함.

      $ kubectl create -f <yaml/nexclipper-agent/nexclipper-agent.yaml>


## Licensing

NexClipper is licensed under the Apache License, Version 2.0. See [LICENSE](https://github.com/NexClipper/NexClipper/blob/master/LICENSE) for the full license text.

## Contact

Email: nexclipper@nexclipper.com

Homepage: https://www.nexclipper.com/

Facebook : https://www.facebook.com/nexclipper/

Linkedin: https://www.linkedin.com/company/nexcloud/

Twitter: https://twitter.com/NexClipper

