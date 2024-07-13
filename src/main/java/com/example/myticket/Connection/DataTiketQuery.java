package com.example.myticket.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import com.example.myticket.Model.DataTiketModel;

public class DataTiketQuery {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public ObservableList<DataTiketModel> getDataTiketList() {
        ObservableList<DataTiketModel> dataTiketList = FXCollections.observableArrayList();

        String sql = "SELECT dt.id, dt.id_perjalanan, dt.rute, dt.status, dt.stok " +
                "FROM data_tiket dt " +
                "JOIN data_perjalanan dp ON dt.id_perjalanan = dp.id";

        try (Statement statement = connectDB.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idPerjalanan = rs.getInt("id_perjalanan");
                String rute = rs.getString("rute");
                String status = rs.getString("status");
                int stok = rs.getInt("stok");

                DataTiketModel dataTiket = new DataTiketModel(id, idPerjalanan, rute, status, stok);
                dataTiketList.add(dataTiket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTiketList;
    }

    public ObservableList<String> getRuteList() {
        ObservableList<String> ruteList = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT rute FROM data_perjalanan";

        try (Statement statement = connectDB.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String rute = rs.getString("rute");
                ruteList.add(rute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruteList;
    }

    public void addDataTiket(int idPerjalanan, String rute, String status, int stok) {
        String sql = "INSERT INTO data_tiket (id_perjalanan, rute, status, stok) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connectDB.prepareStatement(sql)) {
            pstmt.setInt(1, idPerjalanan);
            pstmt.setString(2, rute);
            pstmt.setString(3, status);
            pstmt.setInt(4, stok);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdPerjalananByRute(String rute) {
        String sql = "SELECT id FROM data_perjalanan WHERE rute = ?";
        try (PreparedStatement pstmt = connectDB.prepareStatement(sql)) {
            pstmt.setString(1, rute);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Sebaiknya menggunakan logging
        }
        return -1;
    }

    public void updateDataTiket(DataTiketModel dataTiketModel) {
        String query = "UPDATE data_tiket " +
                "SET id_perjalanan = ?, " +
                "rute = ?, " +
                "status = ?, " +
                "stok = ? " +
                "WHERE id = ?";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
            preparedStatement.setInt(1, dataTiketModel.getIdPerjalanan());
            preparedStatement.setString(2, dataTiketModel.getRute());
            preparedStatement.setString(3, dataTiketModel.getStatus());
            preparedStatement.setInt(4, dataTiketModel.getStok());
            preparedStatement.setInt(5, dataTiketModel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataTiket(DataTiketModel dataTiketModel) {
        String query = "DELETE FROM data_tiket WHERE id = ?";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
            preparedStatement.setInt(1, dataTiketModel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
