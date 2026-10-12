/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`treninzi` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `treninzi`;



DROP TABLE IF EXISTS `Trener`;

CREATE TABLE `Trener` (
  `TrenerID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`TrenerID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Trener` VALUES 
(1,'Anastasija','Djordjevic','anas', 'anas');



DROP TABLE IF EXISTS `Skola`;

CREATE TABLE `Skola` (
  `SkolaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `StepenStudija` VARCHAR(50) NOT NULL,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`SkolaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Skola`  VALUES
(1,'Osnovne studije', 'Univerzitet u Beogradu'),
(2,'Master studije', 'Univerzitet u Novom Sadu'),
(3,'Doktorske studije', 'Univerzitet u Nisu'),
(4,'Strukovne studije', 'Visoka skola strukovnih studija Subotica'),
(5,'Specijalističke studije', 'Univerzitet u Kragujevcu');



DROP TABLE IF EXISTS `TrenerSkola`;

CREATE TABLE `TrenerSkola` (
  `SkolaID` BIGINT(10) UNSIGNED NOT NULL,
  `TrenerID` BIGINT(10) UNSIGNED NOT NULL,
  `DatumZavrsetka` DATE NOT NULL,
  PRIMARY KEY (`SkolaID`, `TrenerID`),
  CONSTRAINT `fk_skola_id` FOREIGN KEY (`SkolaID`) REFERENCES `Skola` (`SkolaID`),
  CONSTRAINT `fk_trener_id2` FOREIGN KEY (`TrenerID`) REFERENCES `Trener` (`TrenerID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `TrenerSkola` VALUES 
(1,1,'2024-09-15');



DROP TABLE IF EXISTS `FitnesProfil`;

CREATE TABLE `FitnesProfil` (
  `FitnesProfilID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TipGradje` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`FitnesProfilID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `FitnesProfil` VALUES
(1, 'Atletska'),
(2, 'Mršava'),
(3, 'Krupna'),
(4, 'Zdepasta'),
(5, 'Prosečna');


DROP TABLE IF EXISTS `Klijent`;

CREATE TABLE `Klijent` (
  `KlijentID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  `StavPremaTreningu` VARCHAR(30) NOT NULL,
  `FitnesProfilID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`KlijentID`),
  CONSTRAINT `fk_fit_id` FOREIGN KEY (`FitnesProfilID`) REFERENCES `FitnesProfil` (`FitnesProfilID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Klijent` VALUES
(1, 'Marko', 'Jovanović', 'marko.jovanovic@gmail.com', '+381641112223', 'Posvećen',1),
(2, 'Ana', 'Petrović', 'ana.petrovic@gmail.com', '+381601234567', 'Motivisan',2),
(3, 'Nikola', 'Ilić', 'nikola.ilic@gmail.com', '+381638888888', 'Opušten',3),
(4, 'Jelena', 'Kovačević', 'jelena.kovacevic@gmail.com', '+381645554433', 'Nezainteresovan',4),
(5, 'Ivan', 'Stojanović', 'ivan.stojanovic@gmail.com', '+381607778899', 'Redovan',5);



DROP TABLE IF EXISTS `Trening`;

CREATE TABLE `Trening` (
  `TreningID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`TreningID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Trening` (`TreningID`, `Naziv`, `Opis`) VALUES
(1, 'Kardio trening', 'Trening fokusiran na izdržljivost, sagorevanje kalorija i jačanje kardiovaskularnog sistema.'),
(2, 'Trening snage', 'Vežbe sa tegovima i opterećenjem za povećanje mišićne mase i snage.'),
(3, 'HIIT', 'Visoko intenzivni intervalni trening koji brzo sagoreva masti i poboljšava kondiciju.'),
(4, 'Pilates', 'Vežbe za stabilnost, fleksibilnost i kontrolu pokreta sa fokusom na core.'),
(5, 'Joga', 'Trening koji kombinuje fizičke položaje, disanje i meditaciju za poboljšanje fleksibilnosti i mentalnog fokusa.');




DROP TABLE IF EXISTS `Program`;

CREATE TABLE `Program` (
  `ProgramID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumPocetka` DATE NOT NULL,
  `DatumZavrsetka` DATE NOT NULL,
  `Cilj` VARCHAR(200) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `KlijentID` BIGINT(10) UNSIGNED NOT NULL,
  `TrenerID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ProgramID`),
  CONSTRAINT `fk_kli_id` FOREIGN KEY (`KlijentID`) REFERENCES `Klijent` (`KlijentID`),
  CONSTRAINT `fk_tre_id` FOREIGN KEY (`TrenerID`) REFERENCES `Trener` (`TrenerID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT INTO `Program` (`ProgramID`, `DatumPocetka`, `DatumZavrsetka`, `Cilj`, `Cena`, `KlijentID`, `TrenerID`) VALUES
(1, '2025-07-01', '2025-08-01', 'Mršavljenje i povećanje izdržljivosti', 12000.00, 1, 1),
(2, '2025-07-05', '2025-09-05', 'Povećanje mišićne mase i snage', 15000.00, 2, 1),
(3, '2025-06-20', '2025-07-20', 'Rehabilitacija i posturalna korekcija', 10000.00, 3, 1),
(4, '2025-07-10', '2025-08-10', 'Fleksibilnost i smanjenje stresa', 9000.00, 4, 1),
(5, '2025-07-15', '2025-08-15', 'Poboljšanje kondicije kroz HIIT', 11000.00, 5, 1);


DROP TABLE IF EXISTS `StavkaPrograma`;

CREATE TABLE `StavkaPrograma` (
  `ProgramID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Dan` DATE NOT NULL,
  `TreningID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ProgramID`,`Rb`),
  CONSTRAINT `fk_pr_id2` FOREIGN KEY (`ProgramID`) REFERENCES `Program` (`ProgramID`) ON DELETE CASCADE,
  CONSTRAINT `fk_tre_id2` FOREIGN KEY (`TreningID`) REFERENCES `Trening` (`TreningID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `StavkaPrograma` VALUES
(1, 1, '2025-07-01', 1),
(1, 2, '2025-07-03', 2),
(2, 1, '2025-07-05', 3),
(2, 2, '2025-07-08', 2),
(3, 1, '2025-06-20', 4),
(3, 2, '2025-06-22', 5);

INSERT INTO `StavkaPrograma` VALUES
(4, 1, '2025-07-10', 4),
(4, 2, '2025-07-12', 5),
(4, 3, '2025-07-15', 4),
(5, 1, '2025-07-15', 3),
(5, 2, '2025-07-17', 1),
(5, 3, '2025-07-20', 2);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
