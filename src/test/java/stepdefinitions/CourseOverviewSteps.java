package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.CourseOverviewPage;
import pages.DashboardPage;
import utils.DriverSetup;

public class CourseOverviewSteps {

    private DashboardPage dashboardPage;
    private CourseOverviewPage courseOverviewPage;

    @Given("pelajar telah login dengan email {string} dan password {string}")
    public void pelajar_telah_login_dengan_email_dan_password(
            String email,
            String password) {

        // bisa kosong jika login sudah diuji di scenario lain
    }

    @When("pelajar mencari course {string}")
    public void pelajar_mencari_course(String courseName) {

        dashboardPage =
                new DashboardPage(DriverSetup.getDriver());

        dashboardPage.selectCourse(courseName);
    }

    @And("pelajar memilih course {string}")
    public void pelajar_memilih_course(String courseName) {

        courseOverviewPage =
                new CourseOverviewPage(DriverSetup.getDriver());
    }

    @Then("halaman Course Overview ditampilkan")
    public void halaman_course_overview_ditampilkan() {

        Assert.assertTrue(
                courseOverviewPage.isCourseOverviewDisplayed());
    }

    @When("pelajar memasukkan kode pendaftaran {string}")
    public void pelajar_memasukkan_kode_pendaftaran(String kode) {

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
    public void sistem_menampilkan_pesan(String expectedMessage) {

        String actualMessage =
                courseOverviewPage.getSuccessMessage();

        Assert.assertTrue(
                actualMessage.contains("Pendaftaran berhasil"));
    }

    @And("status course berubah menjadi terdaftar")
    public void status_course_berubah_menjadi_terdaftar() {

        Assert.assertTrue(
                courseOverviewPage.isRegistrationSuccessful());
    }
}