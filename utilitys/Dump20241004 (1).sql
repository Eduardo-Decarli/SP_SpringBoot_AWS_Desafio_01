CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library`;
-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.2

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
-- Table structure for table `Author`
--

DROP TABLE IF EXISTS `Author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Author` (
  `idAuthor` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `nationality` varchar(255) NOT NULL,
  `biography` text,
  PRIMARY KEY (`idAuthor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Author`
--

LOCK TABLES `Author` WRITE;
/*!40000 ALTER TABLE `Author` DISABLE KEYS */;
INSERT INTO `Author` VALUES (1,'Raymond','2024-04-20','Portuguese','Teste'),(2,'Raymond','2024-04-20','Portuguese','Teste'),(3,'Raymond','2024-04-20','Portuguese','Teste'),(4,'Raymond','2024-04-20','Portuguese','Teste'),(5,'Raymond','2024-04-20','Portuguese','Teste'),(6,'Raymondes','2024-04-20','Portuguese','Teste'),(7,'renan','2001-01-10','brazilian','teste'),(8,'Robert','1999-04-10','Brazilian','teste4'),(9,'Jordan','1999-10-20','brazilian','teste123'),(10,'Mr Robert','2021-12-10','US','teste'),(11,'Paulo','2014-10-10','br','testeasdqwe');
/*!40000 ALTER TABLE `Author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Books`
--

DROP TABLE IF EXISTS `Books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Books` (
  `idBooks` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` int DEFAULT NULL,
  `datePublication` date NOT NULL,
  `isbn` bigint NOT NULL,
  `gender` varchar(100) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`idBooks`),
  KEY `author_id` (`author`),
  CONSTRAINT `Books_ibfk_1` FOREIGN KEY (`author`) REFERENCES `Author` (`idAuthor`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books`
--

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;
INSERT INTO `Books` VALUES (2,'harry pother',2,'2024-10-20',58712460,'Action',84),(3,'Teste',3,'2024-10-20',4563214,'teste',54),(4,'Supernova',5,'2021-04-10',84579630,'adventure',74),(5,'amanhecer',10,'2010-10-10',15478523,'adventure',13),(6,'i am ',2,'2001-04-10',78858056,'action',0),(7,'number one',2,'1999-06-10',4587521600000,'Action',0),(8,'one year',2,'2001-02-10',1542369800000,'mistery',-2),(9,'Dracula',2,'2020-10-10',1234568000000,'Horror',4);
/*!40000 ALTER TABLE `Books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Loan`
--

DROP TABLE IF EXISTS `Loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan` (
  `idLoan` int NOT NULL AUTO_INCREMENT,
  `loanBooks` int NOT NULL,
  `memberId` int NOT NULL,
  `dateLoan` date NOT NULL,
  `returnDate` date NOT NULL,
  `stateLoan` enum('ACTIVE','COMPLETE','LATE') NOT NULL,
  `taxFine` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idLoan`),
  KEY `loanBooks` (`loanBooks`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `Loan_ibfk_1` FOREIGN KEY (`loanBooks`) REFERENCES `Books` (`idBooks`),
  CONSTRAINT `Loan_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `Member` (`idMember`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan`
--

LOCK TABLES `Loan` WRITE;
/*!40000 ALTER TABLE `Loan` DISABLE KEYS */;
INSERT INTO `Loan` VALUES (1,4,1,'2024-10-10','2025-10-10','ACTIVE',10.50),(2,2,1,'2024-10-10','2024-10-10','COMPLETE',10.50),(3,6,1,'2024-10-03','2024-10-04','COMPLETE',10.50),(4,6,1,'2010-10-10','2010-10-11','ACTIVE',15.40),(5,5,1,'2010-10-10','2010-10-10','ACTIVE',15.00),(6,8,1,'2024-10-10','2024-10-10','COMPLETE',15.00),(7,9,1,'2024-10-10','2024-10-16','COMPLETE',0.00),(8,8,1,'2010-10-10','2010-10-19','LATE',0.00),(10,7,1,'2020-10-10','2020-10-10','COMPLETE',0.00),(11,7,1,'2010-10-10','2010-10-10','COMPLETE',0.00),(12,7,1,'2010-10-10','2010-10-10','ACTIVE',0.00),(13,8,1,'2010-10-10','2010-10-10','ACTIVE',0.00);
/*!40000 ALTER TABLE `Loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Member` (
  `idMember` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phoneNumber` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `dateAssociation` date NOT NULL,
  PRIMARY KEY (`idMember`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,'Eduardo','florianopolis/sc',4900000000,'decarliedu@gmail.com','2024-10-10');
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-04 17:01:22
