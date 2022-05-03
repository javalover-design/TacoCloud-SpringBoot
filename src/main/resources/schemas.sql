
CREATE DATABASE Taco;
DROP TABLE IF EXISTS `Ingredient`;

CREATE TABLE `Ingredient` (
  `id` varchar(4) NOT NULL,
  `name` varchar(25) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `Taco`;

CREATE TABLE `Taco` (
  `id` bigint NOT NULL,
  `name` varchar(50) NOT NULL,
  `createdAt` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `Taco_Ingredients`;

CREATE TABLE `Taco_Ingredients` (
  `taco` bigint NOT NULL,
  `ingredient` varchar(4) NOT NULL,
  KEY `taco` (`taco`),
  KEY `ingredient` (`ingredient`),
  CONSTRAINT `Taco_Ingredients_ibfk_1` FOREIGN KEY (`taco`) REFERENCES `Taco` (`id`),
  CONSTRAINT `Taco_Ingredients_ibfk_2` FOREIGN KEY (`ingredient`) REFERENCES `Ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `Taco_Order`;

CREATE TABLE `Taco_Order` (
  `id` bigint NOT NULL,
  `deliveryName` varchar(50) NOT NULL,
  `deliveryStreet` varchar(50) NOT NULL,
  `deliverCity` varchar(50) NOT NULL,
  `deliverState` varchar(2) NOT NULL,
  `deliverZip` varchar(10) NOT NULL,
  `ccNumber` varchar(16) NOT NULL,
  `ccExpiration` varchar(5) NOT NULL,
  `ccCVV` varchar(3) NOT NULL,
  `placedAt` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Taco_Order_Tacos`;

CREATE TABLE `Taco_Order_Tacos` (
  `tacoOrder` bigint NOT NULL,
  `taco` bigint NOT NULL,
  KEY `tacoOrder` (`tacoOrder`),
  KEY `taco` (`taco`),
  CONSTRAINT `Taco_Order_Tacos_ibfk_1` FOREIGN KEY (`tacoOrder`) REFERENCES `Taco_Order` (`id`),
  CONSTRAINT `Taco_Order_Tacos_ibfk_2` FOREIGN KEY (`taco`) REFERENCES `Taco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

