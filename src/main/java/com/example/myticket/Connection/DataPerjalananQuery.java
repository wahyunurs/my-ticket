package com.example.myticket.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import com.example.myticket.Model.DataPerjalananModel;

public class DataPerjalananQuery {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    public void addDataPerjalanan(DataPerjalananModel dataPerjalananModel) {
        String query = "INSERT INTO data_perjalanan (rute, jadwal, asal, tujuan, harga) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
            preparedStatement.setString(1, dataPerjalananModel.getRute());
            preparedStatement.setDate(2, Date.valueOf(dataPerjalananModel.getJadwal()));
            preparedStatement.setString(3, dataPerjalananModel.getAsal());
            preparedStatement.setString(4, dataPerjalananModel.getTujuan());
            preparedStatement.setInt(5, dataPerjalananModel.getHarga());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<DataPerjalananModel> getDataPerjalananList() {
        ObservableList<DataPerjalananModel> DataPerjalananList = FXCollections.observableArrayList();

        String query = "SELECT id, rute, jadwal, asal, tujuan, harga FROM data_perjalanan ORDER BY id";

        try (Statement statement = connectDB.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                DataPerjalananModel dp = new DataPerjalananModel(
                        resultSet.getInt("id"),
                        resultSet.getString("rute"),
                        resultSet.getDate("jadwal").toLocalDate(),
                        resultSet.getString("asal"),
                        resultSet.getString("tujuan"),
                        resultSet.getInt("harga")
                );
                DataPerjalananList.add(dp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DataPerjalananList;
    }

    public void updateDataPerjalanan(DataPerjalananModel dataPerjalananModel) {
        String query = "UPDATE data_perjalanan " +
                "SET rute = ?, " +
                "jadwal = ?, " +
                "asal = ?, " +
                "tujuan = ?, " +
                "harga = ? " +
                "WHERE id = ?";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
            preparedStatement.setString(1, dataPerjalananModel.getRute());
            preparedStatement.setDate(2, Date.valueOf(dataPerjalananModel.getJadwal()));
            preparedStatement.setString(3, dataPerjalananModel.getAsal());
            preparedStatement.setString(4, dataPerjalananModel.getTujuan());
            preparedStatement.setInt(5, dataPerjalananModel.getHarga());
            preparedStatement.setInt(6, dataPerjalananModel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataPerjalanan(DataPerjalananModel dataPerjalananModel) {
        String query = "DELETE FROM data_perjalanan WHERE id = ?";
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {
            preparedStatement.setInt(1, dataPerjalananModel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
