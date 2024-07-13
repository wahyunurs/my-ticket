package com.example.myticket.Controller;

import com.example.myticket.Connection.PemesananTiketQuery;
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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.example.myticket.Model.PemesananTiketModel;

public class PemesananTiketController implements Initializable {

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
    private TableView<PemesananTiketModel> pemesananTiketTable;
    @FXML
    public TableColumn<PemesananTiketModel, Integer> colId;
    @FXML
    public TableColumn<PemesananTiketModel, Integer> colIdTiket;
    @FXML
    public TableColumn<PemesananTiketModel, Integer> colIdPerjalanan;
    @FXML
    public TableColumn<PemesananTiketModel, String> colNamaUser;
    @FXML
    public TableColumn<PemesananTiketModel, Integer> colJumlahTiket;
    @FXML
    public TableColumn<PemesananTiketModel, Integer> colTotalBiaya;
    @FXML
    public TableColumn<PemesananTiketModel, LocalDate> colTanggalPemesanan;
    @FXML
    public Button cetakButton;

    private PemesananTiketModel pemesananTiketModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load Images
        loadImage("img/logo.png", logoImage);
        loadImage("img/profil.png", profilImage);

        // Set up label click events
        setLabelClickEvent(dashboardLabel, "/com/example/myticket/dashboard.fxml", "Dashboard");
        setLabelClickEvent(dataPerjalananLabel, "/com/example/myticket/dataperjalanan.fxml", "Data Perjalanan");
        setLabelClickEvent(pemesananTiketLabel, "/com/example/myticket/pemesanantiket.fxml", "Pemesanan Tiket");
        setLabelClickEvent(dataTiketLabel, "/com/example/myticket/datatiket.fxml", "Data Tiket");
        setLabelClickEvent(laporanPenjualanLabel, "/com/example/myticket/laporanpenjualan.fxml", "Laporan Penjualan");

        showPemesananTiket();
    }

    private void loadImage(String filePath, ImageView imageView) {
        File file = new File(filePath);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    private void setLabelClickEvent(Label label, String fxmlFile, String title) {
        label.setOnMouseClicked(event -> loadFXML(event, fxmlFile, title));
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
            showAlert("Error", "Failed to load " + title + " page.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void showPemesananTiket() {
        PemesananTiketQuery query = new PemesananTiketQuery();
        ObservableList<PemesananTiketModel> list = query.getPemesananTiketList();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdTiket.setCellValueFactory(new PropertyValueFactory<>("idTiket"));
        colIdPerjalanan.setCellValueFactory(new PropertyValueFactory<>("idPerjalanan"));
        colNamaUser.setCellValueFactory(new PropertyValueFactory<>("namaUser"));
        colJumlahTiket.setCellValueFactory(new PropertyValueFactory<>("jumlahTiket"));
        colTotalBiaya.setCellValueFactory(new PropertyValueFactory<>("totalBiaya"));
        colTanggalPemesanan.setCellValueFactory(new PropertyValueFactory<>("tanggalPemesanan"));
        pemesananTiketTable.setItems(list);
    }

    @FXML
    public void mouseClicked(MouseEvent event) {
        try {
            PemesananTiketModel selectedPemesanan = pemesananTiketTable.getSelectionModel().getSelectedItem();
            if (selectedPemesanan != null) {
                pemesananTiketModel = new PemesananTiketModel(
                        selectedPemesanan.getId(),
                        selectedPemesanan.getIdTiket(),
                        selectedPemesanan.getIdPerjalanan(),
                        selectedPemesanan.getNamaUser(),
                        selectedPemesanan.getJumlahTiket(),
                        selectedPemesanan.getTotalBiaya(),
                        selectedPemesanan.getTanggalPemesanan()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to select data.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cetakPemesanan() {
        if (pemesananTiketModel == null) {
            showAlert("Warning", "Please select a ticket to print.", Alert.AlertType.WARNING);
            return;
        }

        String pdfPath = "ticket_" + pemesananTiketModel.getId() + ".pdf";

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.newLineAtOffset(25, 750);
                contentStream.showText("Pemesanan Tiket");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 700);
                contentStream.showText("ID Pemesanan: " + pemesananTiketModel.getId());
                contentStream.newLine();
                contentStream.showText("ID Tiket: " + pemesananTiketModel.getIdTiket());
                contentStream.newLine();
                contentStream.showText("ID Perjalanan: " + pemesananTiketModel.getIdPerjalanan());
                contentStream.newLine();
                contentStream.showText("Nama User: " + pemesananTiketModel.getNamaUser());
                contentStream.newLine();
                contentStream.showText("Jumlah Tiket: " + pemesananTiketModel.getJumlahTiket());
                contentStream.newLine();
                contentStream.showText("Total Biaya: " + pemesananTiketModel.getTotalBiaya());
                contentStream.newLine();
                contentStream.showText("Tanggal Pemesanan: " + pemesananTiketModel.getTanggalPemesanan());
                contentStream.endText();
            }

            document.save(pdfPath);
            showAlert("Success", "PDF generated successfully!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to generate PDF.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
