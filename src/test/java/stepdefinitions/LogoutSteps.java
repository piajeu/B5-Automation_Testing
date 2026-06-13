package stepdefinitions;

import io.cucumber.java.en.*;

import org.junit.Assert;

import pages.LoginPage;
import pages.DashboardPage;

import utils.DriverSetup;

public class LogoutSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("pelajar sudah login menggunakan email {string} dan password {string}")
    public void pelajar_sudah_login_menggunakan_email_dan_password(
            String email,
            String password) {

        DriverSetup.getDriver()
                .get("https://polban-space.cloudias79.com/jtk-learn/");

        loginPage =
                new LoginPage(DriverSetup.getDriver());

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickMasuk();

        dashboardPage =
                new DashboardPage(DriverSetup.getDriver());
    }

    @When("pelajar mengklik nama akun {string} pada header")
    public void pelajar_mengklik_nama_akun_pada_header(
            String namaAkun) {

        dashboardPage.clickAccountMenu();
    }

    @And("pelajar mengklik opsi {string}")
    public void pelajar_mengklik_opsi(
            String opsi) {

        dashboardPage.clickLogout();
    }

    @Then("sistem menghapus sesi aktif pelajar")
    public void sistem_menghapus_sesi_aktif_pelajar() {

        loginPage =
                new LoginPage(DriverSetup.getDriver());

        Assert.assertTrue(
                "Logout gagal",
                loginPage.isLoginPageDisplayed()
        );
    }

    @And("halaman berpindah ke halaman Login")
    public void halaman_berpindah_ke_halaman_login() {

        Assert.assertTrue(
                DriverSetup.getDriver()
                        .getCurrentUrl()
                        .contains("jtk-learn")
        );
    }

    @And("halaman Login menampilkan teks {string}")
    public void halaman_login_menampilkan_teks(
            String text) {

        Assert.assertTrue(
                DriverSetup.getDriver()
                        .getPageSource()
                        .contains(text)
        );
    }

    @And("halaman Login menampilkan field Email")
    public void halaman_login_menampilkan_field_email() {

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed()
        );
    }

    @And("halaman Login menampilkan field Password")
    public void halaman_login_menampilkan_field_password() {

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed()
        );
    }

    @And("halaman Login menampilkan tombol {string}")
    public void halaman_login_menampilkan_tombol(
            String tombol) {

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed()
        );
    }
}