# If you don't use 'defaultdb' for MySQL or use kafka

- update deployment
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
