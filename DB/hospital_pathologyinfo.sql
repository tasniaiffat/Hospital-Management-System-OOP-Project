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
-- Table structure for table `pathologyinfo`
--

DROP TABLE IF EXISTS `pathologyinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pathologyinfo` (
  `ID` varchar(10) NOT NULL,
  `Test name` varchar(45) NOT NULL,
  `Test Description` varchar(65) NOT NULL,
  `Price` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pathologyinfo`
--

LOCK TABLES `pathologyinfo` WRITE;
/*!40000 ALTER TABLE `pathologyinfo` DISABLE KEYS */;
INSERT INTO `pathologyinfo` VALUES ('LAB0000001','Jaundice','Holud Manush','2000.0'),('LAB0000004','DepressionMeter','It calculates how depressed you are.','10000.0'),('LAB0000005','Antinuclear antibody','Diagnoses lupus','600.0'),('LAB0000006','Thyroid Function Test','Checks the thyroid gland ','1000.0'),('LAB0000007','Complete Blood Count(CBC)','Checks for anemia or blood loss','500.0'),('LAB0000008','Urinalysis','Checks the acidity of Urine(PH)','400.0'),('LAB0000009','Flu Test','Used to determine flu llike symptoms','100.0'),('LAB0000010','ESR(Sedimentation Rate)','To follow the course of joint or muscle pain','500.0');
/*!40000 ALTER TABLE `pathologyinfo` ENABLE KEYS */;
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
