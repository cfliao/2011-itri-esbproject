-- MySQL dump 10.8
--
-- Host: localhost    Database: jpetstore
-- ------------------------------------------------------
-- Server version	4.1.7-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE="NO_AUTO_VALUE_ON_ZERO" */;

--
-- Current Database: `jpetstore`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `jpetstore`;

USE `jpetstore`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `userid` varchar(80) NOT NULL default '',
  `email` varchar(80) NOT NULL default '',
  `firstname` varchar(80) NOT NULL default '',
  `lastname` varchar(80) NOT NULL default '',
  `status` char(2) default NULL,
  `addr1` varchar(80) NOT NULL default '',
  `addr2` varchar(40) default NULL,
  `city` varchar(80) NOT NULL default '',
  `state` varchar(80) NOT NULL default '',
  `zip` varchar(20) NOT NULL default '',
  `country` varchar(20) NOT NULL default '',
  `phone` varchar(80) NOT NULL default '',
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 MAX_ROWS=1000 COMMENT='cadastro de contas';

--
-- Dumping data for table `account`
--


/*!40000 ALTER TABLE `account` DISABLE KEYS */;
LOCK TABLES `account` WRITE;
INSERT INTO `account` VALUES ('ACID','acid@yourdomain.com','ABC','XYX','OK','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','555-555-5555'),('j2ee','yourname@yourdomain.com','ABC','XYX','OK','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','555-555-5555');
UNLOCK TABLES;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

--
-- Table structure for table `bannerdata`
--

DROP TABLE IF EXISTS `bannerdata`;
CREATE TABLE `bannerdata` (
  `favcategory` varchar(80) NOT NULL default '',
  `bannername` varchar(255) default NULL,
  PRIMARY KEY  (`favcategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='banner data';

--
-- Dumping data for table `bannerdata`
--


/*!40000 ALTER TABLE `bannerdata` DISABLE KEYS */;
LOCK TABLES `bannerdata` WRITE;
INSERT INTO `bannerdata` VALUES ('BIRDS','<image src=\"../images/banner_birds.gif\">'),('CATS','<image src=\"../images/banner_cats.gif\">'),('DOGS','<image src=\"../images/banner_dogs.gif\">'),('FISH','<image src=\"../images/banner_fish.gif\">'),('REPTILES','<image src=\"../images/banner_reptiles.gif\">');
UNLOCK TABLES;
/*!40000 ALTER TABLE `bannerdata` ENABLE KEYS */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `catid` varchar(10) NOT NULL default '',
  `name` varchar(80) default NULL,
  `descn` varchar(255) default NULL,
  PRIMARY KEY  (`catid`),
  KEY `ixcategoryproduct` (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='categorias';

--
-- Dumping data for table `category`
--


/*!40000 ALTER TABLE `category` DISABLE KEYS */;
LOCK TABLES `category` WRITE;
INSERT INTO `category` VALUES ('BIRDS','Birds','<image src=\"../images/birds_icon.gif\"><font size=\"5\" color=\"blue\"> Birds</font>'),('CATS','Cats','<image src=\"../images/cats_icon.gif\"><font size=\"5\" color=\"blue\"> Cats</font>'),('DOGS','Dogs','<image src=\"../images/dogs_icon.gif\"><font size=\"5\" color=\"blue\"> Dogs</font>'),('FISH','Fish','<image src=\"../images/fish_icon.gif\"><font size=\"5\" color=\"blue\"> Fish</font>'),('REPTILES','Reptiles','<image src=\"../images/reptiles_icon.gif\"><font size=\"5\" color=\"blue\"> Reptiles</font>');
UNLOCK TABLES;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `itemid` varchar(10) NOT NULL default '',
  `qty` int(11) NOT NULL default '0',
  PRIMARY KEY  (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='inventory';

--
-- Dumping data for table `inventory`
--


/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
LOCK TABLES `inventory` WRITE;
INSERT INTO `inventory` VALUES ('EST-1',10000),('EST-10',9999),('EST-11',10000),('EST-12',10000),('EST-13',10000),('EST-14',10000),('EST-15',9999),('EST-16',10000),('EST-17',10000),('EST-18',9998),('EST-19',10000),('EST-2',10000),('EST-20',10000),('EST-21',10000),('EST-22',10000),('EST-23',10000),('EST-24',10000),('EST-25',10000),('EST-26',10000),('EST-27',10000),('EST-28',10000),('EST-3',10000),('EST-4',10000),('EST-5',10000),('EST-6',10000),('EST-7',10000),('EST-8',10000),('EST-9',10000);
UNLOCK TABLES;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `itemid` varchar(10) NOT NULL default '',
  `productid` varchar(10) NOT NULL default '',
  `listprice` decimal(10,2) default NULL,
  `unitcost` decimal(10,2) default NULL,
  `supplier` int(11) default NULL,
  `status` char(2) default NULL,
  `attr1` varchar(80) default NULL,
  `attr2` varchar(80) default NULL,
  `attr3` varchar(80) default NULL,
  `attr4` varchar(80) default NULL,
  `attr5` varchar(80) default NULL,
  PRIMARY KEY  (`itemid`),
  KEY `itemprod` (`productid`),
  KEY `supplier` (`supplier`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`suppid`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='itens';

--
-- Dumping data for table `item`
--


/*!40000 ALTER TABLE `item` DISABLE KEYS */;
LOCK TABLES `item` WRITE;
INSERT INTO `item` VALUES ('EST-1','FI-SW-01','16.50','10.00',1,'P','Large',NULL,NULL,NULL,NULL),('EST-10','K9-DL-01','18.50','12.00',1,'P','Spotted Adult Female',NULL,NULL,NULL,NULL),('EST-11','RP-SN-01','18.50','12.00',1,'P','Venomless',NULL,NULL,NULL,NULL),('EST-12','RP-SN-01','18.50','12.00',1,'P','Rattleless',NULL,NULL,NULL,NULL),('EST-13','RP-LI-02','18.50','12.00',1,'P','Green Adult',NULL,NULL,NULL,NULL),('EST-14','FL-DSH-01','58.50','12.00',1,'P','Tailless',NULL,NULL,NULL,NULL),('EST-15','FL-DSH-01','23.50','12.00',1,'P','With tail',NULL,NULL,NULL,NULL),('EST-16','FL-DLH-02','93.50','12.00',1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-17','FL-DLH-02','93.50','12.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-18','AV-CB-01','193.50','92.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-19','AV-SB-02','15.50','2.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-2','FI-SW-01','16.50','10.00',1,'P','Small',NULL,NULL,NULL,NULL),('EST-20','FI-FW-02','5.50','2.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-21','FI-FW-02','5.29','1.00',1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-22','K9-RT-02','135.50','100.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-23','K9-RT-02','145.49','100.00',1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-24','K9-RT-02','255.50','92.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-25','K9-RT-02','325.29','90.00',1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-26','K9-CW-01','125.50','92.00',1,'P','Adult Male',NULL,NULL,NULL,NULL),('EST-27','K9-CW-01','155.29','90.00',1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-28','K9-RT-01','155.29','90.00',1,'P','Adult Female',NULL,NULL,NULL,NULL),('EST-3','FI-SW-02','18.50','12.00',1,'P','Toothless',NULL,NULL,NULL,NULL),('EST-4','FI-FW-01','18.50','12.00',1,'P','Spotted',NULL,NULL,NULL,NULL),('EST-5','FI-FW-01','18.50','12.00',1,'P','Spotless',NULL,NULL,NULL,NULL),('EST-6','K9-BD-01','18.50','12.00',1,'P','Male Adult',NULL,NULL,NULL,NULL),('EST-7','K9-BD-01','18.50','12.00',1,'P','Female Puppy',NULL,NULL,NULL,NULL),('EST-8','K9-PO-02','18.50','12.00',1,'P','Male Puppy',NULL,NULL,NULL,NULL),('EST-9','K9-DL-01','18.50','12.00',1,'P','Spotless Male Puppy',NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

--
-- Table structure for table `lineitem`
--

DROP TABLE IF EXISTS `lineitem`;
CREATE TABLE `lineitem` (
  `orderid` int(11) NOT NULL default '0',
  `linenum` int(11) NOT NULL default '0',
  `itemid` varchar(10) NOT NULL default '',
  `quantity` int(11) NOT NULL default '0',
  `unitprice` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`orderid`,`linenum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='line item';

--
-- Dumping data for table `lineitem`
--


/*!40000 ALTER TABLE `lineitem` DISABLE KEYS */;
LOCK TABLES `lineitem` WRITE;
INSERT INTO `lineitem` VALUES (8,1,'EST-18',1,'193.50'),(8,2,'EST-15',1,'23.50');
UNLOCK TABLES;
/*!40000 ALTER TABLE `lineitem` ENABLE KEYS */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL auto_increment,
  `userid` varchar(80) NOT NULL default '',
  `orderdate` date NOT NULL default '0000-00-00',
  `shipaddr1` varchar(80) NOT NULL default '',
  `shipaddr2` varchar(80) default NULL,
  `shipcity` varchar(80) NOT NULL default '',
  `shipstate` varchar(80) NOT NULL default '',
  `shipzip` varchar(20) NOT NULL default '',
  `shipcountry` varchar(20) NOT NULL default '',
  `billaddr1` varchar(80) NOT NULL default '',
  `billaddr2` varchar(80) default NULL,
  `billcity` varchar(80) NOT NULL default '',
  `billstate` varchar(80) NOT NULL default '',
  `billzip` varchar(20) NOT NULL default '',
  `billcountry` varchar(20) NOT NULL default '',
  `courier` varchar(80) NOT NULL default '',
  `totalprice` decimal(10,2) NOT NULL default '0.00',
  `billtofirstname` varchar(80) NOT NULL default '',
  `billtolastname` varchar(80) NOT NULL default '',
  `shiptofirstname` varchar(80) NOT NULL default '',
  `shiptolastname` varchar(80) NOT NULL default '',
  `creditcard` varchar(80) NOT NULL default '',
  `exprdate` varchar(7) NOT NULL default '',
  `cardtype` varchar(80) NOT NULL default '',
  `locale` varchar(80) NOT NULL default '',
  PRIMARY KEY  (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='cadastro de pedidos';

--
-- Dumping data for table `orders`
--


/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
LOCK TABLES `orders` WRITE;
INSERT INTO `orders` VALUES (8,'j2ee','2007-03-01','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','UPS','217.00','ABC','XYX','ABC','XYX','999 9999 9999 9999','12/03','Visa','CA');
UNLOCK TABLES;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

--
-- Table structure for table `orderstatus`
--

DROP TABLE IF EXISTS `orderstatus`;
CREATE TABLE `orderstatus` (
  `orderid` int(11) NOT NULL default '0',
  `linenum` int(11) NOT NULL default '0',
  `timestamp` date NOT NULL default '0000-00-00',
  `status` char(2) NOT NULL default '',
  PRIMARY KEY  (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='status de pedidos';

--
-- Dumping data for table `orderstatus`
--


/*!40000 ALTER TABLE `orderstatus` DISABLE KEYS */;
LOCK TABLES `orderstatus` WRITE;
INSERT INTO `orderstatus` VALUES (8,8,'2007-03-01','P');
UNLOCK TABLES;
/*!40000 ALTER TABLE `orderstatus` ENABLE KEYS */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productid` varchar(10) NOT NULL default '',
  `category` varchar(10) NOT NULL default '',
  `name` varchar(80) default NULL,
  `descn` varchar(255) default NULL,
  PRIMARY KEY  (`productid`),
  KEY `productcat` (`category`),
  KEY `productname` (`name`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='categorias';

--
-- Dumping data for table `product`
--


/*!40000 ALTER TABLE `product` DISABLE KEYS */;
LOCK TABLES `product` WRITE;
INSERT INTO `product` VALUES ('AV-CB-01','BIRDS','Amazon Parrot','<image src=\"../images/bird4.gif\">Great companion for up to 75 years'),('AV-SB-02','BIRDS','Finch','<image src=\"../images/bird1.gif\">Great stress reliever'),('FI-FW-01','FISH','Koi','<image src=\"../images/fish3.gif\">Fresh Water fish from Japan'),('FI-FW-02','FISH','Goldfish','<image src=\"../images/fish2.gif\">Fresh Water fish from China'),('FI-SW-01','FISH','Angelfish','<image src=\"../images/fish1.jpg\">Salt Water fish from Australia'),('FI-SW-02','FISH','Tiger Shark','<image src=\"../images/fish4.gif\">Salt Water fish from Australia'),('FL-DLH-02','CATS','Persian','<image src=\"../images/cat1.gif\">Friendly house cat, doubles as a princess'),('FL-DSH-01','CATS','Manx','<image src=\"../images/cat3.gif\">Great for reducing mouse populations'),('K9-BD-01','DOGS','Bulldog','<image src=\"../images/dog2.gif\">Friendly dog from England'),('K9-CW-01','DOGS','Chihuahua','<image src=\"../images/dog4.gif\">Great companion dog'),('K9-DL-01','DOGS','Dalmation','<image src=\"../images/dog5.gif\">Great dog for a Fire Station'),('K9-PO-02','DOGS','Poodle','<image src=\"../images/dog6.gif\">Cute dog from France'),('K9-RT-01','DOGS','Golden Retriever','<image src=\"../images/dog1.gif\">Great family dog'),('K9-RT-02','DOGS','Labrador Retriever','<image src=\"../images/dog5.gif\">Great hunting dog'),('RP-LI-02','REPTILES','Iguana','<image src=\"../images/lizard2.gif\">Friendly green friend'),('RP-SN-01','REPTILES','Rattlesnake','<image src=\"../images/lizard3.gif\">Doubles as a watch dog');
UNLOCK TABLES;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `userid` varchar(80) NOT NULL default '',
  `langpref` varchar(80) NOT NULL default '',
  `favcategory` varchar(30) default NULL,
  `mylistopt` tinyint(1) default NULL,
  `banneropt` tinyint(1) default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='cadastro de perfis';

--
-- Dumping data for table `profile`
--


/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
LOCK TABLES `profile` WRITE;
INSERT INTO `profile` VALUES ('ACID','english','CATS',1,1),('j2ee','english','DOGS',1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `name` varchar(30) NOT NULL default '',
  `nextid` int(11) NOT NULL default '0',
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='inventory';

--
-- Dumping data for table `sequence`
--


/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
LOCK TABLES `sequence` WRITE;
INSERT INTO `sequence` VALUES ('linenum',1000),('ordernum',1000);
UNLOCK TABLES;
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;

--
-- Table structure for table `signon`
--

DROP TABLE IF EXISTS `signon`;
CREATE TABLE `signon` (
  `username` varchar(25) NOT NULL default '',
  `password` varchar(25) NOT NULL default '',
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 MAX_ROWS=1000 COMMENT='cadastro de usuios';

--
-- Dumping data for table `signon`
--


/*!40000 ALTER TABLE `signon` DISABLE KEYS */;
LOCK TABLES `signon` WRITE;
INSERT INTO `signon` VALUES ('ACID','ACID'),('j2ee','j2ee');
UNLOCK TABLES;
/*!40000 ALTER TABLE `signon` ENABLE KEYS */;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `suppid` int(11) NOT NULL default '0',
  `name` varchar(80) default NULL,
  `status` char(2) NOT NULL default '',
  `addr1` varchar(80) default NULL,
  `addr2` varchar(80) default NULL,
  `city` varchar(80) default NULL,
  `state` varchar(80) default NULL,
  `zip` varchar(5) default NULL,
  `phone` varchar(80) default NULL,
  PRIMARY KEY  (`suppid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 MAX_ROWS=1000 COMMENT='cadastro de fornecedores';

--
-- Dumping data for table `supplier`
--


/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
LOCK TABLES `supplier` WRITE;
INSERT INTO `supplier` VALUES (1,'XYZ Pets','AC','600 Avon Way','','Los Angeles','CA','94024','212-947-0797'),(2,'ABC Pets','AC','700 Abalone Way','','San Francisco','CA','94024','415-947-0797');
UNLOCK TABLES;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

