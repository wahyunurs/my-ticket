package com.example.myticket.Connection;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import com.example.myticket.Model.PemesananTiketModel;

public class PemesananTiketQuery {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public ObservableList<PemesananTiketModel> getPemesananTiketList() {
        ObservableList<PemesananTiketModel> PemesananTiketList = FXCollections.observableArrayList();

        String sql = "SELECT pt.id, dt.id AS id_tiket, dp.id AS id_perjalanan, pt.nama_user, pt.jumlah_tiket, pt.total_biaya, pt.tanggal_pemesanan " +
                "FROM pemesanan_tiket pt " +
                "JOIN data_tiket dt ON pt.id_tiket = dt.id " +
                "JOIN data_perjalanan dp ON pt.id_perjalanan = dp.id";

        try (Statement statement = connectDB.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                PemesananTiketModel pt = new PemesananTiketModel(
                        rs.getInt("id"),
                        rs.getInt("id_tiket"),
                        rs.getInt("id_perjalanan"),
                        rs.getString("nama_user"),
                        rs.getInt("jumlah_tiket"),
                        rs.getInt("total_biaya"),
                        rs.getDate("tanggal_pemesanan").toLocalDate()
                );
                PemesananTiketList.add(pt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PemesananTiketList;
    }
}
