Feature: FR06 - Course Overview

  Scenario: TC-03 Pelajar berhasil mendaftarkan course menggunakan kode pendaftaran yang valid
    Given pelajar telah login dengan email "devi123@example.com" dan password "devi123"
    When pelajar mencari course "Fundamental Teori Sistem Informasi"
    And pelajar memilih course "Fundamental Teori Sistem Informasi"
    Then halaman Course Overview ditampilkan

    When pelajar memasukkan kode pendaftaran "FSI2026A1"
    And pelajar mengklik tombol Daftar
    Then sistem memvalidasi kode pendaftaran
    And sistem menampilkan pesan "Sukses! Pendaftaran berhasil. Anda sekarang dapat mengakses materi dan kuis dari kursus ini."
    And status course berubah menjadi terdaftar