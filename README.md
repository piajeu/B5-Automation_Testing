# B5-Automation_Testing - JTK Learn Automation Testing

Proyek ini adalah repositori otomatisasi pengujian (*Automation Testing*) untuk aplikasi **JTK Learn** berbasis **Java**, **Selenium WebDriver**, dan **Cucumber (BDD)** dengan pola desain **Page Object Model (POM)** dan **Page Factory**.

---

## 🛠️ Persiapan Lingkungan Pengujian (Prerequisites)

Sebelum menjalankan pengujian, pastikan perangkat lokal Anda telah terinstal kakas (*tools*) berikut:

1. **Java Development Kit (JDK)**: Versi 17 atau lebih tinggi.
   - Verifikasi instalasi: `java -version`
2. **Apache Maven**: Versi 3.8.x / 3.9.x atau lebih tinggi.
   - Verifikasi instalasi: `mvn -version`
3. **Google Chrome Browser**: Gunakan versi terbaru. WebDriverManager akan secara otomatis mengunduh *driver* Chrome yang kompatibel.
4. **IDE (Direkomendasikan)**: IntelliJ IDEA dengan plugin **Cucumber for Java** dan **Gherkin** terinstal.

---

## 📂 Struktur Proyek

Proyek ini disusun dengan pola arsitektur Page Object Model (POM) untuk memisahkan logika interaksi elemen UI dengan skenario pengujian BDD:

```text
B5-Automation_Testing/
├── pom.xml                               # Manajemen dependensi Maven
├── README.md                             # Dokumentasi proyek
└── src/
    └── test/
        ├── java/
        │   ├── pages/                    # Web Elements & Interaction (POM)
        │   │   ├── LoginPage.java        # Halaman Login
        │   │   └── DashboardPage.java    # Halaman Dashboard Pelajar
        │   │   ├── CoursePage.java       # Halaman Detail Kursus
        │   │   └── MaterialPage.java     # Halaman Player Materi (dengan auto-refresh)
        │   ├── stepdefinitions/          # Implementasi langkah-langkah skenario
        │   │   └── LoginSteps.java
        │   └── runners/                  # Eksekutor JUnit untuk Cucumber
        │       └── TestRunner.java
        └── resources/
            └── features/                 # Berkas skenario pengujian BDD
                ├── login.feature
                └── akses_materi.feature
```

---

## 📝 Contoh Skenario Uji (BDD Feature)

Berikut adalah contoh skenario uji positif untuk modul Autentikasi yang berada di berkas [login.feature](file:///D:/KULIAH/Semester6/Pengujian%20Perangkat%20Lunak/Praktek/Pertemuan14,15/B5-Automation_Testing/src/test/resources/features/login.feature):

```gherkin
Feature: FR01 - Authentication

  Scenario: Pelajar berhasil login menggunakan email dan password yang terdaftar dan diarahkan ke dashboard Pelajar
    Given aplikasi JTK Learn terbuka di halaman login "https://polban-space.cloudias79.com/jtk-learn/"
    When pelajar memasukkan email "devi123@example.com"
    And pelajar memasukkan password "devi123"
    And pelajar mengklik tombol Masuk
    Then sistem memvalidasi kredensial, login berhasil, dan halaman berpindah ke dashboard Pelajar
    And header menampilkan menu navigasi yang berisi "Beranda | Kursus Saya | Riwayat Kuis | Devi"
    And footer menampilkan teks "JTK Polban © 2025 - Hak Cipta Dilindungi"
```

---

## 🚀 Cara Menjalankan Pengujian

### 1. Menjalankan Lewat Terminal / Command Line
Buka terminal pada direktori root proyek `B5-Automation_Testing` lalu jalankan perintah:

```cmd
mvn clean test
```

*(Catatan: Jika variabel path Maven belum diset secara global pada Windows, Anda bisa menggunakan path absolut Java & Maven seperti contoh berikut:)*
```cmd
$env:JAVA_HOME="C:\Program Files\Java\jdk-21"; & "C:\Program Files\Maven\apache-maven-3.9.16\bin\mvn.cmd" clean test
```

### 2. Menjalankan Lewat IntelliJ IDEA
1. Buka proyek `B5-Automation_Testing` menggunakan IntelliJ IDEA.
2. Tunggu proses *sync* / *reload* Maven selesai.
3. Arahkan ke berkas `src/test/java/runners/TestRunner.java`.
4. Klik kanan pada kelas `TestRunner` lalu pilih **Run 'TestRunner'**.

---

## 💡 Fitur Penanganan Khusus: Auto-Refresh Halaman
Pada modul **Akses Materi**, sistem React terkadang mengalami penundaan pemuatan API saat berpindah halaman secara dinamis sehingga memunculkan pesan kosong (*"There are no materials or quizzes for this course yet"*). 

Untuk mengatasinya, proyek ini dilengkapi dengan **mekanisme auto-refresh** pada kelas `MaterialPage.java` yang akan mendeteksi kondisi tersebut dan melakukan muat ulang (*reload*) halaman secara otomatis demi menjamin kestabilan jalannya uji (*test stability*).
