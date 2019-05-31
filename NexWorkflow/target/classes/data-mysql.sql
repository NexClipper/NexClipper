-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: 
-- ------------------------------------------------------
-- Server version	5.7.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `defaultdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `defaultdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `defaultdb`;

--
-- Table structure for table `nexclipper_command`
--

-- DROP TABLE IF EXISTS `nexclipper_command`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `nexclipper_command` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(2) NOT NULL COMMENT 'Command Type( LO : 로그조회, SO:Scale out, SI:Scale In, SU:Scale UP, SD:Scale Down )',
  `host_ip` varchar(32) NOT NULL COMMENT '대상 IP',
  `target` char(1) DEFAULT NULL COMMENT '대상 타겟( F: file, D:Docker, K:Kubernetes,M:Mesos )',
  `filename` varchar(512) DEFAULT NULL COMMENT '대상 파일( full path + filename )',
  `gaguage` varchar(512) DEFAULT NULL COMMENT 'Resource( Memory : byte )',
  `container_id` varchar(512) DEFAULT NULL COMMENT '대상 컨테이너 id ( Docker or Mesos or Kubernetes )',
  `run` char(1) DEFAULT '1' COMMENT 'Command Stop or Start ( 1: Start, 0: Stop )',
  `read` char(1) DEFAULT '0' COMMENT 'is Read ( 1: read, 0: not read )',
  `regdt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY `idx` (`idx`),
  UNIQUE KEY `agent_uk` (`host_ip`,`target`,`container_id`,`type`),
  KEY `target` (`type`),
  KEY `host_ip` (`host_ip`),
  KEY `run` (`run`),
  KEY `read` (`read`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nexclipper_command`
--

LOCK TABLES `nexclipper_command` WRITE;
/*!40000 ALTER TABLE `nexclipper_command` DISABLE KEYS */;
/*!40000 ALTER TABLE `nexclipper_command` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nexclipper_incident`
--

-- DROP TABLE IF EXISTS `nexclipper_incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `nexclipper_incident` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `severity` enum('Critical','Warning') NOT NULL DEFAULT 'Critical' COMMENT 'Notification등급( Critical, Warning)',
  `target_system` varchar(32) DEFAULT NULL COMMENT 'Notification 대상 ( ''Host'',''Agent'',''Task'',''Framework'',''Docker'' )',
  `target_ip` varchar(32) DEFAULT NULL COMMENT '발생대상 IP',
  `target` varchar(124) DEFAULT NULL COMMENT '발생 대상( CPU, Memory, Disk, Netowrk, System Error..... )',
  `metric` varchar(512) DEFAULT NULL COMMENT '수행 Metric',
  `condition` varchar(512) DEFAULT NULL COMMENT 'Condition',
  `id` varchar(512) DEFAULT NULL COMMENT 'Service/Task/Node/Framework의 Service ID or IP',
  `status` enum('S','F','P') DEFAULT 'S' COMMENT '상태 (''S'':발생, ''F'':종료, ''P'':예측)',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '시작시간',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT 'Incident: 종료시간, 예측: 발생시간',
  `contents` text NOT NULL COMMENT 'notification 내용',
  `memo` text,
  `check_yn` char(1) NOT NULL DEFAULT 'N',
  `regdt` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`idx`),
  KEY `severity` (`severity`),
  KEY `target_system` (`target_system`),
  KEY `target_ip` (`target_ip`),
  KEY `id` (`id`),
  KEY `status` (`status`),
  KEY `start_time` (`start_time`),
  KEY `finish_time` (`finish_time`),
  KEY `regdt` (`regdt`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nexclipper_incident`
--

--
-- Table structure for table `nexclipper_log`
--

-- DROP TABLE IF EXISTS `nexclipper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `nexclipper_log` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `host_ip` varchar(32) NOT NULL COMMENT '대상 IP',
  `container_id` varchar(512) DEFAULT NULL COMMENT '대상 컨테이너 id',
  `stream` varchar(12) DEFAULT NULL COMMENT 'stdout, stderr',
  `log` text COMMENT 'Log 내용',
  `time` varchar(32) DEFAULT NULL COMMENT 'Log 발생시간',
  `regdt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  KEY `host_ip` (`host_ip`),
  KEY `container_id` (`container_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nexclipper_log`
--

LOCK TABLES `nexclipper_log` WRITE;
/*!40000 ALTER TABLE `nexclipper_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `nexclipper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nexclipper_metric`
--

-- DROP TABLE IF EXISTS `nexclipper_metric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `nexclipper_metric` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `target_system` varchar(64) NOT NULL COMMENT '대상 시스템 ( H: Host, M:Master, D:Docker, P:Process, A: Agent, T : Task )',
  `target` varchar(64) DEFAULT NULL COMMENT '대상( Master, Agent, CPU, Memory, Disk, Porcess, Network )',
  `data_source` varchar(128) DEFAULT 'nexclipper' COMMENT 'influxdb data Source',
  `table` varchar(128) DEFAULT NULL COMMENT 'influxdb table',
  `metric` varchar(512) DEFAULT NULL COMMENT '대상 Metric',
  `alias` varchar(512) DEFAULT NULL,
  `math` varchar(64) DEFAULT NULL COMMENT '수식이 필요한 경우 (수식( divid ( / ), sum, avg.. ))',
  `group_by` varchar(512) DEFAULT NULL,
  `new_engine` char(1) DEFAULT 'N' COMMENT '새로운 엔진 사용여부( 일반적인 처리가 불가능하고 새로운 로직이 필요한 경우 )',
  `regdt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  KEY `target_system` (`target_system`),
  KEY `target` (`target`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='수집 metric';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nexclipper_metric`
--

LOCK TABLES `nexclipper_metric` WRITE;
/*!40000 ALTER TABLE `nexclipper_metric` DISABLE KEYS */;
INSERT INTO `nexclipper_metric` VALUES (1,'Host','CPU','nexclipper','host','cpu_used_percent','cpu used percent',NULL,'host_ip','N','2018-07-02 01:21:17'),(2,'Host','Memory','nexclipper','host','mem_used_percent','memory used percent',NULL,'host_ip','N','2018-07-02 01:22:00'),(5,'Host','Disk','nexclipper','host_disk','used_percent','disk used percent',NULL,'host_ip, mount_name','N','2018-07-02 01:50:41'),(6,'Host','CPU','nexclipper','host','load1,cpu_total','cpu load 1 minutes',NULL,'host_ip','Y','2018-07-02 01:53:06'),(7,'Host','CPU','nexclipper','host','load5,cpu_total','cpu load 5 minutes',NULL,'host_ip','Y','2018-07-02 01:54:08'),(8,'Host','CPU','nexclipper','host','load15,cpu_total','cpu load 15 minutes',NULL,'host_ip','Y','2018-07-02 01:54:29'),(9,'Host','CPU','nexclipper','host','cpu_wait_percent','cpu iowait percent',NULL,'host_ip','N','2018-07-02 02:10:06'),(10,'Docker','CPU','nexclipper','docker_container','cpu_used_percent','cpu used percent',NULL,'task_id','N','2018-07-02 02:13:30'),(11,'Docker','Memory','nexclipper','docker_container','mem_used_percent','memory used percent',NULL,'task_id','N','2018-07-02 02:15:03'),(12,'Docker','Network','nexclipper','docker_network','rx_dropped','rx dropped  packet',NULL,'task_id','N','2018-07-02 02:20:46'),(13,'Docker','Network','nexclipper','docker_network','tx_dropped','tx dropped  packet',NULL,'task_id','N','2018-07-02 02:20:55'),(14,'Docker','Network','nexclipper','docker_network','rx_errors','rx errors  packet',NULL,'task_id','N','2018-07-02 02:21:34'),(15,'Docker','Network','nexclipper','docker_network','tx_errors','tx errors  packet',NULL,'task_id','N','2018-07-02 02:21:55'),(16,'Host','Network','nexclipper','host_net','rxdropped','rx dropped  packet',NULL,'host_ip, interface','N','2018-07-02 02:30:14'),(17,'Host','Network','nexclipper','host_net','txdropped','tx dropped  packet',NULL,'host_ip, interface','N','2018-07-02 02:32:01'),(18,'Host','Network','nexclipper','host_net','rxerrors','rx errors packet',NULL,'host_ip, interface','N','2018-07-02 02:32:32'),(19,'Host','Network','nexclipper','host_net','txerrors','tx errors packet',NULL,'host_ip, interface','N','2018-07-02 02:33:19'),(20,'Host','Memory','nexclipper','host','swap_used_percent','swap memory used percent',NULL,'host_ip','N','2018-07-05 02:17:07'),(21,'Host','CPU','nexclipper','host','cpu_idle_percent','cpu idle percent',NULL,'host_ip','N','2018-07-05 02:20:15'),(22,'Host','Network','nexclipper','host_net','rxbyte','inbound bytes',NULL,'host_ip, interface','Y','2018-07-10 00:33:45'),(23,'Host','Network','nexclipper','host_net','txbyte','outbound bytes',NULL,'host_ip, interface','Y','2018-07-10 00:34:44'),(24,'Docker','Network','nexclipper','docker_network','rx_bytes','inbound bytes',NULL,'host_ip,task_id','Y','2018-07-10 00:35:37'),(25,'Docker','Network','nexclipper','docker_network','tx_bytes','outbound bytes',NULL,'host_ip,task_id','Y','2018-07-10 00:36:06'),(26,'Host','Process','nexclipper','host','proc_stopped','stopped process count',NULL,'host_ip','N','2018-07-10 00:39:21'),(27,'Host','Process','nexclipper','host','proc_zombie','zombie process count',NULL,'host_ip','N','2018-07-10 00:39:43'),(28,'POD','CPU','nexclipper','k8s_pod','cpu_used_percent','cpu used percent',NULL,'node_ip,pod','N','2018-09-27 08:21:55'),(29,'POD','Memory','nexclipper','k8s_pod','mem_used_percent','memory used percent',NULL,'node_ip,pod','N','2018-09-27 08:22:05'),(30,'Cluster','CPU','nexclipper','k8s_cluster','cpu_used_percent','cpu used percent',NULL,NULL,'N','2018-09-27 08:51:23'),(31,'Cluster','Memory','nexclipper','k8s_cluster','mem_used_percent','memory used percent',NULL,NULL,'N','2018-09-27 08:51:47');
/*!40000 ALTER TABLE `nexclipper_metric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nexclipper_rule`
--

-- DROP TABLE IF EXISTS `nexclipper_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `nexclipper_rule` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `target_system` varchar(64) DEFAULT NULL COMMENT '대상 시스템 ( H: Host, M:Master, D:Docker, P:Process, A: Agent, T : Task )',
  `target` varchar(64) DEFAULT NULL COMMENT '대상',
  `severity` varchar(64) DEFAULT NULL COMMENT 'Incident Level ( Warnig, Critical )',
  `type` varchar(64) DEFAULT 'metric' COMMENT '종류(Metric, Log )',
  `scale_type` char(1) DEFAULT 'U' COMMENT 'Scale조건관련( U: Scale UP, D :Scale Down )',
  `data_source` varchar(128) DEFAULT 'nexclipper' COMMENT 'influxdb data source ( DCOS, Telegraf, Mesos...)',
  `table` varchar(128) DEFAULT NULL,
  `metric` varchar(512) DEFAULT NULL COMMENT 'Type이 Metric일경우 Metric 이름. Log일경우 Log file full path ',
  `math` varchar(512) DEFAULT NULL COMMENT '수식( divid ( / ), sum, avg.. )',
  `group_by` varchar(512) DEFAULT '',
  `condition` varchar(512) DEFAULT NULL COMMENT 'Metric조건',
  `message` varchar(1024) DEFAULT NULL COMMENT '기본 Output Text',
  `status` enum('Y','N') DEFAULT 'Y' COMMENT 'Rule사용 여부',
  `new_engine` enum('Y','N') DEFAULT 'N' COMMENT '새로운 엔진 사용여부',
  `notify` varchar(128) DEFAULT 'email' COMMENT 'notify ( all, email, slack, no )',
  `slack_token` varchar(128) DEFAULT NULL,
  `slack_channel` varchar(128) DEFAULT NULL,
  `regdt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY `idx` (`idx`),
  KEY `target_system` (`target_system`),
  KEY `target` (`target`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nexclipper_rule`
--

LOCK TABLES `nexclipper_rule` WRITE;
/*!40000 ALTER TABLE `nexclipper_rule` DISABLE KEYS */;
INSERT INTO `nexclipper_rule` VALUES (1,'Host','Disk','Critical','metric','U','nexclipper','host_disk','used_percent',NULL,'host_ip,mount_name','>97','[%s] Free disk space less than 3%% ','Y','N','email',NULL,NULL,'2018-01-29 01:12:05'),(2,'Host','CPU','Critical','metric','U','nexclipper','host','cpu_used_percent',NULL,'host_ip','>95 and >5m','[%s] The cpu usage has been being exceed 95%% over the last 5 minutes','Y','N','email',NULL,NULL,'2018-02-19 05:04:39'),(3,'Host','Memory','Critical','metric','U','nexclipper','host','mem_used_percent',NULL,'host_ip','>95 and >10m','[%s] The memory usage has been being exceed 95%% over the last 10 minutes','Y','N','email',NULL,NULL,'2018-02-19 07:47:46'),(4,'Host','Memory','Critical','metric','U','nexclipper','host','swap_used_percent',NULL,'host_ip','>95 and >10m','[%s] The swap memory usage has been being exceed 95%% over the last 10 minutes','Y','N','email',NULL,NULL,'2018-02-19 07:50:36'),(6,'Host','CPU','Critical','metric','U','nexclipper','host','cpu_wait_percent',NULL,'host_ip','>50 and >5m','[%s] The average values of I/O Wait for 5 minutes have been being exceed threshold 50%%','Y','N','email',NULL,NULL,'2018-03-12 03:57:57'),(7,'Host','CPU','Critical','metric','U','nexclipper','host','load1,cpu_total',NULL,'host_ip','>2 and > 10m','[%s] The average values of CPU Load1 have been being exceed threshold 2 for 10 minutes','Y','Y','email',NULL,NULL,'2018-03-12 03:58:59'),(8,'Host','CPU','Critical','metric','U','nexclipper','host','load5,cpu_total',NULL,'host_ip','>2 and >10m','[%s] The average values of CPU Load5 have been being exceed threshold 2 for 10 minutes','Y','Y','email',NULL,NULL,'2018-03-12 03:59:33'),(9,'Host','CPU','Critical','metric','U','nexclipper','host','load15,cpu_total',NULL,'host_ip','>2 and >10m','[%s] The average values of CPU Load15 have been being exceed threshold 2 for 10 minutes','Y','Y','email',NULL,NULL,'2018-03-12 03:59:57'),(10,'Docker','CPU','Critical','metric','U','nexclipper','docker_container','cpu_used_percent',NULL,'host_ip,task_id','>95 and > 5m','[%s] The cpu usage has been being exceed 95%% over the last 5 minutes','Y','N','email',NULL,NULL,'2018-03-12 04:27:07'),(11,'Docker','Memory','Critical','metric','U','nexclipper','docker_container','mem_used_percent',NULL,'host_ip,task_id','>95 and >10m','[%s] The average values of memory usage for 10 minutes have been being exceed threshold 95%%','Y','N','email',NULL,NULL,'2018-03-12 04:28:20');
/*!40000 ALTER TABLE `nexclipper_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nexclipper_user`
--

-- DROP TABLE IF EXISTS `nexclipper_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `nexclipper_user` (
  `user_id` varchar(64) NOT NULL COMMENT 'email형태의 login id',
  `user_name` varchar(124) DEFAULT NULL COMMENT '사용자명',
  `user_passwd` varchar(128) NOT NULL COMMENT '비밀번호',
  `upper_user_id` varchar(64) NOT NULL DEFAULT '1' COMMENT '사용자 생성 ID. 최초 생성자의 기본값은 1',
  `company_name` varchar(64) DEFAULT NULL COMMENT '회사명',
  `dept_name` varchar(64) DEFAULT NULL COMMENT '부서명',
  `phone_num` varchar(32) DEFAULT NULL COMMENT '연락처',
  `access_ip` varchar(32) DEFAULT NULL COMMENT 'access ip',
  `access_date` timestamp NULL DEFAULT NULL COMMENT '접속 시간',
  `user_state` int(16) DEFAULT '1' COMMENT '1:active, 0:inactive, -1:delete',
  `regdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `updtdate` timestamp NULL DEFAULT NULL COMMENT '수정일',
  UNIQUE KEY `user_id` (`user_id`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nexclipper_user`
--

LOCK TABLES `nexclipper_user` WRITE;
/*!40000 ALTER TABLE `nexclipper_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `nexclipper_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-19  5:47:30
