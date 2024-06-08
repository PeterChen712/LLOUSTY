# LLOUSTY: Aplikasi E-Commerce untuk Pembelian Produk Kecantikan

LLOUSTY, yang merupakan kepanjangan dari "Marvellous Beauty" yang berarti "Kecantikan Luar Biasa", adalah sebuah aplikasi yang memungkinkan pengguna untuk membeli atau menjual produk kecantikan.

## Fitur Aplikasi

1. Fitur *Login* dan Registrasi
2. Fitur *Home* yang menampilkan produk yang dijual dengan kategori *Makeup, Skin Care,* dan *Fragrance*.
3. Fitur *My Order* yang menampilkan produk apa saja yang telah dibeli oleh *user* maupun *seller*.
4. Fitur *search* yang dapat mencari produk dan user.
5. Fitur *Profile* yang menampilkan My Profile (informasi data dan alamat pengguna), sekaligus ada fitur *Seller Mode* jika *user* ingin menjual produk, dan ada fitur Logout.
6. Fitur *Chat* sesama *user*
7. Fitur *Cart* alias keranjang yang memuat daftar produk yang dimasukkan ke keranjang sebelum melakukan *Checkout* dan pembayaran
8. Untuk fitur *Seller mode*, *user* dapat menambahkan produk, melihat daftar produk yang dijual, serta ada *History* penjualan
9. Fitur notif, memberitahukan notif baik ke *seller* maupun *user* (pembeli) jika ada pembelian atau penjualan produk terjadi.
10. Fitur *Rating* setelah *user* membeli suatu produk.

## Penerapan Prinsip OOP

- **Config dan Controller**: Terdapat class DbConfig.java dan 8 class controller atau DAO yang meng-extends class DatabaseConfig.java.
- **Models**: Ada kelas induk untuk model-model bernama Model.java dan 9 class model lainnya.
- **Scene**: Terdapat interface ShowScene.java yang diimplementasikan di LoginScene.java dan RegistScene.java.
- **Utils**: Pilar OOP Polimorfisme digunakan pada class imageSet.java dan Removeindex.java, serta pilar OOP Encapsulation diterapkan pada hampir semua class.

## Link Repository GitHub

[https://github.com/PeterChen712/LLOUSTY](https://github.com/PeterChen712/LLOUSTY)

## Screenshots

### Login Scene
![Login Scene](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/login.png)

### Regist Scene
![Regist Scene](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/regis.png)

### Home Scene (User Biasa)
![Home Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/homeuser.png)

### Home Scene (Seller Mode)
![Home Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/homeseller.png)

### Product Scene (User Biasa)
![Product Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/productuser.png)

### Product Scene (Seller Mode)
![Product Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/productseller.png)

### Chat Scene (User Biasa)
![Chat Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/chatuser.png)

### Chat Scene (Seller Mode)
![Chat Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/chatseller.png)

### My Order Scene (User Biasa)
![My Order Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/orderuser.png)

### My Order Scene (Seller Mode)
![My Order Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/orderseller.png)

### Searching Scene (User Biasa)
![Searching Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/searchuser.png)

### Searching Scene (Seller Mode)
![Searching Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/searchseller.png)

### Profil Scene (User Biasa)
![Profil Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/profileuser.png)

### Profil Scene (Seller Mode)
![Profil Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/sellermode.png)

### Cart Scene (User Biasa)
![Cart Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/cartuser.png)

### Cart Scene (Seller Mode)
![Cart Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/cartseller.png)

### Notif Scene (User Biasa)
![Notif Scene (User Biasa)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/notifuser.png)

### Notif Scene (Seller Mode)
![Notif Scene (Seller Mode)](https://github.com/PeterChen712/Grocery-Shop/blob/fa1df36aa5524770c65aff579f3a2194af5721ce/readme/notifseller.png)

## Pengujian Aplikasi
Terdapat daftar pengujian yang dilakukan pada aplikasi, dengan deskripsi pengujian, hasil yang diharapkan, dan hasil dari pengujian tersebut.

| NO | Deskripsi Pengujian | Hasil yang diharapkan | Hasil |
|----|-----------------------|------------------------|-------|
| 1  | Login dengan username atau password salah | Sistem menampilkan pesan kesalahan yang sesuai | Berhasil |
| 2  | Login tanpa mengisi username atau password | Aplikasi menampilkan atau pesan kesalahan yang sesuai | Berhasil |
| 3  | Login dengan username dan password benar | Aplikasi berhasil masuk ke halaman utama pengguna | Berhasil |
| 4  | Mengklik hyperlink "Register" pada halaman login | Aplikasi berpindah ke halaman registrasi | Berhasil |
| 5  | Registrasi dengan semua data benar | Sistem berhasil membuat akun baru dan mengarahkan pengguna ke halaman login | Berhasil |
| 6  | Registrasi tanpa mengisi salah satu field wajib (misalnya, username atau password) | Sistem menampilkan pesan kesalahan bahwa field wajib harus diisi | Berhasil |
| 7  | Registrasi dengan password yang tidak memenuhi kriteria (misalnya, terlalu pendek) | Sistem menampilkan pesan kesalahan bahwa password harus memenuhi kriteria yang ditentukan | Berhasil |
| 9  | Mengklik hyperlink "Login" pada halaman registrasi | Aplikasi berpindah ke halaman login | Berhasil |
| 10 | Memuat halaman HomeScene dengan pengguna yang benar | Halaman HomeScene menampilkan informasi pengguna dengan benar | Berhasil |
| 11 | Mengklik kategori "Make Up" pada halaman HomeScene | Halaman menampilkan produk dalam kategori "Make Up" | Berhasil |
| 12 | Mengklik kategori "Skin Care" pada halaman HomeScene | Halaman menampilkan produk dalam kategori "Skin Care" | Berhasil |
| 13 | Mengklik kategori "Fragrance" pada halaman HomeScene | Halaman menampilkan produk dalam kategori "Fragrance" | Berhasil |
| 14 | Menampilkan elemen visual seperti header, kategori, produk, dan navbar | Semua elemen tampil sesuai dengan desain dalam styles.css | Berhasil |
| 15 | Mengklik produk dalam kategori | Halaman berpindah ke detail produk yang dipilih | Berhasil |
| 16 | Memuat halaman SearchScene dengan data produk yang benar | Halaman SearchScene menampilkan produk yang sesuai dengan pencarian | Berhasil |
| 17 | Memuat halaman SearchScene dengan data pengguna yang benar | Halaman SearchScene menampilkan pengguna yang sesuai dengan pencarian | Berhasil |
| 18 | Mengklik tab "Product" pada halaman SearchScene | Halaman menampilkan hasil pencarian produk yang sesuai | Berhasil |
| 19 | Mengklik tab "User" pada halaman SearchScene | Halaman menampilkan hasil pencarian pengguna yang sesuai | Berhasil |
| 20 | Mengklik produk yang muncul dalam hasil pencarian | Halaman berpindah ke profil pengguna yang dipilih | Berhasil |
| 21 | Mengklik pengguna yang muncul dalam hasil pencarian | Sistem menampilkan profil pengguna yang dipilih | Berhasil |
| 22 | Memuat halaman ChatScene dengan percakapan yang benar | Halaman ChatScene menampilkan percakapan yang sesuai dengan pengguna target | Berhasil |
| 23 | Mengirim pesan baru dalam percakapan | Pesan baru ditambahkan ke percakapan dan ditampilkan di halaman ChatScene | Berhasil |
| 24 | Memuat halaman ChatScene tanpa percakapan | Halaman ChatScene menampilkan pesan "No Chat" | Berhasil |
| 25 | Memuat halaman TransactionScene dengan transaksi yang benar | Halaman TransactionScene menampilkan transaksi yang sesuai dengan userId | Berhasil |
| 26 | Menampilkan transaksi dengan format mata uang yang benar | Total Cost diformat dengan mata uang Rp | Berhasil |
| 27 | Menampilkan informasi transaksi dengan benar | Semua kolom menampilkan informasi yang benar (Date, Total Cost, Payment Method, Product, Seller, Status) | Berhasil |
| 28 | Pengujian tampilan awal profil pengguna | Sistem dapat menampilkan profil pengguna dengan benar, termasuk foto profil, informasi pribadi, dan detail alamat | Berhasil |
| 29 | Pengujian kemampuan edit informasi pengguna| Dapat memberikan informasi pengguna sesuai dengan perubahan yang dibuat| Berhasil |
| 30 | Pengujian upload foto profil pengguna | Sistem dapat mengunggah dan menampilkan foto profil pengguna dengan benar | Berhasil |
| 31 | Pengujian aktivasi mode seller jika informasi yang diperlukan sudah diisi | Sistem dapat mengaktifkan mode penjual jika alamat, nomor telepon, dan jenis kelamin telah diisi | Berhasil |
| 32 | Menjalankan fungsi show pada kelas CartScene dengan userId yang tidak memiliki item di keranjang | Sistem menampilkan pesan peringatan "Cart is Empty" dan menginstruksikan pengguna untuk menambahkan item ke keranjang | Berhasil |
| 33 | Menjalankan fungsi show dan mencoba memilih metode pembayaran | Sistem menampilkan tampilan sesuai dengan metode pembayaran yang dipilih dan memasukkan hanya satu metode yang dipilih pada satu waktu (E-Money, Virtual Account, Credit Card, COD) | Berhasil |
| 34 | Menjalankan fungsi show dan memilih item dalam keranjang dengan menggunakan checkbox | Sistem memperbaharui subtotal dan total pembayaran sesuai dengan item yang dipilih | Berhasil |
| 35 | Menampilkan daftar pengiriman dengan detail (tanggal, judul, isi) | Sistem dapat menampilkan daftar notifikasi beserta detailnya dengan benar | Berhasil |
| 36 | Membuka notifikasi untuk melihat informasi lebih lanjut | Pengguna dapat membuka notifikasi dan melihat informasi lebih lanjut | Berhasil |
| 37 | Tata letak notifikasi responsif dan mudah dibaca | Notifikasi ditampilkan dengan tata letak responsif dan mudah dibaca | Berhasil |
| 38 | Menampilkan detail produk (foto, nama, penjual, rating, deskripsi, varian, tombol keranjang) | Sistem dapat menampilkan semua detail produk dengan benar | Berhasil |
| 39 | Menambah jumlah produk yang akan dibeli | Pengguna dapat menambah jumlah produk yang akan dibeli dengan benar | Berhasil |
| 40 | Menambah komentar pada produk | Pengguna dapat menambah komentar pada produk dengan benar | Berhasil |
| 41 | Menampilkan layer loading selama detik | Layer loading muncul selama 1 detik sebelum konten utama aplikasi ditampilkan | Berhasil |
| 42 | Menampilkan konten utama aplikasi setelah layer loading selesai | Konten utama aplikasi ditampilkan dengan benar setelah layer loading selesai | Berhasil |
| 43 | Menggunakan konten utama setelah ditampilkan | Pengguna dapat menggunakan konten utama tanpa gangguan setelah konten utama ditampilkan | Berhasil |
| 44 | Menambah produk baru dengan detail lengkap (nama, harga, stok, kategori, deskripsi, foto, diskon) | Produk berhasil ditambahkan dengan detail yang lengkap| Berhasil |
| 45 | Menampilkan produk yang ditambahkan di halaman Your Product | Produk yang baru ditambahkan muncul di halaman Your Product | Berhasil |
| 46  | Menampilkan produk yang ditambahkan di halaman Home | Produk yang baru ditambahkan muncul di halaman Home | Berhasil |
| 47  | Menghapus produk di halaman Your Product | Produk yang dihapus tidak lagi muncul di daftar Your Product | Berhasil |
| 48  | Mencatat riwayat penjualan produk di halaman History | Produk yang terjual tercatat dengan benar di halaman History | Berhasil |