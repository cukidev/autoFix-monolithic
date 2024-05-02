-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: tingeso
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bonus`
--

DROP TABLE IF EXISTS `bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bonus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `vehicle_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vehicle_id` (`vehicle_id`),
  CONSTRAINT `fk_vehicle_id` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id_vehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonus`
--

LOCK TABLES `bonus` WRITE;
/*!40000 ALTER TABLE `bonus` DISABLE KEYS */;
/*!40000 ALTER TABLE `bonus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair`
--

DROP TABLE IF EXISTS `repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair` (
  `id_repair` bigint NOT NULL AUTO_INCREMENT,
  `id_repair_prices` bigint NOT NULL,
  `pickup_date` date DEFAULT NULL,
  `pickup_time` time(6) DEFAULT NULL,
  `entry_date` date NOT NULL,
  `entry_time` time(6) NOT NULL,
  `exit_date` datetime(6) DEFAULT NULL,
  `exit_time` datetime(6) DEFAULT NULL,
  `repair_cost` decimal(38,2) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `repair_price_id` bigint NOT NULL,
  `vehicle_id` bigint NOT NULL,
  `adjusted_price` decimal(38,2) DEFAULT NULL,
  `base_price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id_repair`),
  KEY `id_repair_prices` (`id_repair_prices`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `FK25wvq91l3ho67pmdety9cvdn4` (`repair_price_id`),
  CONSTRAINT `FK25wvq91l3ho67pmdety9cvdn4` FOREIGN KEY (`repair_price_id`) REFERENCES `repair_prices` (`id_repair_prices`),
  CONSTRAINT `repair_ibfk_1` FOREIGN KEY (`id_repair_prices`) REFERENCES `repair_prices` (`id_repair_prices`),
  CONSTRAINT `repair_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id_vehicle`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair`
--

LOCK TABLES `repair` WRITE;
/*!40000 ALTER TABLE `repair` DISABLE KEYS */;
INSERT INTO `repair` VALUES (28,1,NULL,NULL,'2023-01-10','08:00:00.000000','2023-01-10 00:00:00.000000','2024-05-01 12:00:00.000000',120000.00,'Pendiente',1,1,118000.00,120000.00),(29,2,NULL,NULL,'2023-01-12','09:00:00.000000','2023-01-12 00:00:00.000000','2024-05-01 11:00:00.000000',130000.00,'Completado',2,2,129000.00,130000.00),(30,3,NULL,NULL,'2023-01-15','10:00:00.000000','2023-01-15 00:00:00.000000','2024-05-01 15:00:00.000000',350000.00,'Cancelado',3,3,345000.00,350000.00);
/*!40000 ALTER TABLE `repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_prices`
--

DROP TABLE IF EXISTS `repair_prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_prices` (
  `id_repair_prices` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `gasoline_price` int NOT NULL,
  `diesel_price` int NOT NULL,
  `hybrid_price` int NOT NULL,
  `electric_price` int NOT NULL,
  PRIMARY KEY (`id_repair_prices`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_prices`
--

LOCK TABLES `repair_prices` WRITE;
/*!40000 ALTER TABLE `repair_prices` DISABLE KEYS */;
INSERT INTO `repair_prices` VALUES (1,'Reparación del Sistema de Freno',120000,120000,180000,220000),(2,'Servicio del Sistema de Refrigeración',130000,130000,190000,230000),(3,'Reparaciones del Motor',350000,450000,700000,800000),(4,'Reparaciones de la Transmisión',210000,210000,300000,300000),(5,'Reparación del Sistema Eléctrico',150000,150000,200000,250000),(6,'Reparaciones del Sistema de Escape',100000,120000,450000,0),(7,'Reparación de Neumáticos y Ruedas',100000,100000,100000,100000),(8,'Reparaciones de la Suspensión y la Dirección',180000,180000,210000,250000),(9,'Reparación del Sistema de Aire Acondicionado y Calefacción',150000,150000,180000,180000),(10,'Reparaciones del Sistema de Combustible',130000,140000,220000,0),(11,'Reparación y Reemplazo del Parabrisas y Cristales',80000,80000,80000,80000);
/*!40000 ALTER TABLE `repair_prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surcharge`
--

DROP TABLE IF EXISTS `surcharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surcharge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `applicable_type` varchar(255) DEFAULT NULL,
  `surcharge_type` enum('MILEAGE','VEHICLE_AGE','DELAY_PICKUP') NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `surcharge_chk_1` CHECK ((`surcharge_type` in (_utf8mb4'MILEAGE',_utf8mb4'VEHICLE_AGE',_utf8mb4'PICKUP_DELAY')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surcharge`
--

LOCK TABLES `surcharge` WRITE;
/*!40000 ALTER TABLE `surcharge` DISABLE KEYS */;
/*!40000 ALTER TABLE `surcharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `id_vehicle` bigint NOT NULL AUTO_INCREMENT,
  `license_plate` varchar(45) NOT NULL COMMENT 'patente',
  `brand` varchar(255) NOT NULL COMMENT 'marca',
  `model` varchar(255) NOT NULL COMMENT 'modelo',
  `v_type` varchar(255) NOT NULL COMMENT 'tipo (Sedan, Hatchback, SUV, Pickup, etc)',
  `year_of_manufacture` int NOT NULL COMMENT 'año de fabricación',
  `engine_type` varchar(255) NOT NULL COMMENT 'tipo de motor',
  `seats` int NOT NULL COMMENT 'cantidad de asientos',
  `mileage` int DEFAULT NULL,
  PRIMARY KEY (`id_vehicle`),
  UNIQUE KEY `vehicle_pk` (`license_plate`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'KBJR82','HAVAL','H6','SUV',2018,'Gasolina',5,12000),(2,'CZDW21','Chevrolet','Spark','Pickup',2024,'Híbrido',5,9000),(3,'KBJR81','Kia','Serato','SUV',2022,'Eléctrico',5,12000),(4,'GGXD69','Mercedes','Araña','Hatchback',2024,'Híbrido',5,9000);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-02 11:56:03
