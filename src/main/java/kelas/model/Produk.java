package kelas.model;
/**
 *
 * @author UreMine
 */
public class Produk {
    private String kodeProduk;
    private String namaProduk;
    private String kategori;
    private double hargaProduk;

    public Produk(String kodeProduk, String namaProduk, String kategori, double hargaProduk) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.hargaProduk = hargaProduk;
    }

    public String getKodeProduk() { return kodeProduk; }
    public void setKodeProduk(String kodeProduk) { this.kodeProduk = kodeProduk; }

    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public double getHargaProduk() { return hargaProduk; }
    public void setHargaProduk(double hargaProduk) { this.hargaProduk = hargaProduk; }
}
