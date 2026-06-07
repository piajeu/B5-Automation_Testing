package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.DashboardPage;
import pages.CoursePage;
import pages.MaterialPage;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CoursePage coursePage;
    private MaterialPage materialPage;
    private String selectedMaterialName;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    // --- FR01: LOGIN STEPS ---

    @Given("aplikasi JTK Learn terbuka di halaman login {string}")
    public void aplikasi_jtk_learn_terbuka_di_halaman_login(String url) {
        driver.get(url);
        loginPage = new LoginPage(driver);
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
        dashboardPage = new DashboardPage(driver);
        String currentUrl = dashboardPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.endsWith("/login"));
    }

    @And("header menampilkan menu navigasi yang berisi {string}")
    public void header_menampilkan_menu_navigasi_yang_berisi(String expectedNavText) {
        String actualNavText = dashboardPage.getHeaderNavigationText();
        String[] menus = expectedNavText.split("\\s*\\|\\s*");
        for (String menu : menus) {
            Assert.assertTrue("Menu '" + menu + "' tidak ditemukan di header navigasi", actualNavText.contains(menu));
        }
    }

    @And("footer menampilkan teks {string}")
    public void footer_menampilkan_teks(String expectedFooterText) {
        String actualFooterText = dashboardPage.getFooterText();
        Assert.assertTrue(actualFooterText.contains(expectedFooterText));
    }

    // --- FR02: ACCESS MATERIALS PRECONDITIONS & STEPS ---

    @Given("pelajar telah login dengan email {string} dan password {string}")
    public void pelajar_telah_login_dengan_email_dan_password(String email, String password) {
        driver.get("https://polban-space.cloudias79.com/jtk-learn/");
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickMasuk();
        try { Thread.sleep(2000); } catch(Exception e) {}
        dashboardPage = new DashboardPage(driver);
    }

    @And("pelajar berada di halaman Detail Kursus untuk {string}")
    public void pelajar_berada_di_halaman_detail_kursus_untuk(String courseName) {
        dashboardPage.selectCourse(courseName);
        try { Thread.sleep(4000); } catch(Exception e) {}
        coursePage = new CoursePage(driver);
    }

    @When("pelajar mengklik tombol Lanjutkan Kursus")
    public void pelajar_mengklik_tombol_lanjutkan_kursus() {
        coursePage.clickLanjutkanKursus();
        try { Thread.sleep(4000); } catch(Exception e) {}
        materialPage = new MaterialPage(driver);
        materialPage.checkForReload();
    }

    @And("pelajar memilih materi video {string} pada navigasi bar")
    public void pelajar_memilih_materi_video_pada_navigasi_bar(String materialName) {
        this.selectedMaterialName = materialName;
        materialPage.selectMaterial(materialName);
        try { Thread.sleep(3000); } catch(Exception e) {}
    }

    @Then("halaman akses materi video harus tampil")
    public void halaman_akses_materi_video_harus_tampil() {
        Assert.assertNotNull(materialPage.getMaterialTitle());
    }

    @And("nama materi harus muncul di bagian atas")
    public void nama_materi_harus_muncul_di_bagian_atas() {
        String actualTitle = materialPage.getMaterialTitle();
        Assert.assertTrue(actualTitle.contains(selectedMaterialName));
    }

    @And("frame video YouTube harus tampil")
    public void frame_video_youtube_harus_tampil() {
        Assert.assertTrue(materialPage.isVideoFrameDisplayed());
    }

    @And("tombol selanjutnya {string} harus muncul")
    public void tombol_selanjutnya_harus_muncul(String nextButtonText) {
        Assert.assertTrue(materialPage.isNextButtonDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
