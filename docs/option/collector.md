# kafka를 사용하거나 다른 DB를 사용할때 수정해야 하는 부분

- deployment 수정
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
