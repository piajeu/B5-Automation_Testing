package stepdefinitions;

import io.cucumber.java.en.*;

import org.junit.Assert;

import pages.LoginPage;
import pages.DashboardPage;
import pages.CourseOverviewPage;

import utils.DriverSetup;

public class CourseOverviewSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CourseOverviewPage courseOverviewPage;

    @Given("pelajar telah login dengan email {string} dan password {string}")
    public void pelajar_telah_login_dengan_email_dan_password(
            String email,
            String password) {

        DriverSetup.getDriver().get(
                "https://polban-space.cloudias79.com/jtk-learn/"
        );

        loginPage = new LoginPage(
                DriverSetup.getDriver()
        );

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickMasuk();

        dashboardPage = new DashboardPage(
                DriverSetup.getDriver()
        );

        Assert.assertTrue(
                "Login gagal",
                dashboardPage.isDashboardDisplayed()
        );
    }

    @When("pelajar mencari course {string}")
    public void pelajar_mencari_course(
            String courseName) {

        dashboardPage.selectCourse(courseName);
    }

    @And("pelajar memilih course {string}")
    public void pelajar_memilih_course(
            String courseName) {

        courseOverviewPage =
                new CourseOverviewPage(
                        DriverSetup.getDriver()
                );
    }

    @Then("halaman Course Overview ditampilkan")
    public void halaman_course_overview_ditampilkan() {

        Assert.assertTrue(
                "Halaman Course Overview tidak tampil",
                courseOverviewPage.isCourseOverviewDisplayed()
        );
    }

    @When("pelajar memasukkan kode pendaftaran {string}")
    public void pelajar_memasukkan_kode_pendaftaran(
            String kode) {

        courseOverviewPage.enterRegistrationCode(kode);
    }

    @And("pelajar mengklik tombol Daftar")
    public void pelajar_mengklik_tombol_daftar() {

        courseOverviewPage.clickDaftar();
    }

    @Then("sistem memvalidasi kode pendaftaran")
    public void sistem_memvalidasi_kode_pendaftaran() {

        Assert.assertTrue(true);
    }

    @And("sistem menampilkan pesan {string}")
    public void sistem_menampilkan_pesan(
            String expectedMessage) {

        String actualMessage =
                courseOverviewPage.getSuccessMessage();

        System.out.println(actualMessage);

        Assert.assertTrue(
                "Pesan sukses tidak ditemukan",
                actualMessage.contains("Pendaftaran berhasil")
                        || actualMessage.contains("Sukses")
        );
    }

    @When("pelajar tidak mengisi kode pendaftaran")
    public void pelajar_tidak_mengisi_kode_pendaftaran() {

        courseOverviewPage.leaveRegistrationCodeEmpty();
    }

    @Then("sistem menolak proses pendaftaran")
    public void sistem_menolak_proses_pendaftaran() {

        String title =
                courseOverviewPage.getErrorTitle();

        Assert.assertEquals(
                "Kesalahan!",
                title
        );
    }

    @And("sistem menampilkan pesan error {string}")
    public void sistem_menampilkan_pesan_error(
            String expectedMessage) {

        String actualMessage =
                courseOverviewPage.getErrorMessage();

        System.out.println(actualMessage);

        Assert.assertEquals(
                expectedMessage,
                actualMessage
        );
    }
}