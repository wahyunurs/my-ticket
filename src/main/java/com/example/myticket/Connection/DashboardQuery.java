package com.example.myticket.Connection;

import com.example.myticket.Model.DashboardModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardQuery {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    public DashboardModel getDashboardData() {
        int dataPerjalananCount = getCount("SELECT COUNT(*) FROM data_perjalanan");
        int dataTiketCount = getCount("SELECT COUNT(*) FROM data_tiket");
        int pemesananTiketCount = getCount("SELECT COUNT(*) FROM pemesanan_tiket");

        return new DashboardModel(dataPerjalananCount, dataTiketCount, pemesananTiketCount);
    }

    private int getCount(String query) {
        try (PreparedStatement preparedStatement = connectDB.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
