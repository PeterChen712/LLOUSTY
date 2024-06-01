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
![Regist Scene](regis.png)

### Home Scene (User Biasa)
![Home Scene (User Biasa)](homeuser.png)

### Home Scene (Seller Mode)
![Home Scene (Seller Mode)](homeseller.png)

### Product Scene (User Biasa)
![Product Scene (User Biasa)](productuser.png)

### Product Scene (Seller Mode)
![Product Scene (Seller Mode)](productseller.png)

### Chat Scene (User Biasa)
![Chat Scene (User Biasa)](chatuser.png)

### Chat Scene (Seller Mode)
![Chat Scene (Seller Mode)](chatseller.png)

### My Order Scene (User Biasa)
![My Order Scene (User Biasa)](orderuser.png)

### My Order Scene (Seller Mode)
![My Order Scene (Seller Mode)](orderseller.png)

### Searching Scene (User Biasa)
![Searching Scene (User Biasa)](searchuser.png)

### Searching Scene (Seller Mode)
![Searching Scene (Seller Mode)](searchseller.png)

### Profil Scene (User Biasa)
![Profil Scene (User Biasa)](profileuser.png)

### Profil Scene (Seller Mode)
![Profil Scene (Seller Mode)](profileuser.png)

### Cart Scene (User Biasa)
![Cart Scene (User Biasa)](cartuser.png)

### Cart Scene (Seller Mode)
![Cart Scene (Seller Mode)](cartseller.png)

### Notif Scene (User Biasa)
![Notif Scene (User Biasa)](notifuser.png)

### Notif Scene (Seller Mode)
![Notif Scene (Seller Mode)](notifseller.png)

### Seller Mode Scene
![Seller Mode Scene](sellermode.png)

## Pengujian Aplikasi

Terdapat daftar pengujian yang dilakukan pada aplikasi, dengan deskripsi pengujian, hasil yang diharapkan, dan hasil dari pengujian tersebut.
