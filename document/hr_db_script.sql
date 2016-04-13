CREATE DATABASE IF NOT EXISTS `hrdb` 
USE `hrdb`;

CREATE TABLE IF NOT EXISTS `employee` (
  `employeeid` int(10) NOT NULL AUTO_INCREMENT,
  `ssn` varchar(20) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `initmiddle` varchar(1) DEFAULT NULL,
  `location` varchar(50) NOT NULL,
  `salarytype` varchar(20) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `job` (
  `jobid` int(10) NOT NULL AUTO_INCREMENT,
  `jobnumber` varchar(25) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  PRIMARY KEY (`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `jobassignment` (
  `employeeid` int(10) NOT NULL,
  `jobid` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  PRIMARY KEY (`employeeid`,`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `location` (
  `locationid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  PRIMARY KEY (`locationid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


INSERT INTO `location` (`locationid`, `name`, `createddate`) VALUES
	(1, 'New York, US', '2016-04-05 15:50:59'),
	(2, 'San Francsco, US', '2016-04-05 15:50:59'),
	(3, 'Dublin, Ireland', '2016-04-05 15:50:59'),
	(4, 'Moscow, Russia', '2016-04-05 15:50:59'),
	(5, 'Chennai, India', '2016-04-05 15:50:59'),
	(6, 'Bangaluru, India', '2016-04-05 15:50:59'),
	(7, 'Pune, India', '2016-04-05 15:50:59'),
	(8, 'Hong Kong, China', '2016-04-05 15:50:59'),
	(9, 'Beijing, China', '2016-04-05 15:50:59'),
	(10, 'Shenzhen, China', '2016-04-05 15:50:59');

	