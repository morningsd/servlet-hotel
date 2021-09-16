-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hoteldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hoteldb` ;

-- -----------------------------------------------------
-- Schema hoteldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hoteldb` DEFAULT CHARACTER SET utf8 ;
USE `hoteldb` ;

-- -----------------------------------------------------
-- Table `hoteldb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`role` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`account` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`account` (
  `account_id` BIGINT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `passwordSha` VARCHAR(255) NOT NULL,
  `account_role` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_account_role_idx` (`account_role` ASC) VISIBLE,
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`account_role`)
    REFERENCES `hoteldb`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`status` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`type` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`apartments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`apartments` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`apartments` (
  `apartments_id` BIGINT NOT NULL AUTO_INCREMENT,
  `no_rooms` INT NOT NULL,
  `no_adults` INT NOT NULL,
  `no_children` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `image_path` VARCHAR(255) NOT NULL,
  `apartments_account` BIGINT NULL,
  `apartments_status` INT NOT NULL DEFAULT 0,
  `apartments_type` INT NOT NULL,
  PRIMARY KEY (`apartments_id`),
  INDEX `fk_room_account1_idx` (`apartments_account` ASC) VISIBLE,
  INDEX `fk_room_status1_idx` (`apartments_status` ASC) VISIBLE,
  INDEX `fk_room_class1_idx` (`apartments_type` ASC) VISIBLE,
  CONSTRAINT `fk_room_account1`
    FOREIGN KEY (`apartments_account`)
    REFERENCES `hoteldb`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_status1`
    FOREIGN KEY (`apartments_status`)
    REFERENCES `hoteldb`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_room_class1`
    FOREIGN KEY (`apartments_type`)
    REFERENCES `hoteldb`.`type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`state` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`state` (
  `state_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`state_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`bill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`bill` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`bill` (
  `bill_id` BIGINT NOT NULL AUTO_INCREMENT,
  `check_in` DATETIME NOT NULL,
  `check_out` DATETIME NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `bill_account` BIGINT NOT NULL,
  `bill_apartments` BIGINT NOT NULL,
  `bill_state` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`bill_id`),
  INDEX `fk_bill_account1_idx` (`bill_account` ASC) VISIBLE,
  INDEX `fk_bill_room1_idx` (`bill_apartments` ASC) VISIBLE,
  INDEX `fk_bill_state1_idx` (`bill_state` ASC) VISIBLE,
  CONSTRAINT `fk_bill_account1`
    FOREIGN KEY (`bill_account`)
    REFERENCES `hoteldb`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bill_room1`
    FOREIGN KEY (`bill_apartments`)
    REFERENCES `hoteldb`.`apartments` (`apartments_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bill_state1`
    FOREIGN KEY (`bill_state`)
    REFERENCES `hoteldb`.`state` (`state_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hoteldb`.`application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hoteldb`.`application` ;

CREATE TABLE IF NOT EXISTS `hoteldb`.`application` (
  `application_id` BIGINT NOT NULL AUTO_INCREMENT,
  `no_rooms` INT NOT NULL,
  `no_adults` INT NOT NULL,
  `no_children` INT NOT NULL,
  `check_in` DATETIME NOT NULL,
  `check_out` DATETIME NOT NULL,
  `wishes` TEXT NULL,
  `application_type` INT NOT NULL,
  `application_account` BIGINT NOT NULL,
  `application_apartments` BIGINT NULL,
  PRIMARY KEY (`application_id`),
  INDEX `fk_application_type1_idx` (`application_type` ASC) VISIBLE,
  INDEX `fk_application_account1_idx` (`application_account` ASC) VISIBLE,
  INDEX `fk_application_room1_idx` (`application_apartments` ASC) VISIBLE,
  CONSTRAINT `fk_application_type1`
    FOREIGN KEY (`application_type`)
    REFERENCES `hoteldb`.`type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_application_account1`
    FOREIGN KEY (`application_account`)
    REFERENCES `hoteldb`.`account` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_application_room1`
    FOREIGN KEY (`application_apartments`)
    REFERENCES `hoteldb`.`apartments` (`apartments_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `hoteldb`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `hoteldb`;
INSERT INTO `hoteldb`.`role` (`role_id`, `name`) VALUES (1, 'CLIENT');
INSERT INTO `hoteldb`.`role` (`role_id`, `name`) VALUES (2, 'MANAGER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hoteldb`.`account`
-- -----------------------------------------------------
START TRANSACTION;
USE `hoteldb`;
INSERT INTO `hoteldb`.`account` (`account_id`, `fname`, `lname`, `email`, `passwordSha`, `account_role`) VALUES (1, 'client', 'client', 'client@gmail.com', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 1);
INSERT INTO `hoteldb`.`account` (`account_id`, `fname`, `lname`, `email`, `passwordSha`, `account_role`) VALUES (2, 'manager', 'manager', 'manager@gmail.com', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hoteldb`.`status`
-- -----------------------------------------------------
START TRANSACTION;
USE `hoteldb`;
INSERT INTO `hoteldb`.`status` (`status_id`, `name`) VALUES (1, 'FREE');
INSERT INTO `hoteldb`.`status` (`status_id`, `name`) VALUES (2, 'BOOKED');
INSERT INTO `hoteldb`.`status` (`status_id`, `name`) VALUES (3, 'BUSY');
INSERT INTO `hoteldb`.`status` (`status_id`, `name`) VALUES (4, 'INACCESSIBLE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hoteldb`.`type`
-- -----------------------------------------------------
START TRANSACTION;
USE `hoteldb`;
INSERT INTO `hoteldb`.`type` (`type_id`, `name`) VALUES (1, 'ECONOMY');
INSERT INTO `hoteldb`.`type` (`type_id`, `name`) VALUES (2, 'JUNIOR_SUITE');
INSERT INTO `hoteldb`.`type` (`type_id`, `name`) VALUES (3, 'SUITE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hoteldb`.`state`
-- -----------------------------------------------------
START TRANSACTION;
USE `hoteldb`;
INSERT INTO `hoteldb`.`state` (`state_id`, `name`) VALUES (1, 'NEW');
INSERT INTO `hoteldb`.`state` (`state_id`, `name`) VALUES (2, 'PAID');

COMMIT;

