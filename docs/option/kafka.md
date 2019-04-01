# If you use Kafka

- Update deployment 

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
    value: rabbitmq                 # kafka
  - name: RABBITMQ_HOST             # don't use
    value: rabbitmq.nexclipper  
  - name: RABBITMQ_PORT             # don't use
    value: '5672'
...
```
