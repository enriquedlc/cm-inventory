-- phpMyAdmin SQL Dump
    -- version 5.1.0
    -- https://www.phpmyadmin.net/
    --

    -- Host: 127.0.0.1
    -- Generation Time: May 31, 2021 at 05:45 PM
    -- Server version: 10.4.19-MariaDB
    -- PHP Version: 8.0.6
SET
    SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION
    ;
SET
    time_zone = "+00:00";
    /*!40101
SET
    @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
    /*!40101
SET
    @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
    /*!40101
SET
    @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
    /*!40101
SET NAMES
    utf8mb4 */;
    --

    -- Database: `inventario`
    --

    -- --------------------------------------------------------
    --

    -- Table structure for table `aula`
    --

CREATE TABLE IF NOT EXISTS `aula`(
    `IdAula` INT(11) NOT NULL,
    `Numeracion` VARCHAR(5) NOT NULL,
    `Descripcion` VARCHAR(255) NOT NULL,
    `IP` VARCHAR(15) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
-- --------------------------------------------------------
--

-- Table structure for table `marcaje`
--

CREATE TABLE IF NOT EXISTS `marcaje`(
    `IdMarcaje` INT(11) NOT NULL,
    `IdProducto` INT(11) NOT NULL,
    `IdAula` INT(11) NOT NULL,
    `Tipo` INT(11) NOT NULL,
    `TimeStamp` DATETIME NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
-- --------------------------------------------------------
--

-- Table structure for table `productos`
--

CREATE TABLE IF NOT EXISTS `productos`(
    `IdProducto` INT(11) NOT NULL,
    `Descripcion` VARCHAR(255) NOT NULL,
    `EAN13` INT(11) NOT NULL,
    `keyRFID` VARCHAR(10) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
--

-- Indexes for dumped tables
--

--

-- Indexes for table `aula`
--

ALTER TABLE
    `aula` ADD PRIMARY KEY(`IdAula`);
    --

    -- Indexes for table `marcaje`
    --

ALTER TABLE
    `marcaje` ADD PRIMARY KEY(`IdMarcaje`),
    ADD KEY `FK_Aula`(`IdAula`),
    ADD KEY `FK_Producto`(`IdProducto`);
    --

    -- Indexes for table `productos`
    --

ALTER TABLE
    `productos` ADD PRIMARY KEY(`IdProducto`);
    --

    -- AUTO_INCREMENT for dumped tables
    --

    --

    -- AUTO_INCREMENT for table `aula`
    --

ALTER TABLE
    `aula` MODIFY `IdAula` INT(11) NOT NULL AUTO_INCREMENT;
    --

    -- AUTO_INCREMENT for table `marcaje`
    --

ALTER TABLE
    `marcaje` MODIFY `IdMarcaje` INT(11) NOT NULL AUTO_INCREMENT;
    --

    -- Constraints for dumped tables
    --

    -- Constraints for table `marcaje`
    --

ALTER TABLE
    `marcaje` ADD CONSTRAINT `FK_Aula` FOREIGN KEY(`IdAula`) REFERENCES `aula`(`IdAula`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `FK_Producto` FOREIGN KEY(`IdProducto`) REFERENCES `productos`(`IdProducto`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT
    ;
    /*!40101
SET
    CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
    /*!40101
SET
    CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
    /*!40101
SET
    COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
CREATE DOMAIN dom_estados AS CHAR(20) CONSTRAINT estados_validos CHECK
    (
    VALUE
        IN(
            ‘Nueva’,
            ’Se necesitan más datos’,
            ’Aceptada’,
            ’Confirmada’,
            ’Resuelta’,
            ‘Cerrada’
        )
)