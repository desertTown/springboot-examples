CREATE DATABASE IF NOT EXISTS `master_tenant` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE IF NOT EXISTS `evan_tenant` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE IF NOT EXISTS `messi_tenant` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE IF NOT EXISTS `master_tenant`.`master_tenant` (
  `ID` varchar(255) NOT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `TENANT` varchar(255) DEFAULT NULL,
  `URL` varchar(256) DEFAULT NULL,
  `USERNAME` varchar(30) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;

INSERT INTO master_tenant.master_tenant(id, PASSWORD, tenant, url, username, VERSION) VALUES(1, '123456', 'evan_tenant', 'jdbc:mysql://localhost:3306/evan_tenant?useSSL=false', 'root', 1);
INSERT INTO master_tenant.master_tenant(id, PASSWORD, tenant, url, username, VERSION) VALUES(2, '123456', 'messi_tenant', 'jdbc:mysql://localhost:3306/messi_tenant?useSSL=false', 'root', 1);


CREATE TABLE  IF NOT EXISTS `evan_tenant`.`user` (
  `ID` VARCHAR(255) NOT NULL,
  `USERNAME` VARCHAR(30) DEFAULT NULL,
  `PASSWORD` VARCHAR(30) DEFAULT NULL,
  `TENANT` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;

CREATE TABLE  IF NOT EXISTS `messi_tenant`.`user` (
  `ID` VARCHAR(255) NOT NULL,
  `USERNAME` VARCHAR(30) DEFAULT NULL,
  `PASSWORD` VARCHAR(30) DEFAULT NULL,
  `TENANT` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;


INSERT INTO evan_tenant.user(id, username, PASSWORD, tenant) VALUES(1, 'evan', '111111', 'evan_tenant');
INSERT INTO messi_tenant.user(id, username, PASSWORD, tenant) VALUES(2, 'messi', '222222', 'messi_tenant');