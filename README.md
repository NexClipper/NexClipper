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

- 본인이 원하는 특정한 db가 있을시에 아래 부분을 변경해야함

```yaml
// yaml/mysql/deployment.yaml
...
spec:
  containers:
    - env:
      - name: MYSQL_DATABASE
        value: defaultdb
      - name: MYSQL_PASSWORD
        value: password
      - name: MYSQL_ROOT_PASSWORD
        value: root
      - name: MYSQL_USER
        value: admin
...
```

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

- 다른 db를 사용하였다면 load.sql 파일에 `USE 'defaultdb'` 부분을 변경한다.
```yaml
// yaml/mysql/load.sql
...
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `defaultdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `defaultdb`;        # 'defaultdb'를 사용하고자 하는 db명으로 변경
...
```


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

- rabbitmq는 바로 create
```sh
  $ kubectl create -f <yaml/rabbitmq/deployment.yaml>
  $ kubectl create -f <yaml/rabbitmq/service.yaml>
```

- kafka로 설치를 진행 할 시 아래 부분을 본인 환경에맞게 변경해야함.

```yaml
// yaml/collector/deployment.yaml
...
env:
  - name: KAFKA_ZOOKEEPER
    value: "kafka-zookeeper.kafka-test-02:2181"
  - name: KAFKA_PORT
    value: '9092'
  - name: KAFKA_HOST
    value: "kafka-kafka.kafka-test-02"
  - name: MYSQL_DBNAME
    value: "defaultdb"
  - name: MYSQL_URL
    value: "mysql.nexclipper:3306"
  - name: MYSQL_PASSWORD
    value: "password"
  - name: MYSQL_USERNAME
    value: "admin"
  - name: REDIS_HOST
    value: redis.nexclipper
  - name: REDIS_PORT
    value: '6379'
  - name: INFLUXDB_ENDPOINT
    value: "http://influx.nexclipper:8087"
  - name: INFLUXDB_DATASOURCE
    value: "nexclipper"
  - name: TDB
    value: INFLUX
  - name: PUSHGATEWAY_ENDPOINT
    value: prometheus-h-pushgateway.prometheus:9091
  - name: BROKER
    value: rabbitmq
  - name: RABBITMQ_HOST
    value: rabbitmq.nexclipper
  - name: RABBITMQ_PORT
    value: '5672'
...
```


### NexClipper service deployment

> #### workflow

- 제공되는 yaml파일 외에 따로 수정한 부분이 있으면 아래 파일을 본인 클러스터 환경에 맞게 변경해야함.
```yaml
// yaml/workflow/deployment.yaml
...
env:
  - name: KAFKA_ZOOKEEPER
    value: "kafka-zookeeper.kafka-test-02:2181"
  - name: KAFKA_PORT
    value: '9092'
  - name: KAFKA_HOST
    value: "kafka-kafka.kafka-test-02"
  - name: MYSQL_DBNAME
    value: "defaultdb"
  - name: MYSQL_URL
    value: "mysql.nexclipper:3306"
  - name: MYSQL_PASSWORD
    value: "password"
  - name: MYSQL_USERNAME
    value: "admin"
  - name: REDIS_HOST
    value: redis.nexclipper
  - name: REDIS_PORT
    value: '6379'
  - name: INFLUXDB_ENDPOINT
    value: "http://influx.nexclipper:8087"
  - name: INFLUXDB_DATASOURCE
    value: "nexclipper"
  - name: BROKER
    value: rabbitmq
  - name: RABBITMQ_HOST
    value: rabbitmq.nexclipper
  - name: RABBITMQ_PORT
    value: '5672'
...
```
      
- create

```sh
  $ kubectl create -f <yaml/workflow/deployment.yaml>
```

> #### collector

- 제공되는 yaml파일 외에 따로 수정한 부분이 있으면 아래 파일을 본인 클러스터 환경에 맞게 변경해야함.
```yaml
// yaml/collector/deployment.yaml
...
spec:
  replicas: 2           # default는 2개 이지만 노드 수에 맞춰주는게 가장 좋음
...
env:
  - name: KAFKA_ZOOKEEPER
    value: "kafka-zookeeper.kafka-test-02:2181"
  - name: KAFKA_PORT
    value: '9092'
  - name: KAFKA_HOST
    value: "kafka-kafka.kafka-test-02"
  - name: MYSQL_DBNAME
    value: "defaultdb"
  - name: MYSQL_URL
    value: "mysql.nexclipper:3306"
  - name: MYSQL_PASSWORD
    value: "password"
  - name: MYSQL_USERNAME
    value: "admin"
  - name: REDIS_HOST
    value: redis.nexclipper
  - name: REDIS_PORT
    value: '6379'
  - name: INFLUXDB_ENDPOINT
    value: "http://influx.nexclipper:8087"
  - name: INFLUXDB_DATASOURCE
    value: "nexclipper"
  - name: TDB
    value: INFLUX
  - name: PUSHGATEWAY_ENDPOINT
    value: prometheus-h-pushgateway.prometheus:9091
  - name: BROKER
    value: rabbitmq
  - name: RABBITMQ_HOST
    value: rabbitmq.nexclipper
  - name: RABBITMQ_PORT
    value: '5672'
...
```
- create
```sh
  $ kubectl create -f <yaml/collector/deployment.yaml>
  $ kubectl create -f <yaml/collector/service.yaml>
```

> #### nexservice

- 제공되는 yaml파일 외에 따로 수정한 부분이 있으면 아래 파일을 본인 클러스터 환경에 맞게 변경해야함.
```yaml
// yaml/nexservice/deployment.yaml
...
env:
  - name: MYSQL_DBNAME
    value: "defaultdb"
  - name: MYSQL_URL
    value: "mysql.nexclipper"
  - name: MYSQL_PORT
    value: '3306'
  - name: MYSQL_USERNAME
    value: "admin"
  - name: MYSQL_PASSWORD
    value: "password"
  - name: REDIS_ENDPOINT
    value: "redis.nexclipper"
  - name: REDIS_PORT
    value: '6379'
  - name: TSDB
    value: "influx"
  - name: INFLUXDB_ENDPOINT
    value: "http://influx.nexclipper:8087"
  - name: INFLUXDB_DATASOURCE
    value: "nexclipper"
  - name: ACTIVE
    value: "dev"
...
```

- create
```sh
  $ kubectl create -f <yaml/nexservice/deployment.yaml>
  $ kubectl create -f <yaml/nexservice/service.yaml>
```

### NexClipper Agent daemonset/deployment

-Install the agent on the cluster node (master) to be monitored as follows
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

