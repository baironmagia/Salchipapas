CREATE SCHEMA IF NOT EXISTS `salchipapas_bd` DEFAULT CHARACTER SET utf8 ;
USE `salchipapas_bd` ;

-- -----------------------------------------------------
-- Table `salchipapas_bd`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`cliente` (
  `id_cli` INT(11) NOT NULL AUTO_INCREMENT,
  `nom1_cli` VARCHAR(30) NOT NULL,
  `nom2_cli` VARCHAR(30) NULL DEFAULT NULL,
  `ape1_cli` VARCHAR(30) NOT NULL,
  `ape2_cli` VARCHAR(30) NULL DEFAULT NULL,
  `ced_cli` VARCHAR(11) NOT NULL,
  `tel_cli` VARCHAR(15) NOT NULL,
  `dir_cli` VARCHAR(100) NOT NULL,
  `cor_cli` VARCHAR(255) NULL DEFAULT NULL,
  `est_cli` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_cli`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`persona` (
  `id_perso` INT(11) NOT NULL AUTO_INCREMENT,
  `nom1_perso` VARCHAR(30) NOT NULL,
  `nom2_perso` VARCHAR(30) NULL DEFAULT NULL,
  `ape1_perso` VARCHAR(30) NOT NULL,
  `ape2_perso` VARCHAR(30) NULL DEFAULT NULL,
  `tel_perso` VARCHAR(15) NULL DEFAULT NULL,
  `dir_perso` VARCHAR(100) NULL DEFAULT NULL,
  `tipo_perso` VARCHAR(15) NULL DEFAULT NULL,
  `usu_perso` VARCHAR(30) NULL DEFAULT NULL,
  `clv_perso` VARCHAR(30) NULL DEFAULT NULL,
  `fecha_perso` DATE NULL DEFAULT NULL,
  `cor_perso` VARCHAR(255) NULL DEFAULT NULL,
  `est_perso` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_perso`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`proveedor` (
  `id_prove` INT(11) NOT NULL AUTO_INCREMENT,
  `nom1_prove` VARCHAR(30) NOT NULL,
  `nom2_prove` VARCHAR(30) NULL DEFAULT NULL,
  `ape1_prove` VARCHAR(30) NOT NULL,
  `ap2_prove` VARCHAR(30) NULL DEFAULT NULL,
  `tel_prove` VARCHAR(15) NULL DEFAULT NULL,
  `nit_prove` VARCHAR(30) NOT NULL,
  `dir_prove` VARCHAR(100) NULL DEFAULT NULL,
  `cor_prove` VARCHAR(255) NULL DEFAULT NULL,
  `fecha_prove` DATE NOT NULL,
  `est_prove` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_prove`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`compra_dia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`compra_dia` (
  `id_compra` INT(11) NOT NULL AUTO_INCREMENT,
  `id_perso` INT(11) NOT NULL,
  `id_prove` INT(11) NOT NULL,
  `total` INT(20) NOT NULL DEFAULT '0',
  `fecha` DATE NOT NULL,
  `est_comp` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_compra_dia_persona1_idx` (`id_perso` ASC),
  INDEX `fk_compra_dia_proveedor1_idx` (`id_prove` ASC),
  CONSTRAINT `fk_compra_dia_persona1`
    FOREIGN KEY (`id_perso`)
    REFERENCES `salchipapas_bd`.`persona` (`id_perso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_dia_proveedor1`
    FOREIGN KEY (`id_prove`)
    REFERENCES `salchipapas_bd`.`proveedor` (`id_prove`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`detalle_compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`detalle_compra` (
  `cantidad` INT(11) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  `valor` INT(20) NULL DEFAULT NULL,
  `id_compra` INT(11) NOT NULL,
  INDEX `fk_detalle_compra_compra_dia1_idx` (`id_compra` ASC),
  CONSTRAINT `fk_detalle_compra_compra_dia1`
    FOREIGN KEY (`id_compra`)
    REFERENCES `salchipapas_bd`.`compra_dia` (`id_compra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`platillo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`platillo` (
  `id_plato` INT(11) NOT NULL AUTO_INCREMENT,
  `des_plato` VARCHAR(50) NULL DEFAULT NULL,
  `valor_plato` INT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_plato`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`venta` (
  `id_venta` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_ven` DATE NULL DEFAULT NULL,
  `id_cli` INT(11) NOT NULL,
  `id_perso` INT(11) NOT NULL,
  `est_ven` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  INDEX `fk_venta_cliente1_idx` (`id_cli` ASC),
  INDEX `fk_venta_persona1_idx` (`id_perso` ASC),
  CONSTRAINT `fk_venta_cliente1`
    FOREIGN KEY (`id_cli`)
    REFERENCES `salchipapas_bd`.`cliente` (`id_cli`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_persona1`
    FOREIGN KEY (`id_perso`)
    REFERENCES `salchipapas_bd`.`persona` (`id_perso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`detalleventa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`detalleventa` (
  `id_venta` INT(11) NOT NULL,
  `id_plato` INT(11) NOT NULL,
  `und_det` INT(11) NOT NULL,
  `val_det` INT(20) NOT NULL,
  INDEX `fk_detalleventa_venta_idx` (`id_venta` ASC),
  INDEX `fk_detalleventa_platillos1_idx` (`id_plato` ASC),
  CONSTRAINT `fk_detalleventa_platillos1`
    FOREIGN KEY (`id_plato`)
    REFERENCES `salchipapas_bd`.`platillo` (`id_plato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalleventa_venta`
    FOREIGN KEY (`id_venta`)
    REFERENCES `salchipapas_bd`.`venta` (`id_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`puntos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`puntos` (
  `id_pun` INT(11) NOT NULL AUTO_INCREMENT,
  `cant_pun` INT(11) NOT NULL,
  `fecha_pun` DATE NOT NULL,
  `id_cli` INT(11) NOT NULL,
  PRIMARY KEY (`id_pun`),
  INDEX `fk_puntos_cliente1_idx` (`id_cli` ASC),
  CONSTRAINT `fk_puntos_cliente1`
    FOREIGN KEY (`id_cli`)
    REFERENCES `salchipapas_bd`.`cliente` (`id_cli`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

USE `salchipapas_bd` ;

-- -----------------------------------------------------
-- function AddPersona
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `AddPersona`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),dir varchar(100),tip varchar(15),usu varchar(30),clv varchar(30),cor varchar(255)) RETURNS tinyint(1)
begin	
    insert into persona values(null,n1,n2,a1,a2,tel,dir,tip,usu,clv,now(),cor,true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function UpPersona
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `UpPersona`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),dir varchar(100),tip varchar(15),usu varchar(30),clv varchar(30),cor varchar(255),est boolean) RETURNS tinyint(1)
begin
    update persona set nom1_perso=n1,nom2_perso=n2,ape1_perso=a1,ape2_perso=a2,tel_perso=tel,dir_perso=dir,tipo_perso=tip,usu_perso=usu,clv_perso=clv,cor_perso=cor,est_perso=est where id_perso=id;
    return true;
end$$

DELIMITER ;

-- ------------------------------------------------------------------------------------
-- add un proveedor a la base de datos
-- ------------------------------------------------------------------------------
DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `Addproveedor`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),ni varchar(100),di varchar(15),co varchar(30)) RETURNS tinyint(1)
begin	
    insert into proveedor values(null,n1,n2,a1,a2,tel,ni,di,co,now(),true);
    return true;
end$$
DELIMITER ;
describe proveedor;
-- ----------------------------------------------------------------------------
-- aptualizar proveedores
-- --------------------------------------------------------------------------
DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `UpProveedor`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15), ni varchar(100),dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin
    update proveedor set nom1_prove=n1,nom2_prove=n2,ape1_prove=a1,ap2_prove=a2,tel_prove=tel,nit_prove=ni,dir_prove=dir,cor_prove=cor where id_prove=id;
    return true;
end$$

DELIMITER ;
drop function UpProveedor;