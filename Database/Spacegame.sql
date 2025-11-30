-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 27 Kas 2025, 23:46:49
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `demo`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `Spacegame`
--

CREATE TABLE `Spacegame` (
  `İsim` text NOT NULL,
  `MAXpuan` int(11) NOT NULL,
  `Şifre` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `Spacegame`
--

INSERT INTO `Spacegame` (`İsim`, `MAXpuan`, `Şifre`) VALUES
('admin', 14995, '123'),
('kaanbaba', 1300, 'bltakan06m'),
('Testuser1', 358, 'abcdef'),
('Testuser2', 441, 'abcdef'),
('Testuser3', 131, 'abcdef'),
('Testuser4', 704, 'abcdef'),
('TestUser5', 0, 'qwerdf'),
('testuser6', 0, 'qwe'),
('turkenes', 1803, 'osterwolde'),
('ardaktin', 204, 'sertsikici'),
('playboicarti', 143, 'burakbaba'),
('leva', 85, 'leva1234'),
('naz', 108, 'naz4141'),
('mert', 144, 'mertaga');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
