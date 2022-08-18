-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: i7a708.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `chatroom_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=551 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (48,11,1,'chat'),(49,11,1,'helllo'),(68,11,1,'hello'),(69,32,1,'hello'),(70,32,1,'he'),(71,32,1,'he'),(72,32,1,'he'),(73,32,1,'7'),(74,32,1,'7'),(75,32,1,'7'),(76,32,1,'123'),(77,32,1,'as'),(87,32,7,'i\'m user7'),(88,32,7,'user7 chatting'),(107,33,1,'asd'),(143,32,1,'as'),(144,32,1,'as'),(303,608,15,'asd'),(304,608,15,'asd'),(305,608,15,'asd'),(306,607,1,'as'),(307,607,1,'as'),(308,607,1,'as'),(309,607,1,'as'),(310,607,1,'as'),(313,610,1,'chat'),(314,610,1,'chat'),(383,380,1,'hello'),(384,380,1,'ho'),(385,380,1,'nice to meet you'),(386,651,1,'hello'),(387,651,1,'안녕하세요'),(388,651,1,'반갑습니다'),(389,NULL,15,'hello'),(390,NULL,15,'im hyun'),(391,NULL,15,'hello'),(392,NULL,15,'hi'),(393,NULL,15,'asdf'),(394,NULL,15,'asdf'),(395,NULL,15,'sadf'),(396,NULL,15,'sadf'),(397,NULL,15,'sadf'),(398,NULL,15,'asdf'),(399,651,15,'안녕하세요'),(400,651,15,'!'),(401,651,15,'반가워요'),(402,NULL,1,'as'),(403,NULL,1,'as'),(409,NULL,9,'as'),(410,NULL,9,'as'),(411,NULL,9,'as'),(412,NULL,9,'as'),(413,NULL,9,'as'),(414,NULL,9,'as'),(415,NULL,9,'as'),(416,649,10,'안녕하세요'),(417,649,11,'안녕하세요'),(418,NULL,10,'hi'),(419,NULL,10,'hi'),(420,658,11,'hello'),(421,NULL,10,'hhdsfsdf'),(427,658,10,'hello'),(428,658,11,'hi'),(429,658,10,'1234'),(430,658,11,'1234'),(431,610,1,'as'),(432,610,1,'as'),(433,610,1,'as'),(434,610,1,'as'),(451,651,1,'안녕하세요!!'),(465,789,18,'안녕하세요ㅎㅎ'),(466,789,18,'잠은 잘 주무셨나요?'),(467,789,1,'반가워요'),(468,789,1,'ㅎㅎ'),(486,1034,1,'hello'),(487,1034,1,'hi'),(488,1034,9,'hi'),(489,1034,9,'nice to meet you'),(490,1034,1,'hi'),(491,1034,1,'noce to meet you'),(492,1045,45,'안녕하세요'),(493,1045,45,'백승훈입니다'),(494,1045,45,'반가워요'),(495,1045,46,'네 안녕하세요'),(496,1045,46,'female'),(497,1045,46,'test'),(498,1045,46,'user'),(499,1045,45,'hi'),(500,1045,45,'helo'),(501,1045,45,'hello'),(502,1045,45,'hei'),(503,1045,45,'hi'),(504,1120,45,'asdf'),(505,1120,45,'as'),(506,1120,45,'df'),(507,1120,45,'asdf'),(508,1120,45,'asdf'),(509,1120,45,'asd'),(510,1120,45,'fa'),(511,1120,45,'sf'),(512,1120,45,'asd'),(513,1120,45,'f'),(514,1120,45,'asdf'),(515,1120,45,'as'),(516,1120,45,'fa'),(517,1120,45,'sfd'),(518,1120,45,'as'),(519,1120,45,'df'),(520,1120,45,'asd'),(521,1120,45,'fas'),(522,1120,45,'f'),(550,1120,45,'hi');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-18 15:15:54
