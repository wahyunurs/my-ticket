package com.example.myticket.Model;

import java.time.LocalDate;

public class DataPerjalananModel {
    private Integer id;
    private String rute;
    private LocalDate jadwal;
    private String asal;
    private String tujuan;
    private Integer harga;

    public DataPerjalananModel(Integer id, String rute, LocalDate jadwal, String asal, String tujuan, Integer harga) {
        this.id = id;
        this.rute = rute;
        this.jadwal = jadwal;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
    }

    public DataPerjalananModel(String rute, LocalDate jadwal, String asal, String tujuan, Integer harga) {
        this.rute = rute;
        this.jadwal = jadwal;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public LocalDate getJadwal() {
        return jadwal;
    }

    public void setJadwal(LocalDate jadwal) {
        this.jadwal = jadwal;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
