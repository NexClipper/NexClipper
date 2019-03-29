# 특정 DB로 변경시 수정해야할 부분

- deployment 코드 수정
```yaml
// yaml/mysql/deployment.yaml
...
spec:
  containers:
    - env:
      - name: MYSQL_DATABASE
        value: defaultdb
      - name: MYSQL_PASSWORD
        value: password
      - name: MYSQL_ROOT_PASSWORD
        value: root
      - name: MYSQL_USER
        value: admin
...
```

- load.sql 파일에 `USE 'defaultdb'` 부분을 변경한다.
```yaml
// yaml/mysql/load.sql
...
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `defaultdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `defaultdb`;        # 'defaultdb'를 사용하고자 하는 db명으로 변경
...
```
