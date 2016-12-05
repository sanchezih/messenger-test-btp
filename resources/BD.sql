-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.16-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para messenger
DROP DATABASE IF EXISTS `messenger`;
CREATE DATABASE IF NOT EXISTS `messenger` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `messenger`;

-- Volcando estructura para tabla messenger.comments
DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(250) NOT NULL DEFAULT '0',
  `created` date DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla messenger.comments: ~2 rows (aproximadamente)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`id`, `message`, `created`, `author`) VALUES
	(1, 'holi', '2016-11-30', 'sofi'),
	(2, 'hola', '2016-11-30', 'jesi');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Volcando estructura para tabla messenger.messages
DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla messenger.messages: ~0 rows (aproximadamente)
DELETE FROM `messages`;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`id`, `message`, `created`, `author`) VALUES
	(1, 'un mensajito :D', '2016-12-01', 'sofi');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;

-- Volcando estructura para tabla messenger.profiles
DROP TABLE IF EXISTS `profiles`;
CREATE TABLE IF NOT EXISTS `profiles` (
  `profileName` varchar(50) NOT NULL,
  `firstName` varchar(50) DEFAULT '0',
  `lastName` varchar(50) DEFAULT '0',
  `created` date DEFAULT NULL,
  PRIMARY KEY (`profileName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla messenger.profiles: ~3 rows (aproximadamente)
DELETE FROM `profiles`;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` (`profileName`, `firstName`, `lastName`, `created`) VALUES
	('melisaaguilera', 'Melisa', 'Aguilera', '2016-12-05'),
	('sheilamachado', 'Sheila', 'Machado', '2016-12-05'),
	('sofiafeijoo', 'Sofia', 'Feijoo', '2016-12-05');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
