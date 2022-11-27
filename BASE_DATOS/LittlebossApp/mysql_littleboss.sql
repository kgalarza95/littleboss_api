-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 06, 2021 at 08:45 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysql_littleboss`
--

-- --------------------------------------------------------

--
-- Table structure for table `articulos`
--

CREATE TABLE `articulos` (
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `codigo` int(11) NOT NULL,
  `precio` double NOT NULL,
  `cantidad` int(3) NOT NULL,
  `descripcion` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `articulos`
--

INSERT INTO `articulos` (`nombre`, `codigo`, `precio`, `cantidad`, `descripcion`) VALUES
('', 0, 0, 0, ''),
('1', 1, 1, 1, '1'),
('2', 2, 22, 2, '2'),
('', 3, 0, 3, '3'),
('4', 4, 4, 4, '4'),
('6', 6, 6, 6, '6'),
('7', 7, 7, 7, '7'),
('8', 8, 8, 8, '8'),
('9', 9, 9, 9, '9');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `codigo` int(20) NOT NULL,
  `nombreCliente` varchar(45) NOT NULL,
  `apellidoCliente` varchar(45) NOT NULL,
  `correoCliente` varchar(50) NOT NULL,
  `celularCliente` varchar(11) NOT NULL,
  `ciudadCliente` varchar(45) NOT NULL,
  `ubicacionCliente` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombreCliente`, `apellidoCliente`, `correoCliente`, `celularCliente`, `ciudadCliente`, `ubicacionCliente`) VALUES
(1, '1', '1', '1', '1', '1', '1'),
(987654321, 'luis', 'vivas', 'luis@hotmai.com', '0987654321', 'guayaquil', 'urdesa');

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `fecharegistrop` varchar(20) NOT NULL,
  `fechaEntrega` varchar(20) NOT NULL,
  `cantidad` varchar(20) NOT NULL,
  `costoEnvio` varchar(20) NOT NULL,
  `cliente` varchar(20) NOT NULL,
  `pagototal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pedido`
--

INSERT INTO `pedido` (`codigo`, `descripcion`, `fecharegistrop`, `fechaEntrega`, `cantidad`, `costoEnvio`, `cliente`, `pagototal`) VALUES
(1, '1', '1', '1', '1', '1', '', '1'),
(2, '2', '2', '2', '2', '2', '', '2'),
(3, '3', '3', '3', '3', '3', '', '3'),
(4, '4', '4', '4', '4', '4', '4', '4'),
(1234, '1 tv', '1-9-21', '5-9-21', '1', '500', 'luis vivas', '512');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `Nombres` text NOT NULL,
  `Apellidos` text NOT NULL,
  `codigo` varchar(300) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`Nombres`, `Apellidos`, `codigo`, `username`, `password`) VALUES
('luis', 'vivas', '0987654321', 'luis@hotmail.com', '$2y$10$7FeOfKIny4uiVvjNUsKRhe1qYOKGXwTCptEdr12TOG2XAo8Ah2HLC');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `codigo` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
