-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Jul 2024 pada 20.55
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myticket`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_perjalanan`
--

CREATE TABLE `data_perjalanan` (
  `id` int(11) NOT NULL,
  `rute` varchar(45) NOT NULL,
  `jadwal` date NOT NULL,
  `asal` varchar(45) NOT NULL,
  `tujuan` varchar(45) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_perjalanan`
--

INSERT INTO `data_perjalanan` (`id`, `rute`, `jadwal`, `asal`, `tujuan`, `harga`) VALUES
(1, 'Jakarta-Bandung', '2024-07-11', 'Jakarta', 'Bandung', 200000),
(2, 'Jakarta-Surabaya', '2024-07-11', 'Jakarta', 'Surabaya', 500000),
(3, 'Jakarta-Semarang', '2024-07-11', 'Jakarta', 'Semarang', 300000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_tiket`
--

CREATE TABLE `data_tiket` (
  `id` int(11) NOT NULL,
  `id_perjalanan` int(11) NOT NULL,
  `rute` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_tiket`
--

INSERT INTO `data_tiket` (`id`, `id_perjalanan`, `rute`, `status`, `stok`) VALUES
(1, 1, 'Jakarta-Bandung', 'Tersedia', 150),
(2, 2, 'Jakarta-Surabaya', 'Tersedia', 11);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemesanan_tiket`
--

CREATE TABLE `pemesanan_tiket` (
  `id` int(11) NOT NULL,
  `id_tiket` int(11) NOT NULL,
  `id_perjalanan` int(11) NOT NULL,
  `nama_user` varchar(45) NOT NULL,
  `jumlah_tiket` int(11) NOT NULL,
  `total_biaya` int(11) NOT NULL,
  `tanggal_pemesanan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pemesanan_tiket`
--

INSERT INTO `pemesanan_tiket` (`id`, `id_tiket`, `id_perjalanan`, `nama_user`, `jumlah_tiket`, `total_biaya`, `tanggal_pemesanan`) VALUES
(1, 1, 1, 'Wahyu', 1, 200000, '2024-07-10');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `data_perjalanan`
--
ALTER TABLE `data_perjalanan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `data_tiket`
--
ALTER TABLE `data_tiket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_perjalanan` (`id_perjalanan`);

--
-- Indeks untuk tabel `pemesanan_tiket`
--
ALTER TABLE `pemesanan_tiket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_tiket` (`id_tiket`),
  ADD KEY `fk_id_perjalanan` (`id_perjalanan`) USING BTREE;

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `data_perjalanan`
--
ALTER TABLE `data_perjalanan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `data_tiket`
--
ALTER TABLE `data_tiket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `pemesanan_tiket`
--
ALTER TABLE `pemesanan_tiket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `data_tiket`
--
ALTER TABLE `data_tiket`
  ADD CONSTRAINT `fk_id_perjalanan` FOREIGN KEY (`id_perjalanan`) REFERENCES `data_perjalanan` (`id`);

--
-- Ketidakleluasaan untuk tabel `pemesanan_tiket`
--
ALTER TABLE `pemesanan_tiket`
  ADD CONSTRAINT `fk_id_perjalanan_new` FOREIGN KEY (`id_perjalanan`) REFERENCES `data_perjalanan` (`id`),
  ADD CONSTRAINT `fk_id_tiket` FOREIGN KEY (`id_tiket`) REFERENCES `data_tiket` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
