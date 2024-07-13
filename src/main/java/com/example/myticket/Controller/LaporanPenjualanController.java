package com.example.myticket.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

public class LaporanPenjualanController implements Initializable {

    @FXML
    private ImageView logoImage;
    @FXML
    private ImageView profilImage;
    @FXML
    private Label dashboardLabel;
    @FXML
    private Label dataPerjalananLabel;
    @FXML
    private Label pemesananTiketLabel;
    @FXML
    private Label dataTiketLabel;
    @FXML
    private Label laporanPenjualanLabel;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("img/logo.png");
        Image loginImage = new Image(logoFile.toURI().toString());
        logoImage.setImage(loginImage);

        File profilFile = new File("img/profil.png");
        Image profilImageFile = new Image(profilFile.toURI().toString());
        profilImage.setImage(profilImageFile);

        dashboardLabel.setOnMouseClicked(event -> loadFXML(event, "/com/example/myticket/dashboard.fxml", "Dashboard"));
        dataPerjalananLabel.setOnMouseClicked(event -> loadFXML(event, "/com/example/myticket/dataperjalanan.fxml", "Data Perjalanan"));
        pemesananTiketLabel.setOnMouseClicked(event -> loadFXML(event, "/com/example/myticket/pemesanantiket.fxml", "Pemesanan Tiket"));
        dataTiketLabel.setOnMouseClicked(event -> loadFXML(event, "/com/example/myticket/datatiket.fxml", "Data Tiket"));
        laporanPenjualanLabel.setOnMouseClicked(event -> loadFXML(event, "/com/example/myticket/laporanpenjualan.fxml", "Laporan Penjualan"));
    }

    private void loadFXML(MouseEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newContent = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(newContent));
            stage.setTitle(title);
            stage.show();

            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
