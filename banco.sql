create database agendaeletronica;
use agendaeletronica;

CREATE TABLE `agendaeletronica`.`contato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `endereco` VARCHAR(255) NOT NULL,
  `telfixo` VARCHAR(255),
  `celular` VARCHAR(255),
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);
