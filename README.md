# What is NexClipper?  
NexClipper is the simple and easy container monitoring tool which to collects statistics, status, process, network, environment, volume, and log.

![GUI1](images/logo1.png) 

# Feature Overview
NexClipper features the following capabilities:
* Dashboard
* Container list
* Status of containers (total continers, running, paused, stopped)
* Number of Images
* Container resource usage (cpu, memory, network, block i/o)
* Container process, network , environment, network, log

# Quick Start: Running NexClipper in a Docker Container
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

**NexClipper is now running (in the background) on http://localhost:10001.**

# Web UI
NexClipper exposes a web UI at its port:  
**http://<HOST_IP>:<HOST_PORT>/**

## Dashboard
The dashboard shows the container's statistics, status, logs, and various information.
![GUI1](images/main_k8s.PNG)  

![GUI1](images/detail_container.PNG)

# Contact
http://www.nexcloud.co.kr/  
