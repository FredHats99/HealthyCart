-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ISPW_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ISPW_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ISPW_DB` DEFAULT CHARACTER SET utf8 ;
USE `ISPW_DB` ;

-- -----------------------------------------------------
-- Table `ISPW_DB`.`Additives`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ISPW_DB`.`Additives` (
  `idAdditives` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Dangerousness` ENUM('A', 'B', 'C', 'D', 'E') NOT NULL,
  PRIMARY KEY (`idAdditives`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ISPW_DB`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ISPW_DB`.`Users` (
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `isPremium` TINYINT NOT NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ISPW_DB`.`Carts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ISPW_DB`.`Carts` (
  `idCarts` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `avgScore` INT NOT NULL,
  PRIMARY KEY (`idCarts`),
  INDEX `user_cart_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `user_cart`
    FOREIGN KEY (`user`)
    REFERENCES `ISPW_DB`.`Users` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ISPW_DB`.`ItemsInCart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ISPW_DB`.`ItemsInCart` (
  `Cart` INT NOT NULL AUTO_INCREMENT,
  `barcode` VARCHAR(45) NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`Cart`, `barcode`, `Quantity`),
  CONSTRAINT `cart_items`
    FOREIGN KEY (`Cart`)
    REFERENCES `ISPW_DB`.`Carts` (`idCarts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `ISPW_DB` ;

-- -----------------------------------------------------
-- procedure search_additive_dangerousness
-- -----------------------------------------------------

DELIMITER $$
USE `ISPW_DB`$$
CREATE PROCEDURE `search_additive_dangerousness` (IN additive_name VARCHAR(45))
BEGIN
	select Dangerousness from Additives
    where name = additive_name;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure create_User
-- -----------------------------------------------------

DELIMITER $$
USE `ISPW_DB`$$
CREATE PROCEDURE `create_User` (IN user_name VARCHAR(45), IN user_password VARCHAR(45))
BEGIN
	INSERT INTO Users(Username, Password_, isPremium)
    VALUES (user_name, user_password, 0);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure change_Password
-- -----------------------------------------------------

DELIMITER $$
USE `ISPW_DB`$$
CREATE PROCEDURE `change_Password` (IN user_name varchar(45), IN user_password VARCHAR(45))
BEGIN
	if exists(SELECT username from Users WHERE username = user_name) then
		update Users
		set Users.password = user_password
		where username = user_name;
	end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure update_to_Premium_user
-- -----------------------------------------------------

DELIMITER $$
USE `ISPW_DB`$$
CREATE PROCEDURE `update_to_Premium_user` (IN user_name VARCHAR(45))
BEGIN
	if exists(SELECT username from Users WHERE username = user_name) then
		update Users
        set isPremium = 1
        where username = user_name;
	end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure addCart
-- -----------------------------------------------------

DELIMITER $$
USE `ISPW_DB`$$
CREATE PROCEDURE `addCart` (IN user_name VARCHAR(45), IN avg_Score INT)
BEGIN
	INSERT INTO Carts(user, date, avgScore) VALUES (user_name, curdate(), avg_Score);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_carts
-- -----------------------------------------------------

DELIMITER $$
USE `ISPW_DB`$$
CREATE PROCEDURE `get_carts` (IN Username VARCHAR(45))
BEGIN
	SELECT (Cart,Barcode) FROM ItemsInCart 
    JOIN (SELECT * FROM Carts WHERE Carts.user = Username) AS A1
    ON ItemsInCart.Cart = A1.idCarts;
END$$

DELIMITER ;

INSERT INTO Users(Username, Password, isPremium) VALUES ('Cap', 'Cap', 0);
INSERT INTO Users(Username, Password, isPremium) VALUES ('Gio', 'Gio', 1);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
