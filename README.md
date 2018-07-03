# What is NexDocker?  
NexDocker collects statistics, status information, process, network, environment, volume, and log information about containers.

# Feature Overview
NexDocker features the following capabilities:
* Dashboard
* Container list
* Status of containers (total continers, running, paused, stopped)
* Number of Images
* Container resource usage (cpu, memory, network, block i/o)
* Container process, network , environment, network, log

# Quick Start: Running NexDocker in a Docker Container
You can run nexdocker to monitor the docker containers.  
Simply run:

```
sudo docker pull nexclipper/nexdocker;
```

```
sudo docker run -d \
	     --detach=true \
	     --name NexDocker \
	     -p 10001:9001 \
	     --volume /var/run/docker.sock:/var/run/docker.sock \
	     --volume /var/lib/docker:/var/lib/docker \
	     nexclipper/nexdocker;
```

**NexDocker is now running (in the background) on http://localhost:10001.**

# Web UI
NexDocker exposes a web UI at its port:  
**http://<HOST_IP>:<HOST_PORT>/**

## Dashboard
The dashboard shows the container's statistics, status, logs, and various information.
![GUI1](images/main.PNG)  

![GUI1](images/detail_container.PNG)

# Contact
http://www.nexcloud.co.kr/  
![Alt text](https://pbs.twimg.com/profile_images/989758552326660098/GNYr5cyO_400x400.jpg)
