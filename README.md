# B5-Automation_Testing - JTK Learn Automation Testing

Proyek ini adalah repositori otomatisasi pengujian (*Automation Testing*) untuk aplikasi **JTK Learn** berbasis **Java**, **Selenium WebDriver**, dan **Cucumber (BDD)** dengan pola desain **Page Object Model (POM)** dan **Page Factory**.

---

## 🛠️ Persiapan Lingkungan Pengujian (Prerequisites)

Sebelum menjalankan pengujian, pastikan perangkat lokal Anda telah terinstal kakas (*tools*) berikut:

1. **Java Development Kit (JDK)**: Versi 17 atau lebih tinggi (direkomendasikan versi 21).
   - Verifikasi instalasi: `java -version`
2. **Apache Maven**: Versi 3.8.x / 3.9.x atau lebih tinggi.
   - Verifikasi instalasi: `mvn -version`
3. **Google Chrome Browser**: Gunakan versi terbaru. WebDriverManager akan secara otomatis mengunduh *driver* Chrome yang kompatibel.
4. **IDE (Direkomendasikan)**: IntelliJ IDEA dengan plugin **Cucumber for Java** dan **Gherkin** terinstal.

---

## 📂 Struktur Proyek

Proyek ini disusun dengan pola arsitektur Page Object Model (POM) untuk memisahkan logika interaksi elemen UI dengan skenario pengujian BDD secara rapi:

```text
B5-Automation_Testing/
├── pom.xml                               # Manajemen dependensi Maven
├── README.md                             # Dokumentasi proyek
└── src/
    ├── main/
    │   └── java/
    │       ├── hooks/
    │       │   └── Hooks.java            # Setup & teardown WebDriver sebelum/setelah skenario
    │       ├── pages/                    # Web Elements & Interaction (POM - Page Factory)
    │       │   ├── LoginPage.java        # Halaman Login & Validasi Error
    │       │   ├── DashboardPage.java    # Halaman Dashboard Pelajar
    │       │   └── CourseOverviewPage.java # Halaman Pendaftaran Kursus
    │       └── utils/
    │           └── DriverSetup.java      # Pengaturan instansiasi WebDriver & ChromeOptions
    └── test/
        ├── java/
        │   ├── runners/                  # Eksekutor JUnit untuk Cucumber
        │   │   └── TestRunner.java
        │   └── stepdefinitions/          # Implementasi langkah-langkah skenario Gherkin
        │       ├── LoginSteps.java
        │       ├── LogoutSteps.java
        │       └── CourseOverviewSteps.java
        └── resources/
            └── features/                 # Berkas skenario pengujian BDD (Gherkin)
                ├── login.feature         # Fitur login (sukses & gagal)
                ├── logout.feature        # Fitur logout
                └── course_overview.feature # Fitur pendaftaran kelas / course
```

---

## 📝 Contoh Skenario Uji (BDD Feature)

Berikut adalah contoh skenario uji positif untuk modul Autentikasi yang berada di berkas [login.feature](file:///d:/KULIAH/Semester6/Pengujian%20Perangkat%20Lunak/Praktek/Pertemuan14,15/B5-Automation_Testing/src/test/resources/features/login.feature):

```gherkin
Feature: FR01 - Login

  Scenario: TC01 - Pelajar berhasil login menggunakan email dan password yang terdaftar dan diarahkan ke dashboard Pelajar
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

*(Catatan: Jika variabel path Maven belum diset secara global pada Windows, Anda bisa menggunakan path/variabel lingkungan seperti contoh berikut:)*
```cmd
$env:JAVA_HOME="C:\Program Files\Java\jdk-21"; & "C:\Program Files\Maven\apache-maven-3.9.16\bin\mvn.cmd" clean test
```

Jika ingin menjalankan pengujian dalam mode headless (tanpa memunculkan antarmuka grafis browser Chrome):
```cmd
mvn test -Dheadless=true
```

### 2. Menjalankan Lewat IntelliJ IDEA
1. Buka proyek `B5-Automation_Testing` menggunakan IntelliJ IDEA.
2. Tunggu proses *sync* / *reload* Maven selesai.
3. Arahkan ke berkas `src/test/java/runners/TestRunner.java`.
4. Klik kanan pada kelas `TestRunner` lalu pilih **Run 'TestRunner'**.

---

## 📊 Cara Melihat Hasil Pengujian (Test Reports)

Setelah pengujian dijalankan, berkas laporan pengujian secara otomatis akan dibuat di dalam folder `target/` sesuai dengan konfigurasi plugin pada [TestRunner.java](file:///d:/KULIAH/Semester6/Pengujian%20Perangkat%20Lunak/Praktek/Pertemuan14,15/B5-Automation_Testing/src/test/java/runners/TestRunner.java):

1. **Laporan HTML**:
   - Lokasi berkas: `target/cucumber-reports.html`
   - Cara melihat: Buka folder `target/` di File Explorer, lalu klik ganda berkas `cucumber-reports.html` untuk membukanya di browser internet pilihan Anda (Chrome, Edge, Firefox, dll.). Laporan ini menyajikan ringkasan visual yang interaktif tentang skenario mana saja yang berhasil (*passed*) maupun gagal (*failed*).

2. **Laporan JSON**:
   - Lokasi berkas: `target/cucumber.json`
   - Laporan mentah berformat JSON ini biasanya digunakan untuk kebutuhan integrasi dengan kakas pihak ketiga (CI/CD pipelines seperti Jenkins, GitLab CI, GitHub Actions) atau visualisasi eksternal.

---

## 💡 Fitur Penanganan Khusus: Penanganan Keadaan Sudah Terdaftar (Already Enrolled Bypassing)
Pada modul **Course Overview** (pendaftaran kelas), terdapat situasi di mana akun pengujian sudah pernah terdaftar pada kelas target di sesi/eksekusi pengujian sebelumnya. Jika sistem mencoba mendaftar kembali secara paksa, pengujian berpotensi gagal karena halaman/elemen pendaftaran sudah tidak ditampilkan.

Untuk menjamin idempotensi dan stabilitas jalannya pengujian (*test stability*), proyek ini dilengkapi dengan mekanisme bypass pada kelas [CourseOverviewSteps.java](file:///d:/KULIAH/Semester6/Pengujian%20Perangkat%20Lunak/Praktek/Pertemuan14,15/B5-Automation_Testing/src/test/java/stepdefinitions/CourseOverviewSteps.java). Sistem akan otomatis mendeteksi jika URL halaman berpindah langsung ke area `/course/` dan mengatur flag `isAlreadyEnrolled = true`, sehingga skenario langkah pengisian kode registrasi dan pendaftaran dilewati dengan sukses.
