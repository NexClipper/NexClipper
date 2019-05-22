# Install NexClipper with HELM

## Install helm

```sh
$ curl https://raw.githubusercontent.com/helm/helm/master/scripts/get > get_helm.sh
$ chmod 700 get_helm.sh
$ ./get_helm.sh
```

> create service account and clusterrolebinding

```sh
$ kubectl -n kube-system create sa tiller
$ kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller
```

> install tiller

```sh
$ helm init --service-account tiller
```

## Install chart

> download helm chart of NexClipper

```sh
$ wget https://raw.githubusercontent.com/NexClipper/NexClipper/dev/HELM/nexclipper-0.1.0.tgz
```

> install the chart

```sh
$ helm install --set hostPath.influx=<influx data path>,hostPath.mysql=<mysql data path> nexclipper-0.1.0.tgz
```


## Uninstall chart

```sh
$ helm ls
$ helm delete <chart name>
```

## Configuration

The following table lists the configurable parameters of the NexClipper chart and their default values.

|           Parameter                  |              Description                       |         Default               |
|--------------------------------------|------------------------------------------------|-------------------------------|
| `hostPath.influx`                    | Hostpath of influx                             | `/nfs/influxdb`               |
| `hostPath.mysql`                     | Hostpath of mysql                              | `/nfs/mysql`                  |
