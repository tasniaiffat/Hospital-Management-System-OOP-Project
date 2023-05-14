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
-- Table structure for table `patientinfo`
--

DROP TABLE IF EXISTS `patientinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patientinfo` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Contact No` varchar(14) NOT NULL,
  `Email Address` varchar(45) NOT NULL,
  `Address` varchar(60) NOT NULL,
  `Date of Birth` varchar(45) NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Medical History` varchar(100) NOT NULL,
  `Current Treatment` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientinfo`
--

LOCK TABLES `patientinfo` WRITE;
/*!40000 ALTER TABLE `patientinfo` DISABLE KEYS */;
INSERT INTO `patientinfo` VALUES ('PAT0000002','Tasnia','01796304073','tasnia@yahoo.com','Japan garden city','2001-05-04','Female','Headache','Headache, Consulted Dr Md Zahidul Haque (Medicine) on 2023-05-14, Consulted Ahmed Nesar Tahsin Choudhury (Gynaecology) on 2023-05-14, Consulted Nirjhor Shingha (Endocrinologist) on 2023-05-14, Consulted Dr Tanzim (ENT) on 2023-05-14'),('PAT0000003','Mahmudul Hasan Yeamim','19671299','yeamim@yahoo.com','Mirpur','1997-05-02','Male','Dust allergy','Stomach pain'),('PAT0000004','Fardin Faiyaz','28971319','fardin@hmail.com','Dhaka','2000-08-28','Male','Severe Depression','Pani Pora, Consulted Dr Md Zahidul Haque (Medicine) on 2023-05-14, Consulted Ahmed Nesar Tahsin Choudhury (Gynaecology) on 2023-05-14, Consulted Shariful Islam rayhan (Child specialist) on 2023-05-14'),('PAT0000005','Samiya Sultana Riya','+012345678910','shashuri@gmail.com','Shitalakkha','2023-05-14','Female','Peramoy Jibon','N/A'),('PAT0000006','waki','87836863','dghgdhasgd','shdghsvd','2023-05-10','Male','mentally ill','Intense gameplay, Consulted Dr. Tasnia (Neurosurgeon) on 2023-05-14'),('PAT0000007','df','1478','sdsd','xccdds','2023-05-12','Male','sds','Bloop'),('PAT0000008','Shafin','11242467','shafin@gmail.com','Dhaka','2023-05-02','Male','Being sad','Antidepressant, Consulted Dr Tanzim (ENT) on 2023-05-14'),('PAT0000009','admin','87441928','gasu','dhaka','2023-05-01','Male','jbvdskd','scsdv, Consulted Dr Yeamim (Neurology) on 2023-05-14');
/*!40000 ALTER TABLE `patientinfo` ENABLE KEYS */;
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
