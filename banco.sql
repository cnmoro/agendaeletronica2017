create database agendaeletronica;

use agendaeletronica;



CREATE TABLE `agendaeletronica`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `endereço` VARCHAR(255) NOT NULL,
  `telfixo` DOUBLE NOT NULL,
  `celular` DOUBLE NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);