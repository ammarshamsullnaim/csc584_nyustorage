-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2023 at 05:33 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `courier`
--

CREATE TABLE `courier` (
  `courier_id` varchar(10) NOT NULL,
  `courier_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courier`
--

INSERT INTO `courier` (`courier_id`, `courier_name`) VALUES
('1', 'Shopee Express'),
('2', 'Poslaju'),
('3', 'CityLink');

-- --------------------------------------------------------

--
-- Table structure for table `parcel`
--

CREATE TABLE `parcel` (
  `tracking_num` varchar(10) NOT NULL,
  `receipient_name` varchar(50) NOT NULL,
  `tel_num` varchar(15) NOT NULL,
  `received_date` date NOT NULL,
  `collection_date` date NOT NULL,
  `weight` int(5) NOT NULL,
  `remark` varchar(50) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `shelf_id` varchar(10) NOT NULL,
  `courier_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parcel`
--

INSERT INTO `parcel` (`tracking_num`, `receipient_name`, `tel_num`, `received_date`, `collection_date`, `weight`, `remark`, `userId`, `shelf_id`, `courier_id`) VALUES
('T001', 'Arisa', '0193214923', '2023-08-04', '2023-08-05', 2, 'Received', '001', '2', '2'),
('T002', 'Amsyar', '01923271', '2023-08-03', '2023-08-11', 1, 'Collected', '001', '1', '1'),
('T003', 'Rosmah', '23232', '2023-08-03', '2023-08-05', 1, 'Collected', '001', '2', '1');

-- --------------------------------------------------------

--
-- Table structure for table `shelf`
--

CREATE TABLE `shelf` (
  `shelf_id` varchar(11) NOT NULL,
  `shelf_row` varchar(4) NOT NULL,
  `shelf_column` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `shelf`
--

INSERT INTO `shelf` (`shelf_id`, `shelf_row`, `shelf_column`) VALUES
('1', 'AA', 1),
('2', 'BB', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` varchar(10) NOT NULL,
  `userPassword` varchar(20) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `userIC` varchar(20) NOT NULL,
  `userPhoneNo` varchar(20) NOT NULL,
  `userAddress` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userPassword`, `userName`, `userIC`, `userPhoneNo`, `userAddress`) VALUES
('001', 'user01', 'Luqman', '090803010231', '0123239148', 'Kedah'),
('099', '99', 'sembilannn', '009981111', '01911111', 'gombakkk');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courier`
--
ALTER TABLE `courier`
  ADD PRIMARY KEY (`courier_id`);

--
-- Indexes for table `parcel`
--
ALTER TABLE `parcel`
  ADD PRIMARY KEY (`tracking_num`);

--
-- Indexes for table `shelf`
--
ALTER TABLE `shelf`
  ADD PRIMARY KEY (`shelf_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
