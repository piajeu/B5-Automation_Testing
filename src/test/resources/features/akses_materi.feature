Feature: FR02 - Access Materials

  Scenario: Tampilan halaman akses materi video untuk pelajar yang sudah enroll
    Given pelajar telah login dengan email "devi123@example.com" dan password "devi123"
    And pelajar berada di halaman Detail Kursus untuk "Software Testing"
    When pelajar mengklik tombol Lanjutkan Kursus
    And pelajar memilih materi video "Pengujian White Box dengan contoh | Path Testing" pada navigasi bar
    Then halaman akses materi video harus tampil
    And nama materi harus muncul di bagian atas
    And frame video YouTube harus tampil
    And tombol selanjutnya ">" harus muncul
