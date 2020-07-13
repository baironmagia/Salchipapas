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
AUTO_INCREMENT = 3
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
AUTO_INCREMENT = 3
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
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`compra_dia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`compra_dia` (
  `id_compra` INT(11) NOT NULL AUTO_INCREMENT,
  `id_perso` INT(11) NOT NULL,
  `id_prove` INT(11) NOT NULL,
  `fecha` DATE NOT NULL,
  `est_comp` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_compra_dia_persona1_idx` (`id_perso` ASC) VISIBLE,
  INDEX `fk_compra_dia_proveedor1_idx` (`id_prove` ASC) VISIBLE,
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
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`detalle_compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`detalle_compra` (
  `id_compra` INT(11) NOT NULL,
  `cantidad` INT(11) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  `valor` INT(20) NULL DEFAULT NULL,
  INDEX `fk_detalle_compra_compra_dia1_idx` (`id_compra` ASC) VISIBLE,
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
  INDEX `fk_venta_cliente1_idx` (`id_cli` ASC) VISIBLE,
  INDEX `fk_venta_persona1_idx` (`id_perso` ASC) VISIBLE,
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
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salchipapas_bd`.`detalleventa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salchipapas_bd`.`detalleventa` (
  `id_venta` INT(11) NOT NULL,
  `id_plato` INT(11) NOT NULL,
  `und_det` INT(11) NOT NULL,
  `val_det` INT(20) NOT NULL,
  INDEX `fk_detalleventa_venta_idx` (`id_venta` ASC) VISIBLE,
  INDEX `fk_detalleventa_platillos1_idx` (`id_plato` ASC) VISIBLE,
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
  INDEX `fk_puntos_cliente1_idx` (`id_cli` ASC) VISIBLE,
  CONSTRAINT `fk_puntos_cliente1`
    FOREIGN KEY (`id_cli`)
    REFERENCES `salchipapas_bd`.`cliente` (`id_cli`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `salchipapas_bd` ;

-- -----------------------------------------------------
-- function AddCompraDia
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `AddCompraDia`(id_per int(11),id_pro int(11)) RETURNS tinyint(1)
begin	
    insert into compra_dia values(null,id_per ,id_pro,now(),true);
    return true;
end$$

DELIMITER ;

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
-- function Addcliente
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `Addcliente`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),ce varchar(15),tel varchar(15),dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin	
    insert into cliente values(null,n1,n2,a1,a2,ce,tel,dir,cor,true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Adddetalle_compra
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `Adddetalle_compra`(can int(11),des varchar(200),val int(20),id_c int(11)) RETURNS tinyint(1)
begin	
    insert into detalle_compra values(can,des,val,id_c,true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Addproveedor
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `Addproveedor`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),ni varchar(100),di varchar(15),co varchar(30)) RETURNS tinyint(1)
begin	
    insert into proveedor values(null,n1,n2,a1,a2,tel,ni,di,co,now(),true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function UpCompraDia
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `UpCompraDia`(id int,id_per int(11),id_prov int(11)) RETURNS tinyint(1)
begin
    update compra_dia set id_perso=id_per,id_prove=id_prov where id_compra=id;
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

-- -----------------------------------------------------
-- function UpProveedor
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `UpProveedor`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15), ni varchar(100),dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin
    update proveedor set nom1_prove=n1,nom2_prove=n2,ape1_prove=a1,ap2_prove=a2,tel_prove=tel,nit_prove=ni,dir_prove=dir,cor_prove=cor where id_prove=id;
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Upcliente
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `Upcliente`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),ce varchar(15) ,tel varchar(15), dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin
    update cliente set nom1_cli=n1,nom2_cli=n2,ape1_cli=a1,ape2_cli=a2,ced_cli=ce,tel_cli=tel,dir_cli=dir,cor_cli=cor where id_cli=id;
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Updetalle_compra
-- -----------------------------------------------------

DELIMITER $$
USE `salchipapas_bd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `Updetalle_compra`( can int(11),des varchar(200),val int(20),id_c int(11)) RETURNS tinyint(1)
begin
    update detalle_compra set cantidad=can,descripcion=des,valor=val,ide_compra=id_c where cantidad=can;
    return true;
end$$

DELIMITER ;

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'laura pausini','ines','rrosales de castro','ortiz','8787','32022002','playa','lala@gmail.com',1),(2,'Amelia','daris','rozo','dulca','8989','3200222','playa','luz@gmail.com',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `compra_dia` WRITE;
/*!40000 ALTER TABLE `compra_dia` DISABLE KEYS */;
INSERT INTO `compra_dia` VALUES (1,1,1,'2020-06-02',1),(2,1,1,'2020-06-02',1),(3,2,2,'2020-05-07',1),(4,2,1,'2020-07-12',1);
/*!40000 ALTER TABLE `compra_dia` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'a','c','v','b','3','n','Administrador','4','t','2020-06-27','n',1),(2,'ui','ui','ui','iu','ui','ui','Administrador','ui','iu','2020-06-27','ui',1);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'martin','leo','otiz','catro','3211123','1010','av playa','martin@gmail.com','2020-06-27',1),(2,'jimmy','leo','guzman','peres','321444111','1020','vp playa','jim@mail.com','2020-06-27',1);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;


