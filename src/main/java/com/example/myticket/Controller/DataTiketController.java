package com.example.myticket.Controller;

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
import javafx.collections.FXCollections;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import com.example.myticket.Model.DataTiketModel;
import com.example.myticket.Connection.DataTiketQuery;

public class DataTiketController implements Initializable {

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
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<DataTiketModel> dataTiketTable;
    @FXML
    public TableColumn<DataTiketModel, Integer> colId;
    @FXML
    public TableColumn<DataTiketModel, Integer> colIdPerjalanan;
    @FXML
    public TableColumn<DataTiketModel, String> colRute;
    @FXML
    public TableColumn<DataTiketModel, String> colStatus;
    @FXML
    public TableColumn<DataTiketModel, Integer> colStok;
    @FXML
    private ComboBox<String> ruteComboBox;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextField stokTextField;
    @FXML
    private Label notificationLabel;

    private DataTiketQuery dataTiketQuery = new DataTiketQuery();
    private DataTiketModel dataTiketModel;

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

        showDataTiket();

        ObservableList<String> ruteList = dataTiketQuery.getRuteList();
        ruteComboBox.setItems(ruteList);

        // Inisialisasi status ComboBox dengan nilai statis
        statusComboBox.setItems(FXCollections.observableArrayList("Tersedia", "Habis"));
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
    private void showNotification(ObservableList<DataTiketModel> list) {
        for (DataTiketModel dataTiket : list) {
            if (dataTiket.getStok() <= 10) {
                notificationLabel.setText("Tiket ID " + dataTiket.getId() + " hampir habis");
                return;
            }
        }
        notificationLabel.setText("");
    }

    @FXML
    private void showDataTiket() {
        DataTiketQuery query = new DataTiketQuery();
        ObservableList<DataTiketModel> list = query.getDataTiketList();
        colId.setCellValueFactory(new PropertyValueFactory<DataTiketModel, Integer>("id"));
        colIdPerjalanan.setCellValueFactory(new PropertyValueFactory<DataTiketModel, Integer>("idPerjalanan"));
        colRute.setCellValueFactory(new PropertyValueFactory<DataTiketModel, String>("rute"));
        colStatus.setCellValueFactory(new PropertyValueFactory<DataTiketModel, String>("status"));
        colStok.setCellValueFactory(new PropertyValueFactory<DataTiketModel, Integer>("stok"));
        dataTiketTable.setItems(list);

        showNotification(list);
    }

    @FXML
    public void mouseClicked(MouseEvent event) {
        try {
            DataTiketModel selectedTiket = dataTiketTable.getSelectionModel().getSelectedItem();
            if (selectedTiket != null) {
                this.dataTiketModel = selectedTiket;

                // Populate the ComboBox with all available routes
                ObservableList<String> ruteList = dataTiketQuery.getRuteList();
                ruteComboBox.setItems(ruteList);

                // Select the current route of the selected ticket
                ruteComboBox.getSelectionModel().select(selectedTiket.getRute());

                // Set status and stock fields
                statusComboBox.getSelectionModel().select(selectedTiket.getStatus());
                stokTextField.setText(String.valueOf(selectedTiket.getStok()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void addDataTiket() {
        try {
            String selectedRute = ruteComboBox.getSelectionModel().getSelectedItem();
            String selectedStatus = statusComboBox.getSelectionModel().getSelectedItem();
            int stok = Integer.parseInt(stokTextField.getText());

            int idPerjalanan = dataTiketQuery.getIdPerjalananByRute(selectedRute);
            if (idPerjalanan != -1) {
                dataTiketQuery.addDataTiket(idPerjalanan, selectedRute, selectedStatus, stok);

                // Refresh table view to show the new data
                showDataTiket();

                // Clear fields
                ruteComboBox.getSelectionModel().clearSelection();
                statusComboBox.getSelectionModel().clearSelection();
                stokTextField.clear();
            } else {
                System.out.println("Rute tidak ditemukan");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan saat menambahkan tiket", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void updateDataTiket() {
        try {
            String selectedRute = ruteComboBox.getSelectionModel().getSelectedItem();
            String selectedStatus = statusComboBox.getSelectionModel().getSelectedItem();
            int stok = Integer.parseInt(stokTextField.getText());

            // Get idPerjalanan based on selectedRute
            int idPerjalanan = dataTiketQuery.getIdPerjalananByRute(selectedRute);
            if (idPerjalanan == -1) {
                showAlert("Error", "Rute tidak ditemukan", Alert.AlertType.ERROR);
                return;
            }

            // Update the existing DataTiketModel object
            dataTiketModel.setIdPerjalanan(idPerjalanan);
            dataTiketModel.setRute(selectedRute);
            dataTiketModel.setStatus(selectedStatus);
            dataTiketModel.setStok(stok);

            // Update data in the database
            dataTiketQuery.updateDataTiket(dataTiketModel);

            // Refresh table view
            showDataTiket();

            // Clear fields
            ruteComboBox.getSelectionModel().clearSelection();
            statusComboBox.getSelectionModel().clearSelection();
            stokTextField.clear();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan saat mengupdate tiket", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void deleteDataTiket() {
        try {
            dataTiketQuery.deleteDataTiket(dataTiketModel);

            // Refresh table view to show the updated data
            showDataTiket();

            // Clear fields
            ruteComboBox.getSelectionModel().clearSelection();
            statusComboBox.getSelectionModel().clearSelection();
            stokTextField.clear();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan saat menghapus tiket", Alert.AlertType.ERROR);
        }
    }

    // Method to refresh ComboBox options
    private void refreshComboBoxes() {
        ObservableList<String> ruteList = dataTiketQuery.getRuteList();
        ruteComboBox.setItems(ruteList);
        statusComboBox.setItems(FXCollections.observableArrayList("Tersedia", "Habis"));
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
