-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: parking
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `idtarifa` int NOT NULL,
  `localidadId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  KEY `fkTarifa_idx` (`idtarifa`),
  KEY `fkLocalidad_idx` (`localidadId`),
  CONSTRAINT `fkLocalidad` FOREIGN KEY (`localidadId`) REFERENCES `localidad` (`id`),
  CONSTRAINT `fkTarifa` FOREIGN KEY (`idtarifa`) REFERENCES `tarifa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (0,'asdf','asdf','123','34',2,4),(31,'afsd','asdf','123','asd',1,1),(131,'Illu','prueba@prueba.prueba','123123123','20953676D',3,40),(682,'dfedx','dfcg','123','20953676A',3,11),(731,'ctgcg','tdrfcft','336534','wegR',3,6);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_vehiculo`
--

DROP TABLE IF EXISTS `cliente_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_vehiculo` (
  `cliente_id` int NOT NULL,
  `vehiculo_id` int NOT NULL,
  PRIMARY KEY (`cliente_id`,`vehiculo_id`),
  KEY `vehiculo_fk` (`vehiculo_id`),
  CONSTRAINT `cliente_vehiculo_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehiculo_fk` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_vehiculo`
--

LOCK TABLES `cliente_vehiculo` WRITE;
/*!40000 ALTER TABLE `cliente_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localidad` (
  `id` int NOT NULL,
  `codpostal` int NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `codpostal_UNIQUE` (`codpostal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,14900,'Córdoba','Lucena'),(2,2001,'Albacete','Albacete'),(3,3001,'Alicante','Alicante'),(4,4001,'Almería','Almería'),(5,5001,'Ávila','Ávila'),(6,6001,'Badajoz','Badajoz'),(7,7001,'Baleares','Palma de Mallorca'),(8,8001,'Barcelona','Barcelona'),(9,9001,'Burgos','Burgos'),(10,10001,'Cáceres','Cáceres'),(11,11001,'Cádiz','Cádiz'),(12,12001,'Castellón','Castellón de la Plana'),(13,13001,'Ciudad Real','Ciudad Real'),(14,14001,'Córdoba','Córdoba'),(15,15001,'A Coruña','A Coruña'),(16,16001,'Cuenca','Cuenca'),(17,17001,'Girona','Girona'),(18,18001,'Granada','Granada'),(19,19001,'Guadalajara','Guadalajara'),(20,20001,'Guipúzcoa','San Sebastián'),(21,21001,'Huelva','Huelva'),(22,22001,'Huesca','Huesca'),(23,23001,'Jaén','Jaén'),(24,24001,'León','León'),(25,25001,'Lleida','Lleida'),(26,26001,'La Rioja','Logroño'),(27,27001,'Lugo','Lugo'),(28,28001,'Madrid','Madrid'),(29,29001,'Málaga','Málaga'),(30,30001,'Murcia','Murcia'),(31,31001,'Navarra','Pamplona'),(32,32001,'Ourense','Ourense'),(33,33001,'Asturias','Oviedo'),(34,34001,'Palencia','Palencia'),(35,35001,'Las Palmas','Las Palmas de Gran Canaria'),(36,36001,'Pontevedra','Vigo'),(37,37001,'Salamanca','Salamanca'),(38,38001,'Santa Cruz de Tenerife','Santa Cruz de Tenerife'),(39,39001,'Cantabria','Santander'),(40,40001,'Segovia','Segovia'),(41,41001,'Sevilla','Sevilla'),(42,42001,'Soria','Soria'),(43,43001,'Tarragona','Tarragona'),(44,44001,'Teruel','Teruel'),(45,45001,'Toledo','Toledo'),(46,46001,'Valencia','Valencia'),(47,47001,'Valladolid','Valladolid'),(48,48001,'Vizcaya','Bilbao'),(49,49001,'Zamora','Zamora'),(50,50001,'Zaragoza','Zaragoza'),(51,51001,'Ceuta','Ceuta'),(52,52001,'Melilla','Melilla'),(53,1002,'Álava','Llodio'),(54,2002,'Albacete','Hellín'),(55,3002,'Alicante','Elche'),(56,4002,'Almería','Roquetas de Mar'),(57,5002,'Ávila','Arévalo'),(58,6002,'Badajoz','Mérida'),(59,7002,'Baleares','Ibiza'),(60,8002,'Barcelona','Sabadell'),(61,9002,'Burgos','Aranda de Duero'),(62,10002,'Cáceres','Plasencia'),(63,11002,'Cádiz','Jerez de la Frontera'),(64,12002,'Castellón','Villarreal'),(65,13002,'Ciudad Real','Puertollano'),(66,14970,'Córdoba','Iznájar'),(67,15002,'A Coruña','Santiago de Compostela'),(68,16002,'Cuenca','Tarancón'),(69,17002,'Girona','Figueres'),(70,18002,'Granada','Motril'),(71,19002,'Guadalajara','Azuqueca de Henares'),(72,20002,'Guipúzcoa','Eibar'),(73,21002,'Huelva','Lepe'),(74,22002,'Huesca','Jaca'),(75,23002,'Jaén','Úbeda'),(76,24002,'León','Ponferrada'),(77,25002,'Lleida','Balaguer'),(78,26002,'La Rioja','Calahorra'),(79,27002,'Lugo','Monforte de Lemos'),(80,28002,'Madrid','Alcalá de Henares'),(81,29002,'Málaga','Marbella'),(82,30002,'Murcia','Cartagena'),(83,31002,'Navarra','Tudela'),(84,32002,'Ourense','Verín'),(85,33002,'Asturias','Gijón'),(86,34002,'Palencia','Aguilar de Campoo'),(87,35002,'Las Palmas','Telde'),(88,36002,'Pontevedra','Pontevedra'),(89,37002,'Salamanca','Béjar'),(90,38002,'Santa Cruz de Tenerife','La Laguna'),(91,39002,'Cantabria','Torrelavega'),(92,40002,'Segovia','Cuéllar'),(93,41002,'Sevilla','Dos Hermanas'),(94,42002,'Soria','Almazán'),(95,43002,'Tarragona','Reus'),(96,44002,'Teruel','Alcañiz'),(97,45002,'Toledo','Talavera de la Reina'),(98,46002,'Valencia','Gandía'),(99,47002,'Valladolid','Medina del Campo'),(100,48002,'Vizcaya','Getxo');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_parking`
--

DROP TABLE IF EXISTS `registro_parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_parking` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `hora_entrada` timestamp NOT NULL,
  `hora_salida` timestamp NULL DEFAULT NULL,
  `pagado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `matricula` (`matricula`),
  CONSTRAINT `registro_parking_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `vehiculo` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_parking`
--

LOCK TABLES `registro_parking` WRITE;
/*!40000 ALTER TABLE `registro_parking` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sequence` (
  `SEQ_COUNT` bigint NOT NULL,
  `SEQ_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SEQ_COUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES (930,'SEQ_GEN');
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifa` (
  `id` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `preciomensual` float NOT NULL,
  `preciohora` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES (1,'Básica',0,8),(2,'Premium',30,2),(3,'Definitiva',100,0);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `id` int NOT NULL,
  `matricula` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (881,'1234TTX'),(781,'9247CXD');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-13 14:41:45
