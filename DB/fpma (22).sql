-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 13-Jun-2020 às 01:04
-- Versão do servidor: 10.4.10-MariaDB
-- versão do PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `fpma`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(350) NOT NULL,
  `telemovel` varchar(20) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nome`, `email`, `telemovel`, `data`) VALUES
(133, 'Tiago Conceição', 'tigs.conceicao@hotmail.com', '935624518', '2020-06-13 00:47:47'),
(134, 'Francisco Sequeira', 'chico19@gmail.com', '915647829', '2020-06-13 00:47:47'),
(135, 'Renato Sequeira', 'rena.rodolfo@gmail.com', '924517843', '2020-06-13 00:47:47'),
(136, 'Nuno Conceição', 'nuno29@hotmail.com', '914537298', '2020-06-13 00:47:47'),
(137, 'Cláudio Ferreira', 'ferreira.claudio@gmail.com', '914527495', '2020-06-13 00:47:47'),
(138, 'André Ferreira', 'andreferreira@hotmail.com', '914365893', '2020-06-13 00:47:47'),
(139, 'Rui Andradre', 'ruizinho@gmail.com', '916745275', '2020-06-13 00:47:47'),
(140, 'Maria Albuquerque', 'mariazinha@hotmail.com', '965887025', '2020-06-13 00:47:47'),
(141, 'Joana Cortez', 'cortez.22@hotmail.com', '915648924', '2020-06-13 00:47:47'),
(142, 'Catarina Ferraz', 'ferraz10@gmail.com', '935627598', '2020-06-13 00:47:47'),
(143, 'Beatriz Matos', 'bea.borboleta@gmail.com', '963415631', '2020-06-13 00:47:47');

-- --------------------------------------------------------

--
-- Estrutura da tabela `plano`
--

DROP TABLE IF EXISTS `plano`;
CREATE TABLE IF NOT EXISTS `plano` (
  `id_plano` int(11) NOT NULL,
  `tipo_plano` varchar(100) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  `preco_plano` int(11) NOT NULL,
  PRIMARY KEY (`id_plano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `plano`
--

INSERT INTO `plano` (`id_plano`, `tipo_plano`, `data`, `preco_plano`) VALUES
(1, 'Diária Completa Individual', '2019-12-28 15:19:20', 32),
(2, 'Diária Completa Casal', '2019-12-28 15:19:20', 62),
(3, 'Diária Incompleta Individual', '2019-12-28 15:19:20', 28),
(4, 'Diária Incompleta Casal', '2020-01-05 00:59:36', 52),
(5, 'Dormida e Peq.Almoço Individual', '2020-01-05 01:01:37', 23),
(6, 'Dormida e Peq.Almoço Casal', '2020-01-05 01:02:09', 40);

-- --------------------------------------------------------

--
-- Estrutura da tabela `quarto`
--

DROP TABLE IF EXISTS `quarto`;
CREATE TABLE IF NOT EXISTS `quarto` (
  `id_quarto` int(11) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_quarto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `quarto`
--

INSERT INTO `quarto` (`id_quarto`, `tipo`, `data`, `status`) VALUES
(1, 'Individual', '2020-01-17 16:41:29', 1),
(2, 'Individual', '2020-01-17 16:41:39', 0),
(3, 'Individual', '2020-01-17 16:41:46', 0),
(5, 'Individual', '2020-01-17 16:41:56', 0),
(6, 'Individual', '2020-01-17 16:42:06', 1),
(7, 'Individual', '2020-01-17 16:42:17', 1),
(8, 'Individual', '2020-01-17 16:42:30', 0),
(10, 'Casal', '2020-01-17 16:42:54', 1),
(11, 'Casal', '2020-01-17 16:43:06', 0),
(12, 'Casal', '2020-01-17 16:43:17', 0),
(17, 'Individual', '2020-01-17 16:44:25', 0),
(18, 'Individual', '2020-01-17 16:44:35', 0),
(19, 'Individual', '2020-01-17 16:44:45', 0),
(20, 'Individual', '2020-01-17 16:44:54', 0),
(21, 'Individual', '2020-01-17 16:45:01', 0),
(22, 'Individual', '2020-01-17 16:45:11', 0),
(23, 'Individual', '2020-01-17 16:45:19', 0),
(24, 'Individual', '2020-01-17 16:45:27', 0),
(29, 'Duplo', '2020-01-17 16:50:44', 0),
(30, 'Casal + Duplo', '2020-01-17 16:51:33', 0),
(31, 'Casal', '2020-01-17 16:51:45', 0),
(32, 'Triplo', '2020-01-17 16:52:21', 0),
(34, 'Triplo', '2020-01-17 16:52:56', 0),
(35, 'Duplo', '2020-01-17 16:53:13', 0),
(36, 'Triplo', '2020-01-17 16:53:26', 0),
(37, 'Duplo', '2020-01-17 16:53:42', 1),
(38, 'Triplo', '2020-01-17 16:53:57', 0),
(40, 'Triplo', '2020-01-17 16:54:23', 0),
(41, 'Casal', '2020-01-17 16:54:41', 0),
(42, 'Triplo', '2020-01-17 16:54:52', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id_reserva` int(11) NOT NULL AUTO_INCREMENT,
  `id_quarto` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_plano` int(11) NOT NULL,
  `data_checkin` date NOT NULL,
  `data_checkout` date NOT NULL,
  `observacoes` varchar(300) NOT NULL,
  `data_alteracao` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_reserva`),
  KEY `id_quarto` (`id_quarto`,`id_cliente`,`id_plano`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_plano` (`id_plano`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `id_quarto`, `id_cliente`, `id_plano`, `data_checkin`, `data_checkout`, `observacoes`, `data_alteracao`) VALUES
(62, 31, 134, 2, '2020-06-25', '2020-06-30', '', '2020-06-13 01:03:26'),
(63, 18, 133, 1, '2020-08-13', '2020-08-18', 'Não come carne de Porco', '2020-06-13 01:03:26'),
(64, 21, 138, 5, '2020-05-06', '2020-05-09', '', '2020-06-13 01:03:26'),
(65, 41, 142, 4, '2020-07-23', '2020-07-26', 'Não jantam', '2020-06-13 01:03:26'),
(66, 31, 143, 6, '2020-07-22', '2020-06-25', '', '2020-06-13 01:03:26'),
(67, 11, 136, 2, '2020-06-15', '2020-06-17', '', '2020-06-13 01:03:26'),
(68, 5, 137, 3, '2020-06-27', '2020-06-30', 'Não Almoça', '2020-06-13 01:03:26'),
(69, 6, 141, 5, '2020-07-03', '2020-06-09', '', '2020-06-13 01:03:26'),
(70, 30, 140, 4, '2020-08-02', '2020-08-06', 'Não jantam', '2020-06-13 01:03:26'),
(71, 41, 135, 6, '2020-07-14', '2020-06-18', 'Um dos ocupantes é alérgico à lactose', '2020-06-13 01:03:26'),
(72, 24, 139, 5, '2020-06-14', '2020-06-19', '', '2020-06-13 01:03:26'),
(73, 31, 134, 2, '2020-06-25', '2020-06-30', '', '2020-06-13 01:03:30'),
(74, 18, 133, 1, '2020-08-13', '2020-08-18', 'Não come carne de Porco', '2020-06-13 01:03:30'),
(75, 21, 138, 5, '2020-05-06', '2020-05-09', '', '2020-06-13 01:03:30'),
(76, 41, 142, 4, '2020-07-23', '2020-07-26', 'Não jantam', '2020-06-13 01:03:30'),
(77, 31, 143, 6, '2020-07-22', '2020-06-25', '', '2020-06-13 01:03:30'),
(78, 11, 136, 2, '2020-06-15', '2020-06-17', '', '2020-06-13 01:03:30'),
(79, 5, 137, 3, '2020-06-27', '2020-06-30', 'Não Almoça', '2020-06-13 01:03:30'),
(80, 6, 141, 5, '2020-07-03', '2020-06-09', '', '2020-06-13 01:03:30'),
(81, 30, 140, 4, '2020-08-02', '2020-08-06', 'Não jantam', '2020-06-13 01:03:30'),
(82, 41, 135, 6, '2020-07-14', '2020-06-18', 'Um dos ocupantes é alérgico à lactose', '2020-06-13 01:03:30'),
(83, 24, 139, 5, '2020-06-14', '2020-06-19', '', '2020-06-13 01:03:30');

-- --------------------------------------------------------

--
-- Estrutura da tabela `utilizador`
--

DROP TABLE IF EXISTS `utilizador`;
CREATE TABLE IF NOT EXISTS `utilizador` (
  `id_utilizador` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_utilizador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `utilizador`
--

INSERT INTO `utilizador` (`id_utilizador`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_plano`) REFERENCES `plano` (`id_plano`),
  ADD CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`id_quarto`) REFERENCES `quarto` (`id_quarto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
