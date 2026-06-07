Feature: FR01 - Authentication

  Scenario: Pelajar berhasil login menggunakan email dan password yang terdaftar dan diarahkan ke dashboard Pelajar
    Given aplikasi JTK Learn terbuka di halaman login "https://polban-space.cloudias79.com/jtk-learn/"
    When pelajar memasukkan email "devi123@example.com"
    And pelajar memasukkan password "devi123"
    And pelajar mengklik tombol Masuk
    Then sistem memvalidasi kredensial, login berhasil, dan halaman berpindah ke dashboard Pelajar
    And header menampilkan menu navigasi yang berisi "Beranda | Kursus Saya | Riwayat Kuis | Devi"
    And footer menampilkan teks "JTK Polban © 2025 - Hak Cipta Dilindungi"
