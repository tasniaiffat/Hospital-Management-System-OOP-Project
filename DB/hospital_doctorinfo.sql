-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hospital
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `doctorinfo`
--

DROP TABLE IF EXISTS `doctorinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctorinfo` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Speciality` varchar(45) NOT NULL,
  `Contact No` varchar(14) NOT NULL,
  `Email Address` varchar(45) NOT NULL,
  `Address` varchar(60) NOT NULL,
  `Date of Birth` varchar(45) NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Qualification` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorinfo`
--

LOCK TABLES `doctorinfo` WRITE;
/*!40000 ALTER TABLE `doctorinfo` DISABLE KEYS */;
INSERT INTO `doctorinfo` VALUES ('DOC0000002','Dr Md Zahidul Haque','Medicine','01799427270','Jahidul44@gmail.com','Bangladesh Specialized Hospital','1971-05-17','Male','MBBS, FCPS'),('DOC0000003','Ahmed Nesar Tahsin Choudhury','Gynaecology','01914567929','nesar13@gmail.com','Chankharpul','2023-01-31','Male','Bachelor\'s in CSE'),('DOC0000004','Nirjhor Shingha','Endocrinologist','123456789','nirjhor@gmail.com','Dhaka','1982-09-05','Male','BCS'),('DOC0000005','Dr Yeamim','Neurology','912783908749','yeamimbrain@gmail.com','Dhaka','2001-05-02','Female','HSC Pass'),('DOC0000006','Dr Tanzim','ENT','218970312','tanzim@gmail.com','Babor Road','2001-06-12','Female','MBBS'),('DOC0000007','Shariful Islam rayhan','Child specialist','913702892134','rayhan@gmail.com','Banasree','2001-07-10','Male','MBBS'),('DOC0000008','Dr. Tasnia','Neurosurgeon','132343566','tasnia@gmail.com','Dhaka','2023-10-04','Male','MBBS, FCPS, PhD'),('DOC0000009','Dr. Waki','Dentist','1323545675','waki@gmail.com','Dhaka','2023-02-21','Male','MBBS');
/*!40000 ALTER TABLE `doctorinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-14 15:07:18
