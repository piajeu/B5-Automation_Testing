package stepdefinitions;

import io.cucumber.java.en.*;

import org.junit.Assert;

import pages.LoginPage;
import pages.DashboardPage;

import utils.DriverSetup;

public class LoginSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("aplikasi JTK Learn terbuka di halaman login {string}")
    public void aplikasi_jtk_learn_terbuka_di_halaman_login(String url) {

        DriverSetup.getDriver().get(url);

        loginPage = new LoginPage(
                DriverSetup.getDriver()
        );
    }

    @When("pelajar memasukkan email {string}")
    public void pelajar_memasukkan_email(String email) {

        loginPage.enterEmail(email);
    }

    @And("pelajar memasukkan password {string}")
    public void pelajar_memasukkan_password(String password) {

        loginPage.enterPassword(password);
    }

    @And("pelajar mengklik tombol Masuk")
    public void pelajar_mengklik_tombol_masuk() {

        loginPage.clickMasuk();
    }

    @Then("sistem memvalidasi kredensial, login berhasil, dan halaman berpindah ke dashboard Pelajar")
    public void sistem_memvalidasi_kredensial_login_berhasil_dan_halaman_berpindah_ke_dashboard_pelajar() {

        dashboardPage = new DashboardPage(
                DriverSetup.getDriver()
        );

        Assert.assertTrue(
                "Dashboard tidak berhasil ditampilkan",
                dashboardPage.isDashboardDisplayed()
        );
    }

    @And("header menampilkan menu navigasi yang berisi {string}")
    public void header_menampilkan_menu_navigasi_yang_berisi(String expectedNavText) {

        String actualNavText =
                dashboardPage.getHeaderNavigationText();

        String[] menus =
                expectedNavText.split("\\s*\\|\\s*");

        for (String menu : menus) {

            Assert.assertTrue(
                    "Menu '" + menu + "' tidak ditemukan",
                    actualNavText.contains(menu)
            );
        }
    }

    @And("footer menampilkan teks {string}")
    public void footer_menampilkan_teks(String expectedFooterText) {

        String actualFooterText =
                dashboardPage.getFooterText();

        Assert.assertTrue(
                "Footer tidak sesuai",
                actualFooterText.contains(expectedFooterText)
        );
    }
}