# Tugas Praktikum Minggu 3 - My Profile App

**Nama:** Faisal H Sinambela  
**NIM:** 124140040  
**Mata Kuliah:** Praktikum Pengembangan Aplikasi Mobile  

---

## 📸 Hasil Tampilan Aplikasi

### Tampilan Aplikasi (Desktop)
<img src="screenshot/Dekstop.png" width="450" />

### Tampilan Aplikasi (Android)
<img src="screenshot/Android.png" width="300" />

> **Catatan Pengumpulan:** Ambil screenshot saat aplikasi sedang berjalan (tekan tombol `PrtScn` di keyboard), lalu simpan tangkapan layar di dalam folder `screenshot/Dekstop.png` dan/atau `screenshot/Android.png`.

---

## ✨ Fitur-Fitur Aplikasi

1. **Halaman Profil:**
   - **Header Profil:** Foto profil berbentuk lingkaran (*circular*) dengan border, indikator status online/aktif, nama lengkap (**Faisal H Sinambela**), title profesi (**NIM. 124140040 • Mobile Developer**), dan deskripsi singkat (*bio*).
   - **Quick Stats:** Menampilkan statistik interaktif (*Followers*, *Following*, *Projects*).
   - **Action Buttons:**
     - **Tombol "Ikuti" / "Mengikuti":** Interaktif, mengubah status ikuti dan menambah/mengurangi jumlah *follower* secara real-time.
     - **Tombol "Edit Profil":** Membuka dialog interaktif untuk mengubah nama, profesi, bio, nomor telepon, dan lokasi secara langsung.
   - **List Informasi Kontak:**
     - ✉️ **Email:** faisalnambela@gmail.com
     - 📞 **Nomor Telepon:** +62 812-3456-7890
     - 📍 **Lokasi:** Medan, Sumatera Utara, Indonesia
   - **Tentang Saya:** Narasi perkenalan dan latar belakang pembelajaran mobile.
   - **Keahlian & Teknologi:** Badge keahlian (*Kotlin*, *Compose*, *Android*, *Git*).

---

## 🧩 3 Reusable Composable Functions

Sesuai instruksi tugas, aplikasi ini memisahkan logika antarmuka ke dalam minimal 3 komponen Composable reusable di dalam package `com.faisal.myapplication.components`:

1. **`ProfileHeader`** (`shared/src/commonMain/kotlin/com/faisal/myapplication/components/ProfileHeader.kt`):
   - Komponen reusable untuk menampilkan foto profil lingkaran (*circular* dengan `CircleShape`), lencana status online (`Box`), nama pengguna, peran/profesi, serta bio singkat.
2. **`InfoItem`** (`shared/src/commonMain/kotlin/com/faisal/myapplication/components/InfoItem.kt`):
   - Komponen reusable untuk setiap item informasi profil (Email, Telepon, Lokasi, dsb.) yang menggabungkan icon dalam kontainer berbentuk melengkung, label, nilai data, dan aksi klik (*clickable*).
3. **`ProfileCard`** (`shared/src/commonMain/kotlin/com/faisal/myapplication/components/ProfileCard.kt`):
   - Komponen kontainer reusable berbasis `ElevatedCard` lengkap dengan styling rounded corner, header icon, judul seksi, pembatas horizontal (*divider*), dan konten kustom.

---

## 🛠️ Komponen Compose Wajib yang Digunakan

Aplikasi telah mengimplementasikan seluruh komponen UI wajib:
- [x] **`Column`** : Digunakan untuk tata letak vertikal halaman, card, dialog, dan teks informasi.
- [x] **`Row`** : Digunakan pada `InfoItem` (icon + teks), baris statistik, baris tombol aksi, dan badges.
- [x] **`Box`** : Digunakan sebagai kontainer foto profil, lencana status online hijau, latar belakang icon, dan skill chip.
- [x] **`Card`** : Digunakan pada komponen `ProfileCard` dan ringkasan statistik menggunakan `ElevatedCard`.
- [x] **`Text`** : Digunakan untuk judul, nama, caption, label, nilai info, bio, dan tombol.
- [x] **`Button`** : Digunakan untuk tombol interaktif "Ikuti", "Edit Profil", dan tombol simpan pada dialog.
- [x] **`Image` / `Icon`** : Digunakan untuk menampilkan foto profil (*Image*) dan ragam ikon Material Design (*Icon* Email, Phone, Location, Edit, Person, dll).

---

## 🚀 Cara Menjalankan Aplikasi

Pastikan Java Development Kit (JDK 17 atau 21) terpasang.

### 1. Menjalankan di Desktop
Jalankan perintah berikut di terminal:
```bash
./gradlew :desktopApp:run
```

### 2. Menjalankan di Android
- Buka project ini di **Android Studio**.
- Pilih konfigurasi target `androidApp` dan jalankan pada emulator atau perangkat fisik Android.
- Atau build file APK debug via terminal:
  ```bash
  ./gradlew :androidApp:assembleDebug
  ```
  File APK akan dihasilkan di folder `androidApp/build/outputs/apk/debug/`.