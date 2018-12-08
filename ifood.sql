CREATE DATABASE  IF NOT EXISTS `vehiclerouting` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `vehiclerouting`;

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `restaurant_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `pickup` varchar(45) DEFAULT NULL,
  `delivery` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `client_idx` (`client_id`),
  KEY `restaurant_idx` (`restaurant_id`),
  CONSTRAINT `client` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

