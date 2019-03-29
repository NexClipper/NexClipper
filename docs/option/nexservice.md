# 특정 DB사용시 수정해야 하는 부분

- deployment 수정
```yaml
// yaml/nexservice/deployment.yaml
...
env:
  - name: MYSQL_DBNAME
    value: "defaultdb"
  - name: MYSQL_URL
    value: "mysql.nexclipper"
  - name: MYSQL_PORT
    value: '3306'
  - name: MYSQL_USERNAME
    value: "admin"
  - name: MYSQL_PASSWORD
    value: "password"
  - name: REDIS_ENDPOINT
    value: "redis.nexclipper"
  - name: REDIS_PORT
    value: '6379'
  - name: TSDB
    value: "influx"
  - name: INFLUXDB_ENDPOINT
    value: "http://influx.nexclipper:8087"
  - name: INFLUXDB_DATASOURCE
    value: "nexclipper"
  - name: ACTIVE
    value: "dev"
...
```
