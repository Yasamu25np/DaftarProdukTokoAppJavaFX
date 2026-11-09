package kelas.controller;
/**
 *
 * @author UreMine
 */
import kelas.model.Produk;
import kelas.model.ProductRepo;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TextField txtCari;
    @FXML private TableView<Produk> tableProduk;
    @FXML private TableColumn<Produk, String> colKode;
    @FXML private TableColumn<Produk, String> colNama;
    @FXML private TableColumn<Produk, String> colKategori;
    @FXML private TableColumn<Produk, Double> colHarga;

    private FilteredList<Produk> filteredData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeProduk"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaProduk"));
        colKategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("hargaProduk"));

        filteredData = new FilteredList<>(ProductRepo.getListProduk(), p -> true);
        tableProduk.setItems(filteredData);
    }

    @FXML
    private void handleBukaFormInput() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InputFormWindow.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Form Tambah Produk Baru");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCari() {
        String keyword = txtCari.getText().trim().toLowerCase();
        filteredData.setPredicate(produk -> {
            if (keyword.isEmpty()) {
                return true;
            }
            return produk.getNamaProduk().toLowerCase().contains(keyword);
        });
    }

    @FXML
    private void handleLihatDetail() {
        Produk selectedProduct = tableProduk.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih produk dari tabel terlebih dahulu!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailWindow.fxml"));
            Parent root = loader.load();

            DetailController detailController = loader.getController();
            detailController.setProduk(selectedProduct);

            Stage stage = new Stage();
            stage.setTitle("Detail Produk");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
private void handleHapus() {
    // ambil produk yang dipilih dari tabel
    Produk selectedProduct = tableProduk.getSelectionModel().getSelectedItem();

    if (selectedProduct == null) {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih produk dari tabel yang ingin dihapus terlebih dahulu!");
        return;
    }

    // tampilkan dialog konfirmasi 
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Konfirmasi Hapus");
    alert.setHeaderText(null);
    alert.setContentText("Apakah Anda yakin ingin menghapus produk '" + selectedProduct.getNamaProduk() + "'?");

    alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
            // panggil repo untuk hapus produk
            ProductRepo.hapusProduk(selectedProduct);
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil dihapus!");
        }
    });
}
}
