package com.example.myticket.Model;

import java.time.LocalDate;

public class PemesananTiketModel {
    private Integer id;
    private Integer idTiket;
    private Integer idPerjalanan;
    private String namaUser;
    private Integer jumlahTiket;
    private Integer totalBiaya;
    private LocalDate tanggalPemesanan;

    public PemesananTiketModel(Integer id, Integer idTiket, Integer idPerjalanan, String namaUser, Integer jumlahTiket, Integer totalBiaya, LocalDate tanggalPemesanan) {
        this.id = id;
        this.idTiket = idTiket;
        this.idPerjalanan = idPerjalanan;
        this.namaUser = namaUser;
        this.jumlahTiket = jumlahTiket;
        this.totalBiaya = totalBiaya;
        this.tanggalPemesanan = tanggalPemesanan;
    }

    // Getter and Setter for id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and Setter for idTiket
    public Integer getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(Integer idTiket) {
        this.idTiket = idTiket;
    }

    // Getter and Setter for idPerjalanan
    public Integer getIdPerjalanan() {
        return idPerjalanan;
    }

    public void setIdPerjalanan(Integer idPerjalanan) {
        this.idPerjalanan = idPerjalanan;
    }

    // Getter and Setter for namaUser
    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    // Getter and Setter for jumlahTiket
    public Integer getJumlahTiket() {
        return jumlahTiket;
    }

    public void setJumlahTiket(Integer jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }

    // Getter and Setter for totalBiaya
    public Integer getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(Integer totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    // Getter and Setter for tanggalPemesanan
    public LocalDate getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(LocalDate tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }
}
