package com.example.myticket.Model;

public class DataTiketModel {
    private Integer id;
    private Integer idPerjalanan;
    private String rute;
    private String status;
    private Integer stok;

    public DataTiketModel(Integer id, Integer IdPerjalanan, String rute, String status, Integer stok) {
        this.id = id;
        this.idPerjalanan = idPerjalanan;
        this.rute = rute;
        this.status = status;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPerjalanan() {
        return id;
    }

    public void setIdPerjalanan(int id) {
        this.id = id;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
