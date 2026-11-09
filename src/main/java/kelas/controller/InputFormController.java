package kelas.controller;
/**
 *
 * @author UreMine
 */
import kelas.exception.DuplicateProductException;
import kelas.model.Produk;
import kelas.model.ProductRepo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InputFormController implements Initializable {

    @FXML private TextField txtKode;
    @FXML private TextField txtNama;
    @FXML private ComboBox<String> comboKategori;
    @FXML private TextField txtHarga;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboKategori.getItems().addAll("Makanan", "Minuman");
        comboKategori.getSelectionModel().selectFirst();

        txtKode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtKode.setText(newValue.toUpperCase());
            }
        });
    }

    // method untuk menyeragamkan format kode (P1, P0001->P001)
    private String formatKodeProduk(String input) {
        if (input == null || input.isEmpty()) return "";

        // ambil huruf awalan (jika tidak ada, gunakan default 'P')
        String prefix = input.replaceAll("[^A-Z]", "");
        if (prefix.isEmpty()) {
            prefix = "P";
        } else {
            prefix = prefix.substring(0, 1);
        }

        // ambil hanya angkanya
        String angkaStr = input.replaceAll("[^0-9]", "");
        if (angkaStr.isEmpty()) {
            return "";
        }

        // format otomatis 3 digit (contoh: 1 -> 001)
        int angka = Integer.parseInt(angkaStr);
        return String.format("%s%03d", prefix, angka);
    }

    @FXML
    private void handleSimpan() {
        // 2. memanggil method formateKeProduk()
        String rawKode = txtKode.getText().trim().toUpperCase();
        String kode = formatKodeProduk(rawKode);

        String nama = txtNama.getText().trim();
        String kategori = comboKategori.getValue();
        String hargaText = txtHarga.getText().trim();

        if (kode.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Kode produk harus memuat angka yang valid! (Contoh: P001)");
            return;
        }

        if (nama.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Nama produk tidak boleh kosong!");
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaText);
            if (harga <= 0) {
                showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Harga produk harus lebih dari nol!");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validasi Gagal", "Harga produk harus berupa angka yang valid!");
            return;
        }

        try {
            Produk produkBaru = new Produk(kode, nama, kategori, harga);
            ProductRepo.tambahProduk(produkBaru);

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil ditambahkan!");
            tutupWindow();
        } catch (DuplicateProductException e) {
            showAlert(Alert.AlertType.ERROR, "Duplikat ID/Kode", e.getMessage());
        }
    }

    @FXML
    private void handleBatal() {
        tutupWindow();
    }

    private void tutupWindow() {
        Stage stage = (Stage) txtKode.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}