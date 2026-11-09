package kelas.controller;
/**
 *
 * @author UreMine
 */
import kelas.model.Produk;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailController {

    @FXML private Label lblKode;
    @FXML private Label lblNama;
    @FXML private Label lblKategori;
    @FXML private Label lblHarga;

    public void setProduk(Produk produk) {
        if (produk != null) {
            lblKode.setText(produk.getKodeProduk());
            lblNama.setText(produk.getNamaProduk());
            lblKategori.setText(produk.getKategori());
            lblHarga.setText(String.format("Rp %,.2f", produk.getHargaProduk()));
        }
    }

    @FXML
    private void handleTutup() {
        Stage stage = (Stage) lblKode.getScene().getWindow();
        stage.close();
    }
}
