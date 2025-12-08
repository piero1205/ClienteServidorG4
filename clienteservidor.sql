-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2025 at 02:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clienteservidor`
--

-- --------------------------------------------------------

--
-- Table structure for table `proyecto`
--

CREATE TABLE `proyecto` (
  `nombre` varchar(200) NOT NULL,
  `costo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proyecto`
--

INSERT INTO `proyecto` (`nombre`, `costo`) VALUES
('Prueba1', 120000),
('Prueba2', 200000),
('Prueba3', 300000);

-- --------------------------------------------------------

--
-- Table structure for table `riesgos`
--

CREATE TABLE `riesgos` (
  `proyecto` varchar(200) NOT NULL,
  `descripcion` varchar(400) NOT NULL,
  `impacto` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `riesgos`
--

INSERT INTO `riesgos` (`proyecto`, `descripcion`, `impacto`) VALUES
('Prueba1', 'riesgo de prueba 1', 'Medio');

-- --------------------------------------------------------

--
-- Table structure for table `tareas`
--

CREATE TABLE `tareas` (
  `proyecto` varchar(200) NOT NULL,
  `id` int(100) NOT NULL,
  `estado` varchar(200) NOT NULL,
  `comentario` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `responsable` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tareas`
--

INSERT INTO `tareas` (`proyecto`, `id`, `estado`, `comentario`, `fecha`, `responsable`) VALUES
('Prueba', 1, 'En progreso', 'crear codigo', '2026-01-01', NULL),
('Prueba1', 1, 'Activa', 'Tarea #1', '2025-12-20', NULL),
('Prueba1', 3, 'Terminado', '', '2026-02-03', NULL),
('Prueba2', 2, 'En progreso', 'Hacer codigo', '2026-06-06', 'b');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `nombre` varchar(20) NOT NULL,
  `rol` varchar(20) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `contra` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`nombre`, `rol`, `correo`, `contra`) VALUES
('a', 'Admin', 'abc', '1234'),
('b', 'colaborador', 'col12', '4321'),
('c', 'Colaborador', 'ccolab12', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`nombre`);

--
-- Indexes for table `riesgos`
--
ALTER TABLE `riesgos`
  ADD PRIMARY KEY (`proyecto`,`descripcion`);

--
-- Indexes for table `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`proyecto`,`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
