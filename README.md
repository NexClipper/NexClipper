# What is NexClipper?  
NexCloud is the container monitoring and performance management solution specialized in Docker, Apache Mesos, Marathon, DC/OS, Mesosphere, Kubernetes. It will provide container performance management and Machine learning based predictive, forecasting, anormaly detection.


![GUI1](images/logo1.png) 

# NexClipper Cloud 
NexClipper Cloud is online SaaS for monitoring and managing performance of container cluster -  Docker, DC/OS and Kubernetes.
NexClipper Cloud features the following capabilities:
* Fullstack dashboard (Infrastructure, DC/OS, Kubernetes)
* Container Cluster (DC/OS, Kuberentes)
* Service Performance (for API)
* Infrastruture Monitoring (Container, Host, Resource)
* Incidents Management
* AI Analytics (Forecasting, Anomaly detection, Metric correlation)

For try NexClipper Cloud beta, visit http://server.nexclipper.com



# NexClipper Light 
NexClipper light is host level monitoring tool for docker, DC/OS and Kubernetes without addational longterm data store, and not for container cluster level. But it provide almost necessary information to monitor docker instanty.
NexClipper Light features the following capabilities:
* Dashboard
* Container list
* Status of containers (total continers, running, paused, stopped)
* Number of Images
* Container resource usage (cpu, memory, network, block i/o)
* Container process, network , environment, network, log

# Quick Start: Running NexClipper Light in a Docker Container
You can run NexClipper to monitor the docker containers.  
Simply run:

```
sudo docker pull nexclipper/nexclipper;
```

```
sudo docker run \
	     --detach=true \
	     --name NexClipper \
	     -p 10001:9001 \
	     --volume /var/run/docker.sock:/var/run/docker.sock \
	     --volume /var/lib/docker:/var/lib/docker \
	     nexclipper/nexclipper;
```

**NexClipper light is now running (in the background) on http://localhost:10001.**

# NexClipper Light Web UI
NexClipper exposes a web UI at its port:  
**http://<HOST_IP>:<HOST_PORT>/**

# NexClipper LightDashboard
The dashboard shows the container's statistics, status, logs, and various information.
![GUI1](images/main_k8s.PNG)  

![GUI1](images/detail_container.PNG)

# Licensing
NexClipper is licensed under the Apache License, Version 2.0. See 
[LICENSE](https://github.com/TheNexCloud/NexClipper/blob/master/LICENSE) for the full
license text.

# Contact
http://www.nexcloud.co.kr/  
nexcloud@nexcloud.co.kr 
