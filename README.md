# NexClipper

NexClipper is the container and container orchestration monitoring and performance management solution specialized in Docker, DC/OS, Mesosphere, Kubernetes. NexClipper Cloud especilly supports machine learning based predictive, forecasting, anormaly detection.

There are two different versions of NexClipper: NexClipper Cloud and NexClipper.

Please note that previsous NexClipper Light project which was a host level container monitoring tool has been moved to [NexLight](https://github.com/NexClipper/NexClipper/tree/master/NexLight) directory.

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

For more details visit  https://www.nexclipper.io/
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

### Prepare deployment

On Kubernetes Master

- redis

      $kubectl create -f redis.yaml  
      $kubectl create -f redisservice.yaml

- mysql

      $kubectl create -f mysql.yaml  
      $kubectl create -f mysqlservice.yaml

- influxdb

      $kubectl create -f influx.yaml   
      $kubectl create -f influxservice.yaml


### Nexclipper service deployment

- workflow

      $kubectl create -f workflow_deployment.yaml  
      $kubectl create -f workflow_service.yaml


- collector

      $kubectl create -f collectorapi_deployment.yaml  
      $kubectl create -f collectorapi_service.yaml

- ui

      $kubectl create -f deployment.yaml
      $kubectl create -f service.yaml


### Nexclipper Agent deployment

    $kubectl create -f nexclipper-agent.yaml


### Docker images

Docker image

    $nexclipper/nexagent

NexClipper will... 

## Licensing

NexClipper is licensed under the Apache License, Version 2.0. See [LICENSE](https://github.com/NexClipper/NexClipper/blob/master/LICENSE) for the full license text.

## Contact

Email: nexclipper@nexclipper.com

Homepage: https://www.nexclipper.io/

Facebook : https://www.facebook.com/nexclipper/

Linkedin: https://www.linkedin.com/company/nexcloud/

Twitter: https://twitter.com/NexClipper
