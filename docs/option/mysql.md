# If you use your own DB instead of 'defaultdb' 

- Update deployment 
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

- Update DB at load.sql 
```yaml
// yaml/mysql/load.sql
...
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `defaultdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `defaultdb`;        # update if you use other DB
...
```
