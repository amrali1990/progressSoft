CREATE DATABASE `progresssoft`;

CREATE TABLE progresssoft.files (
  `fileId` int(11) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fileId`),
  UNIQUE KEY `PS_FileName` (`fileId`),
  UNIQUE KEY `UK_e034t8bj77h24d7pvibbw1pcn` (`fileName`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE progresssoft.validdeals (
  `validDealId` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `fromCurrency` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `toCurrency` varchar(255) DEFAULT NULL,
  `fileId` int(11) DEFAULT NULL,
  `file` int(11) DEFAULT NULL,
  PRIMARY KEY (`validDealId`),
  KEY `FK_jq2vgidxcfepqs7o9d2wkp2t3` (`fileId`),
  CONSTRAINT `FK_jq2vgidxcfepqs7o9d2wkp2t3` FOREIGN KEY (`fileId`) REFERENCES progresssoft.files (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `invaliddeals` (
  `inValidDealId` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `fromCurrency` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `toCurrency` varchar(255) DEFAULT NULL,
  `fileId` int(11) DEFAULT NULL,
  PRIMARY KEY (`inValidDealId`),
  KEY `FK_amc96pidfbcx2s9y6l49kigh` (`fileId`),
  CONSTRAINT `FK_amc96pidfbcx2s9y6l49kigh` FOREIGN KEY (`fileId`) REFERENCES progresssoft.files (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;



