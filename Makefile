.DEFAULT_GOAL=build
.PHONY: build
NEXSERVER=nexserver
NEXAGENT=nexagent
VERSION=0.3.0
DOCKER_REGISTRY=

PROTOC_GEN_GO := $(GOPATH)/bin/protoc-gen-go

$(PROTOC_GEN_GO):
	go get -u github.com/golang/protobuf/protoc-gen-go

nexclipper.pb.go: api/nexclipper.proto | $(PROTOC_GEN_GO)
	protoc -I api/ api/nexclipper.proto --go_out=plugins=grpc:api

compile: nexclipper.pb.go

nexserver: cmd/nexserver/main.go api/nexclipper.pb.go
	mkdir -p build/nexserver
	go mod download
	go build -a -o build/nexserver/nexserver ./cmd/nexserver/

nexserver-docker: Dockerfile-nexserver nexserver
	docker build -f Dockerfile-nexserver -t $(NEXSERVER):$(VERSION) .
	docker tag $(NEXSERVER):$(VERSION) $(DOCKER_REGISTRY)$(NEXSERVER):$(VERSION)

nexagent: cmd/nexagent/main.go api/nexclipper.pb.go
	mkdir -p build/nexagent
	go mod download
	go build -a -o build/nexagent/nexagent ./cmd/nexagent/

nexagent-docker: Dockerfile-nexagent nexagent
	docker build -f Dockerfile-nexagent -t $(NEXAGENT):$(VERSION) .
	docker tag $(NEXAGENT):$(VERSION) $(DOCKER_REGISTRY)$(NEXAGENT):$(VERSION)

nexagent-docker-push: nexagent-docker
	docker push $(DOCKER_REGISTRY)$(NEXAGENT):$(VERSION)

nexserver-docker-push: nexserver-docker
	docker push $(DOCKER_REGISTRY)$(NEXSERVER):$(VERSION)

all: nexclipper.pb.go nexserver nexagent nexserver-docker nexagent-docker

build: nexagent nexserver

docker: nexserver-docker nexagent-docker

clean:
	rm api/nexclipper.pb.go
