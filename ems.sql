-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2016 at 01:48 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ems`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `name`, `password`) VALUES
('123456', 'Name', 'Password'),
('9', 'Tu', 'Tu9');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `item_id` int(20) NOT NULL AUTO_INCREMENT,
  `category` int(20) NOT NULL,
  `brand` int(20) NOT NULL,
  `serial_no` varchar(200) NOT NULL,
  `property_no` varchar(200) NOT NULL,
  `available` tinyint(1) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `category`, `brand`, `serial_no`, `property_no`, `available`) VALUES
(1, 1, 1, 'NSX-190', 'PROJ1', 1),
(2, 2, 2, 'INSP5520', '50N2A', 1);

-- --------------------------------------------------------

--
-- Table structure for table `item_brand`
--

CREATE TABLE IF NOT EXISTS `item_brand` (
  `brand_id` int(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(200) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `item_brand`
--

INSERT INTO `item_brand` (`brand_id`, `brand_name`) VALUES
(1, 'Panasonic'),
(2, 'Dell'),
(3, 'HP'),
(4, 'Coloud'),
(5, 'CD-R King');

-- --------------------------------------------------------

--
-- Table structure for table `item_category`
--

CREATE TABLE IF NOT EXISTS `item_category` (
  `category_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `item_category`
--

INSERT INTO `item_category` (`category_id`, `category_name`) VALUES
(1, 'Projector'),
(2, 'Laptop'),
(3, 'Speakers');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `request_id` int(20) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(200) NOT NULL,
  `item_id` varchar(200) NOT NULL,
  `room` varchar(200) NOT NULL,
  `date_req` varchar(200) NOT NULL,
  `date_res` varchar(200) NOT NULL,
  `date_deadline` varchar(200) NOT NULL,
  `declined` tinyint(1) NOT NULL,
  `allowed` tinyint(1) NOT NULL,
  `returned` tinyint(1) NOT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `request_rooms`
--

CREATE TABLE IF NOT EXISTS `request_rooms` (
  `room_id` int(20) NOT NULL AUTO_INCREMENT,
  `room` varchar(20) NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id` (`room`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `request_rooms`
--

INSERT INTO `request_rooms` (`room_id`, `room`) VALUES
(1, '501'),
(2, '502'),
(3, '503'),
(4, 'CL1'),
(5, 'CL2'),
(6, 'MMA3');

-- --------------------------------------------------------

--
-- Table structure for table `return_item`
--

CREATE TABLE IF NOT EXISTS `return_item` (
  `reserve_id` int(11) NOT NULL,
  `date_req` varchar(200) NOT NULL,
  `date_ret` varchar(200) NOT NULL,
  `date_deadline` varchar(200) NOT NULL,
  `deadline_exceeded` tinyint(1) NOT NULL,
  `admin_id` varchar(200) NOT NULL,
  `item_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `student_id` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `course` varchar(20) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`student_id`, `first_name`, `last_name`, `course`) VALUES
('201301132', 'Michael', 'Lazo', '3'),
('201301146', 'Ethan', 'Sonza', '3'),
('201301266', 'Joshua', 'Pelingon', '3');

-- --------------------------------------------------------

--
-- Table structure for table `user_course`
--

CREATE TABLE IF NOT EXISTS `user_course` (
  `course_id` int(20) NOT NULL AUTO_INCREMENT,
  `course` varchar(200) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course` (`course`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user_course`
--

INSERT INTO `user_course` (`course_id`, `course`) VALUES
(1, 'FD'),
(2, 'GD'),
(3, 'SE');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
