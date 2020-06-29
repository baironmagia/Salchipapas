CREATE SCHEMA IF NOT EXISTS `salchipapas_bd`;
USE `salchipapas_bd` ;

CREATE TABLE cliente (
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
  PRIMARY KEY (id_cli));
CREATE TABLE persona (
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
  PRIMARY KEY (`id_perso`));
CREATE TABLE proveedor (
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
  PRIMARY KEY (`id_prove`));
CREATE TABLE compra_dia (
  `id_compra` INT(11) NOT NULL AUTO_INCREMENT,
  `id_perso` INT(11) NOT NULL,
  `id_prove` INT(11) NOT NULL,
  `total` INT(20) NOT NULL DEFAULT '0',
  `fecha` DATE NOT NULL,
  `est_comp` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_compra_dia_persona1_idx` (`id_perso` ASC),
  CONSTRAINT `fk_compra_dia_persona1`
    FOREIGN KEY (`id_perso`)
    REFERENCES `persona` (`id_perso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compra_dia_proveedor1`
    FOREIGN KEY (`id_prove`)
    REFERENCES `proveedor` (`id_prove`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE detalle_compra (
  `cantidad` INT(11) NULL DEFAULT NULL,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL,
  `valor` INT(20) NULL DEFAULT NULL,
  `id_compra` INT(11) NOT NULL,
  INDEX `fk_detalle_compra_compra_dia1_idx` (`id_compra` ASC),
  CONSTRAINT `fk_detalle_compra_compra_dia1`
    FOREIGN KEY (`id_compra`)
    REFERENCES `compra_dia` (`id_compra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE platillo (
  `id_plato` INT(11) NOT NULL AUTO_INCREMENT,
  `des_plato` VARCHAR(50) NULL DEFAULT NULL,
  `valor_plato` INT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_plato`));
CREATE TABLE venta (
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
    REFERENCES `cliente` (`id_cli`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_persona1`
    FOREIGN KEY (`id_perso`)
    REFERENCES `persona` (`id_perso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE detalleventa (
  `id_venta` INT(11) NOT NULL,
  `id_plato` INT(11) NOT NULL,
  `und_det` INT(11) NOT NULL,
  `val_det` INT(20) NOT NULL,
  INDEX `fk_detalleventa_venta_idx` (`id_venta` ASC),
  INDEX `fk_detalleventa_platillos1_idx` (`id_plato` ASC),
  CONSTRAINT `fk_detalleventa_platillos1`
    FOREIGN KEY (`id_plato`)
    REFERENCES `platillo` (`id_plato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalleventa_venta`
    FOREIGN KEY (`id_venta`)
    REFERENCES `venta` (`id_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE puntos (
  `id_pun` INT(11) NOT NULL AUTO_INCREMENT,
  `cant_pun` INT(11) NOT NULL,
  `fecha_pun` DATE NOT NULL,
  `id_cli` INT(11) NOT NULL,
  PRIMARY KEY (`id_pun`),
  INDEX `fk_puntos_cliente1_idx` (`id_cli` ASC),
  CONSTRAINT `fk_puntos_cliente1`
    FOREIGN KEY (`id_cli`)
    REFERENCES `cliente` (`id_cli`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- function AddPersona
-- -----------------------------------------------------

DELIMITER $$
CREATE FUNCTION `AddPersona`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),dir varchar(100),tip varchar(15),usu varchar(30),clv varchar(30),cor varchar(255)) RETURNS tinyint(1)
begin	
    insert into persona values(null,n1,n2,a1,a2,tel,dir,tip,usu,clv,now(),cor,true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Addcliente
-- -----------------------------------------------------

DELIMITER $$
CREATE FUNCTION `Addcliente`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),ce varchar(15),tel varchar(15),dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin	
    insert into cliente values(null,n1,n2,a1,a2,ce,tel,dir,cor,true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Addproveedor
-- -----------------------------------------------------

DELIMITER $$
CREATE FUNCTION `Addproveedor`(n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),ni varchar(100),di varchar(15),co varchar(30)) RETURNS tinyint(1)
begin	
    insert into proveedor values(null,n1,n2,a1,a2,tel,ni,di,co,now(),true);
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function UpPersona
-- -----------------------------------------------------

DELIMITER $$
CREATE FUNCTION `UpPersona`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15),dir varchar(100),tip varchar(15),usu varchar(30),clv varchar(30),cor varchar(255),est boolean) RETURNS tinyint(1)
begin
    update persona set nom1_perso=n1,nom2_perso=n2,ape1_perso=a1,ape2_perso=a2,tel_perso=tel,dir_perso=dir,tipo_perso=tip,usu_perso=usu,clv_perso=clv,cor_perso=cor,est_perso=est where id_perso=id;
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function UpProveedor
-- -----------------------------------------------------

DELIMITER $$

CREATE FUNCTION `UpProveedor`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),tel varchar(15), ni varchar(100),dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin
    update proveedor set nom1_prove=n1,nom2_prove=n2,ape1_prove=a1,ap2_prove=a2,tel_prove=tel,nit_prove=ni,dir_prove=dir,cor_prove=cor where id_prove=id;
    return true;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function Upcliente
-- -----------------------------------------------------

DELIMITER $$
CREATE FUNCTION `Upcliente`(id int,n1 varchar(30),n2 varchar(30),a1 varchar(30),a2 varchar(30),ce varchar(15) ,tel varchar(15), dir varchar(100),cor varchar(255)) RETURNS tinyint(1)
begin
    update cliente set nom1_cli=n1,nom2_cli=n2,ape1_cli=a1,ape2_cli=a2,ced_cli=ce,tel_cli=tel,dir_cli=dir,cor_cli=cor where id_cli=id;
    return true;
end$$

DELIMITER ;

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'laura pausini','ines','rrosales de castro','ortiz','8787','32022002','playa','lala@gmail.com',1),(2,'Amelia','daris','rozo','dulca','8989','3200222','playa','luz@gmail.com',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
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
