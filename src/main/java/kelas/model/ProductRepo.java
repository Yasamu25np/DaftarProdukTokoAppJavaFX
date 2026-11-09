package kelas.model;
/**
 *
 * @author UreMine
 */
import kelas.exception.DuplicateProductException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductRepo {
    private static final ObservableList<Produk> listProduk = FXCollections.observableArrayList();

    public static ObservableList<Produk> getListProduk() {
        return listProduk;
    }

        static {
        // Sample dummy data  
        listProduk.add(new Produk("P001", "Minyak Sawit", "Minuman", 100000));
        listProduk.add(new Produk("P002", "Es Teh Manis", "Minuman", 5000));
        listProduk.add(new Produk("P003", "Sate Madura", "Makanan", 20000));
        
    }
public static void hapusProduk(Produk produk) {
    listProduk.remove(produk);
}
    public static void tambahProduk(Produk produkBaru) throws DuplicateProductException {
        // menyamakan huruf id ke Uppercase
        String kodeBaru = produkBaru.getKodeProduk().trim().toUpperCase();
        produkBaru.setKodeProduk(kodeBaru);

        // cek duplikat namun tidak melihat up/undercase
        for (Produk p : listProduk) {
            if (p.getKodeProduk().equalsIgnoreCase(kodeBaru)) {
                throw new DuplicateProductException("Kode Produk '" + kodeBaru + "' sudah digunakan!");
            }
        }

        listProduk.add(produkBaru);
    }
}