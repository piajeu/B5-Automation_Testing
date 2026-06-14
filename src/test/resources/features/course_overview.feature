Feature: FR06 - Course Overview

  Scenario: TC-03 Pelajar berhasil mendaftarkan course menggunakan kode pendaftaran yang valid
    Given pelajar telah login dengan email "devi123@example.com" dan password "devi123"
    When pelajar mencari course "Web Automation Testing 4"
    And pelajar memilih course "Web Automation Testing 4"
    Then halaman Course Overview ditampilkan
    When pelajar memasukkan kode pendaftaran "WAT2026SEL"
    And pelajar mengklik tombol Daftar
    Then sistem memvalidasi kode pendaftaran
    And sistem menampilkan pesan "Pendaftaran berhasil"

  Scenario: TC-06 Pelajar gagal mendaftarkan course karena kode pendaftaran kosong
    Given pelajar telah login dengan email "devi123@example.com" dan password "devi123"
    When pelajar mencari course "Pemrograman Web"
    And pelajar memilih course "Pemrograman Web"
    Then halaman Course Overview ditampilkan
    When pelajar tidak mengisi kode pendaftaran
    And pelajar mengklik tombol Daftar
    Then sistem menolak proses pendaftaran
    And sistem menampilkan pesan error "Silakan masukkan kode pendaftaran!"

  Scenario: TC-07 Pelajar gagal mendaftarkan course karena kode pendaftaran berupa spasi
    Given pelajar telah login dengan email "devi123@example.com" dan password "devi123"
    When pelajar mencari course "Pemrograman Web"
    And pelajar memilih course "Pemrograman Web"
    Then halaman Course Overview ditampilkan
    When pelajar memasukkan kode pendaftaran " "
    And pelajar mengklik tombol Daftar
    Then sistem menolak proses pendaftaran
    And sistem menampilkan pesan error "Kode Pendaftaran yang Anda masukkan tidak valid. Silakan coba lagi!"

    