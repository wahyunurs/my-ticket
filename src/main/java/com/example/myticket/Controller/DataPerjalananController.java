package com.example.myticket.Controller;

import com.example.myticket.Connection.DataPerjalananQuery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.io.IOException;

import com.example.myticket.Model.DataPerjalananModel;

public class DataPerjalananController implements Initializable {

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
    @FXML
    private TableView<DataPerjalananModel> dataPerjalananTable;
    @FXML
    public TableColumn<DataPerjalananModel, Integer> colId;
    @FXML
    public TableColumn<DataPerjalananModel, String> colRute;
    @FXML
    public TableColumn<DataPerjalananModel, LocalDate> colJadwal;
    @FXML
    public TableColumn<DataPerjalananModel, String> colAsal;
    @FXML
    public TableColumn<DataPerjalananModel, String> colTujuan;
    @FXML
    public TableColumn<DataPerjalananModel, Integer> colHarga;
    @FXML
    private TextField ruteTextField;
    @FXML
    private DatePicker jadwalDatePicker;
    @FXML
    private TextField asalTextField;
    @FXML
    private TextField tujuanTextField;
    @FXML
    private TextField hargaTextField;

    private DataPerjalananModel dataPerjalananModel;

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

        showDataPerjalanan();
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

    @FXML
    private void showDataPerjalanan() {
        DataPerjalananQuery query = new DataPerjalananQuery();
        ObservableList<DataPerjalananModel> list = query.getDataPerjalananList();
        colId.setCellValueFactory(new PropertyValueFactory<DataPerjalananModel, Integer>("id"));
        colRute.setCellValueFactory(new PropertyValueFactory<DataPerjalananModel, String>("rute"));
        colJadwal.setCellValueFactory(new PropertyValueFactory<DataPerjalananModel, LocalDate>("jadwal"));
        colAsal.setCellValueFactory(new PropertyValueFactory<DataPerjalananModel, String>("asal"));
        colTujuan.setCellValueFactory(new PropertyValueFactory<DataPerjalananModel, String>("tujuan"));
        colHarga.setCellValueFactory(new PropertyValueFactory<DataPerjalananModel, Integer>("harga"));
        dataPerjalananTable.setItems(list);
    }

    @FXML
    public void mouseClicked (MouseEvent event) {
        try {
            DataPerjalananModel dataPerjalananModel = dataPerjalananTable.getSelectionModel().getSelectedItem();
            dataPerjalananModel = new DataPerjalananModel(dataPerjalananModel.getId(), dataPerjalananModel.getRute(), dataPerjalananModel.getJadwal(), dataPerjalananModel.getAsal(), dataPerjalananModel.getTujuan(), dataPerjalananModel.getHarga());
            this.dataPerjalananModel = dataPerjalananModel;
            ruteTextField.setText(dataPerjalananModel.getRute());
            jadwalDatePicker.setValue(dataPerjalananModel.getJadwal());
            asalTextField.setText(dataPerjalananModel.getAsal());
            tujuanTextField.setText(dataPerjalananModel.getTujuan());
            hargaTextField.setText(String.valueOf(dataPerjalananModel.getHarga()));
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void addDataPerjalanan() {
        try {
            String rute = ruteTextField.getText();
            LocalDate jadwal = jadwalDatePicker.getValue();
            String asal = asalTextField.getText();
            String tujuan = tujuanTextField.getText();
            int harga = Integer.parseInt(hargaTextField.getText());

            DataPerjalananModel dataPerjalananModel = new DataPerjalananModel(0, rute, jadwal, asal, tujuan, harga); // id 0 untuk auto increment
            DataPerjalananQuery query = new DataPerjalananQuery();
            query.addDataPerjalanan(dataPerjalananModel);

            // Refresh table view to show the new data
            showDataPerjalanan();

            // Clear text fields
            ruteTextField.clear();
            jadwalDatePicker.setValue(null);
            asalTextField.clear();
            tujuanTextField.clear();
            hargaTextField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateDataPerjalanan() {
        try {
            DataPerjalananModel dataPerjalananModel = new DataPerjalananModel(
                    this.dataPerjalananModel.getId(),
                    ruteTextField.getText(),
                    jadwalDatePicker.getValue(),
                    asalTextField.getText(),
                    tujuanTextField.getText(),
                    Integer.parseInt(hargaTextField.getText())
            );
            DataPerjalananQuery query = new DataPerjalananQuery();
            query.updateDataPerjalanan(dataPerjalananModel);

            showDataPerjalanan();

            // Clear text fields
            ruteTextField.clear();
            jadwalDatePicker.setValue(null);
            asalTextField.clear();
            tujuanTextField.clear();
            hargaTextField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteDataPerjalanan() {
        try {
            DataPerjalananModel dataPerjalananModel = new DataPerjalananModel(
                    this.dataPerjalananModel.getId(),
                    ruteTextField.getText(),
                    jadwalDatePicker.getValue(),
                    asalTextField.getText(),
                    tujuanTextField.getText(),
                    Integer.parseInt(hargaTextField.getText())
            );
            DataPerjalananQuery query = new DataPerjalananQuery();
            query.deleteDataPerjalanan(dataPerjalananModel);

            showDataPerjalanan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
