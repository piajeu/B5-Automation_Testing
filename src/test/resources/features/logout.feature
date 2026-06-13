Feature: FR02 - Logout

  Scenario: FR02-TC-001 - Pelajar berhasil logout dari aplikasi

    Given pelajar sudah login menggunakan email "devi123@example.com" dan password "devi123"
    When pelajar mengklik nama akun "Devi" pada header
    And pelajar mengklik opsi "Keluar"
    Then sistem menghapus sesi aktif pelajar
    And halaman berpindah ke halaman Login
    And halaman Login menampilkan teks "Selamat datang,"
    And halaman Login menampilkan field Email
    And halaman Login menampilkan field Password
    And halaman Login menampilkan tombol "Masuk"