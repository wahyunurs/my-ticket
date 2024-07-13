package com.example.myticket.Model;

public class DashboardModel {
    private int dataPerjalananCount;
    private int dataTiketCount;
    private int pemesananTiketCount;

    public DashboardModel(int dataPerjalananCount, int dataTiketCount, int pemesananTiketCount) {
        this.dataPerjalananCount = dataPerjalananCount;
        this.dataTiketCount = dataTiketCount;
        this.pemesananTiketCount = pemesananTiketCount;
    }

    public int getDataPerjalananCount() {
        return dataPerjalananCount;
    }

    public void setDataPerjalananCount(int dataPerjalananCount) {
        this.dataPerjalananCount = dataPerjalananCount;
    }

    public int getDataTiketCount() {
        return dataTiketCount;
    }

    public void setDataTiketCount(int dataTiketCount) {
        this.dataTiketCount = dataTiketCount;
    }

    public int getPemesananTiketCount() {
        return pemesananTiketCount;
    }

    public void setPemesananTiketCount(int pemesananTiketCount) {
        this.pemesananTiketCount = pemesananTiketCount;
    }
}
