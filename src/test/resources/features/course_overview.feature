Feature: FR06 - Course Overview

  Scenario: TC-03 Pelajar berhasil mendaftarkan course menggunakan kode pendaftaran yang valid
    Given pelajar telah login dengan email "devi123@example.com" dan password "devi123"
    When pelajar mencari course "Web Automation Testing 3"
    And pelajar memilih course "Web Automation Testing 3"
    Then halaman Course Overview ditampilkan
    When pelajar memasukkan kode pendaftaran "WAT2026SEL"
    And pelajar mengklik tombol Daftar
    Then sistem memvalidasi kode pendaftaran
    And sistem menampilkan pesan "Pendaftaran berhasil"