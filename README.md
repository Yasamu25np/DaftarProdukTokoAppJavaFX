# DaftarProdukTokoAppJavaFX
 
 
aplikasi data daftar produk toko sederhana menggunakan JavaFX - ApacheNetbeans 
# 🛒 Aplikasi Pencatatan Produk Toko (JavaFX Multi-Stage)

Aplikasi desktop berbasis **JavaFX** untuk manajemen pencatatan produk toko menggunakan pola arsitektur **MVC (Model-View-Controller)** dengan tampilan **Multi-Stage (Multi-Window)**.

---

## ⚡ Cara Menjalankan Aplikasi

### Prasyarat
- **JDK 17** atau yang lebih baru.
- **Apache Maven 3.6+**.

### Langkah Jalankan (via Terminal/CMD)
1. **Clone repository:**
   ```bash
   git clone https://github.com/Yasamu25np/-DaftarProdukTokoAppJavaFX.git
2. **buka folder**
```
cd DaftarProdukTokoAppl
```
3. **jalankan perintah**
```
   mvn clean javafx:run
```
### Langkah Jalankan (via Software ApacheNetbeans IDE)
**Bisa juga langsung RUN(f6), karna sudah dibuat shortcut goal javafx:run pada file nbactions.xml**

**TAMPILAN**
<p align="center">
   <img width="969" height="742" alt="image" src="https://github.com/user-attachments/assets/7d4cf378-e6cb-46a4-8b5f-8374e2bb7649" />

</p>

<p align="center">

 
<img width="574" height="435" alt="image" src="https://github.com/user-attachments/assets/f73d3d2c-9778-4643-b628-4cc5492d7d65" />

</p>
<p align="center">
  
<img width="954" height="732" alt="image" src="https://github.com/user-attachments/assets/33393b71-1520-4c52-a967-52b16f69cc8e" />

</p>
 ✨ Fitur Utama
1. Pencatatan Data Produk:
  - Form penambahan produk baru (Kode, Nama, Kategori, Harga).
  - Pilihan kategori khusus: Makanan atau Minuman.
2. Daftar & Tabel Produk:
  - Menampilkan daftar produk secara real-time pada TableView.
3. Pencarian Produk:
  - Fitur pencarian produk berdasarkan nama secara case-insensitive melalui tombol Cari.
4. Lihat Detail Produk:
  - Menampilkan rincian data dari objek Produk yang dipilih pada jendela (Stage) tersendiri.
5. Validasi Input Dasar:
  - Nama produk tidak boleh kosong.
  - Harga produk harus berupa angka dan bernilai $> 0$.


  🚀 Fitur Tambahan  
Berikut adalah improvisasi dan fitur baru yang telah ditambahkan untuk meningkatkan kualitas serta keamanan aplikasi:
1. Fitur Hapus Produk (Data Management):
  - Penambahan tombol Hapus Produk beserta dialog konfirmasi sebelum data dihapus dari repository memori.
2. Custom Exception (DuplicateProductException):
  - Menambahkan exception class khusus untuk mendeteksi dan menggagalkan penambahan data jika Kode Produk (ID) yang diinput sudah terdaftar di repository.
3. Normalisasi & Auto-Formatting Kode Produk:
  - Real-time Auto-Capital: Input pada field Kode Produk otomatis diubah menjadi huruf kapital secara langsung di layar saat diketik.
  - Formatting Standard (Zero-Padding): Pengubahan format otomatis (misal: input p1 atau P0001 akan di-normalisasi menjadi P001), mencegah kerancuan variasi ID produk yang secara logika merujuk ke nomor urut yang sama.
4. Pencegahan Input Karakter Ilegal:
  - Menyaring input Kode Produk hanya untuk karakter Alfanumerik (Huruf A-Z dan Angka 0-9).

  ( https://github.com/Yasamu25np/-DaftarProdukTokoAppJavaFX.git )
