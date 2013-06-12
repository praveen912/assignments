-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 11, 2013 at 03:27 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `studentbank`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountinfo`
--

CREATE TABLE IF NOT EXISTS `accountinfo` (
  `Username` varchar(15) NOT NULL,
  `Accountnumber` int(10) NOT NULL,
  `Balance` double NOT NULL,
  `Debitcardnumber` varchar(20) NOT NULL,
  `Creditcardnumber` varchar(16) NOT NULL,
  PRIMARY KEY (`Accountnumber`),
  UNIQUE KEY `Debitcardnumber` (`Debitcardnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountinfo`
--

INSERT INTO `accountinfo` (`Username`, `Accountnumber`, `Balance`, `Debitcardnumber`, `Creditcardnumber`) VALUES
('vaish7789', 20345, 70488, '2147483647789234', '2304564798762367'),
('praveen1788', 20356, 59800, '3404675409871267', '3081324934883743'),
('sairam', 41999, 20000, '1320574497497238', '3970442276218965'),
('Pavi87', 42591, 0, '725574893588288', '6475162532027108');

-- --------------------------------------------------------

--
-- Table structure for table `cctransactioninfo`
--

CREATE TABLE IF NOT EXISTS `cctransactioninfo` (
  `TransactionId` int(20) NOT NULL,
  `Creditcardnumber` varchar(16) NOT NULL,
  `Limit` double NOT NULL,
  `Balance` double NOT NULL,
  `Amount` double NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Transferfor` varchar(30) NOT NULL,
  `DateandTime` datetime NOT NULL,
  PRIMARY KEY (`TransactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cctransactioninfo`
--

INSERT INTO `cctransactioninfo` (`TransactionId`, `Creditcardnumber`, `Limit`, `Balance`, `Amount`, `Type`, `Transferfor`, `DateandTime`) VALUES
(23671, '2304564798762367', 50000, 40000, 10000, 'debit', 'Macys', '2012-06-14 07:25:10'),
(23672, '2304564798762367', 50000, 35000, 5000, 'debit', 'Giant Eagle', '2012-12-04 06:34:15'),
(23673, '2304564798762367', 50000, 34700, 300, 'debit', 'Macys', '2012-12-21 10:54:01'),
(23674, '2304564798762367', 50000, 50000, 15300, 'credit', 'Credit card bill payment', '2012-12-21 16:07:08'),
(23675, '2304564798762367', 50000, 49960, 40, 'debit', 'Giant Eagle', '2012-12-27 17:35:34'),
(23676, '2304564798762367', 50000, 50000, 40, 'credit', 'Credit card bill payment', '2012-12-27 17:41:32'),
(37431, '3081324934883743', 60000, 59900, 100, 'debit', 'Macys', '2013-01-04 21:54:23'),
(37432, '3081324934883743', 60000, 59800, 100, 'debit', 'Target', '2013-01-07 20:56:30'),
(37433, '3081324934883743', 60000, 60000, 200, 'credit', 'Credit card bill payment', '2013-01-07 20:57:18');

-- --------------------------------------------------------

--
-- Table structure for table `creditcardinfo`
--

CREATE TABLE IF NOT EXISTS `creditcardinfo` (
  `Username` varchar(15) NOT NULL,
  `Creditcardnumber` varchar(16) NOT NULL,
  `Pinnumber` int(4) NOT NULL,
  `Limit` int(20) NOT NULL,
  `Balance` int(20) NOT NULL,
  PRIMARY KEY (`Creditcardnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `creditcardinfo`
--

INSERT INTO `creditcardinfo` (`Username`, `Creditcardnumber`, `Pinnumber`, `Limit`, `Balance`) VALUES
('vaish7789', '2304564798762367', 1712, 50000, 50000),
('praveen1788', '3081324934883743', 1988, 60000, 60000),
('sairam', '3970442276218965', 1789, 60000, 60000),
('Pavi87', '6475162532027108', 1988, 40000, 40000);

-- --------------------------------------------------------

--
-- Table structure for table `transactioninfo`
--

CREATE TABLE IF NOT EXISTS `transactioninfo` (
  `TransactionId` int(50) NOT NULL,
  `Accountnumber` int(10) NOT NULL,
  `Balance` double NOT NULL,
  `Type` varchar(10) NOT NULL,
  `Amount` double NOT NULL,
  `Transfertofrom` varchar(50) NOT NULL,
  `DateandTime` datetime NOT NULL,
  PRIMARY KEY (`TransactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactioninfo`
--

INSERT INTO `transactioninfo` (`TransactionId`, `Accountnumber`, `Balance`, `Type`, `Amount`, `Transfertofrom`, `DateandTime`) VALUES
(203451, 20345, 50000, 'credit', 10000, 'Salary pay check', '2012-12-05 07:32:19'),
(203452, 20345, 46000, 'debit', 4000, 'Amazon', '2012-12-12 13:14:28'),
(203453, 20345, 45970, 'debit', 30, 'Giant Eagle', '2012-12-19 19:28:33'),
(203454, 20345, 45958, 'debit', 12, 'Papa Johns', '2012-12-19 19:47:02'),
(203455, 20345, 45858, 'debit', 100, 'Target', '2012-12-19 19:51:33'),
(203456, 20345, 65858, 'credit', 20000, 'Salary pay check', '2012-12-19 20:06:40'),
(203457, 20345, 50558, 'debit', 15300, 'Credit card bill payment', '2012-12-21 16:07:08'),
(203458, 20345, 70558, 'credit', 20000, 'Salary pay check', '2012-12-21 16:54:44'),
(203459, 20345, 70528, 'debit', 30, 'Giant Eagle', '2012-12-21 17:24:19'),
(203460, 20345, 70488, 'debit', 40, 'Credit card bill payment', '2012-12-27 17:41:32'),
(203561, 20356, 59800, 'debit', 200, 'Credit card bill payment', '2013-01-07 20:57:18'),
(222961, 22296, 40000, 'credit', 40000, 'Salary pay check', '2013-01-10 14:36:11'),
(419991, 41999, 20000, 'credit', 20000, 'Salary pay check', '2012-12-27 19:59:24');

-- --------------------------------------------------------

--
-- Table structure for table `useremployerinfo`
--

CREATE TABLE IF NOT EXISTS `useremployerinfo` (
  `Username` varchar(15) NOT NULL,
  `Company` varchar(30) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Designation` varchar(20) NOT NULL,
  `Salary` int(20) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useremployerinfo`
--

INSERT INTO `useremployerinfo` (`Username`, `Company`, `Address`, `Designation`, `Salary`) VALUES
('praveen1788', 'University of Pittsburgh', '708, William Pitt Union, Pittsburgh, 15213', 'Graduate', 30000),
('sairam', 'University of Pittsburgh', '12, Oakland, Pittsburgh, 15213', 'Student', 30000),
('vaish7789', 'University of Pittsburgh', '708, William Pitt Union, Pittsburgh, 15213', 'Graduate', 25000);

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE IF NOT EXISTS `userinfo` (
  `Username` varchar(15) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `MiddleName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Gender` varchar(12) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `DOB` date NOT NULL,
  `SSN` varchar(15) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`Username`, `Password`, `FirstName`, `MiddleName`, `LastName`, `Gender`, `Address`, `Email`, `DOB`, `SSN`) VALUES
('Pavi87', 'pavi87', 'Pavithra', '', 'Krishnan', 'Female', '12, Oakland, Pittsburgh, 15213', 'pavi@gmail.com', '1987-12-30', '345-56-6789'),
('praveen1788', 'prn1788', 'Praveen', 'Narayanan', 'Kumanan', 'male', 'Hardie way, Oakland, Pittsburgh', 'prn14@pitt.edu', '1988-12-17', '456-23-5678'),
('sairam', 'sairam', 'Sairam', '', 'Shri', 'Male', '1, Oakland, Pittsburgh, 15213', 'sairam@gmail.com', '1990-12-04', '345-56-6789'),
('vaish7789', 'vaish7789', 'Vaishnavi', '', 'Sivaramakrishnan', 'female', 'Melwood Avenue,Oakland,Pittsburgh', 'vas57@pitt.edu', '1989-07-07', '234-34-7564');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
