# System Requirements

- Linux
- Go: 1.11 or above
- ProtocolBuffer Compiler
- TimescaleDB



# Setup build environments

In `ubuntu` distribution

```bash
sudo apt install -y build-essential
sudo apt install -y protobuf-compiler
go get -u github.com/golang/protobuf/protoc-gen-go
export PATH=$PATH:~/go/bin
```



# Initialize database

## Install TimescaleDB (docker)

Refer to https://docs.timescale.com/latest/getting-started/installation document for other platform.



Pull `timescaledb` docker image from [Docker Hub](https://hub.docker.com/r/timescale/timescaledb/).

```bash
docker pull timescale/timescaledb:1.4.2-pg11
```



Start with persistent storage.

```bash
docker run -d --name timescaledb -p 5432:5432 -e POSTGRES_PASSWORD=password \
  -v [/local/disk/storage]:/var/lib/postgresql/data \
  timescale/timescaledb:1.4.2-pg11
```

```bash
docker ps | grep timescaledb

db2efef9599f        timescale/timescaledb:1.4.2-pg11   "docker-entrypoint.s…"   18 seconds ago      Up 16 seconds       0.0.0.0:5432->5432/tcp   timescaledb
```



First connect to the PostgreSQL instance.

```bash
docker exec -it db2e bash

bash-5.0# psql -h localhost -U postgres
psql (11.5)
Type "help" for help.

postgres=#
```



Create `nexclipper` database.

```bash
postgres=# CREATE DATABASE nexclipper;
CREATE DATABASE
postgres=# \q
bash-5.0# exit
exit
```



## Initialize tables

Execute database migration command

```bash
go get github.com/NexClipper/NexClipper
cd ~/go/github.com/NexClipper/NexClipper

go mod download
go run cmd/migrate/migrate.go --db.host=localhost --db.user=postgres \
  --db.pass=password --db.name=nexclipper
```



# Build executable binaries

## NexServer binary and docker image

```bash
make nexserver nexserver-docker
...

ls -al build/nexserver
```



## NexAgent binary and docker image

```bash
make nexagent nexagent-docker
...

ls -al build/nexagent
```



# Start NexServer and NexAgent

## Execute NexServer

```bash
./build/nexserver/nexserver --db.host=localhost --db.user=postgres --db.pass=password --db.name=nexclipper

2019/10/21 09:11:17 Server: listen at 0.0.0.0:18000
```



Use docker container

```bash
docker run -it -p 18000:18000 -p 18001:18001 -e NEXSERVER_DB_HOST=10.9.1.2 \
  -e NEXSERVER_DB_USER=postgres -e NEXSERVER_DB_PASS=password \
  -e NEXSERVER_DB_NAME=nexclipper \
  nexserver:0.2.0

2019/10/21 10:14:48 Server: listen at 0.0.0.0:18000
```



## Execute NexAgent

Open another terminal and execute `nexagent` 

```bash
./build/nexagent/nexagent --server localhost:18000 --agent.cluster local-machines

2019/10/21 09:17:13 Trying to get Kubernetes configuration in cluster
2019/10/21 09:17:13 Trying to use KUBERCONFIG environment variable
2019/10/21 09:17:13 Kubernetes API Server: https://10.9.1.2:6443
2019/10/21 09:17:13 Server connected
...
```



Use docker container

```bash
docker run -it --network host -e NEXAGENT_SERVER_ADDRESS=localhost:18000 \
  -e NEXAGENT_CLUSTER=local-machines -v /var/run/docker.sock:/var/run/docker.dock \
  -v /var/lib/docker:/var/lib/docker -v /dev:/dev -v /sys:/sys -v /var/log:/var/log \
  -v /etc/kubernetes/pki:/etc/kubernetes/pki -v /etc:/etc \
  nexagent:0.2.0

2019/10/21 10:20:31 Trying to get Kubernetes configuration in cluster
2019/10/21 10:20:31 Trying to use KUBERCONFIG environment variable
2019/10/21 10:20:31 Server connected
```



## Check NexServer and NexAgent status using REST API

```bash
curl http://localhost:18001/api/agents | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   113  100   113    0     0  11300      0 --:--:-- --:--:-- --:--:-- 11300
{
  "data": {
    "local-machines": [
      {
        "id": 1,
        "version": "0.2.0",
        "ip": "10.9.1.2",
        "online": true
      }
    ]
  },
  "message": "",
  "status": "ok"
}
```

```bash
curl http://localhost:18001/api/nodes | jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   201  100   201    0     0  28714      0 --:--:-- --:--:-- --:--:-- 33500
{
  "data": {
    "local-machines": [
      {
        "id": 1,
        "host": "node-1",
        "ip": "10.9.1.2",
        "os": "linux",
        "platform": "ubuntu",
        "platform_family": "debian",
        "platform_version": "18.04",
        "agent_id": 1
      }
    ]
  },
  "message": "",
  "status": "ok"
}
```

