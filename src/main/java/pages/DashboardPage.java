package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    WebDriver driver;

    @FindBy(css = "nav.custom-navbar")
    private WebElement headerNav;

    @FindBy(tagName = "footer")
    private WebElement footerSection;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Validasi dashboard berhasil ditampilkan
    public boolean isDashboardDisplayed() {
        return headerNav.isDisplayed();
    }

    // Mengambil teks navigasi header
    public String getHeaderNavigationText() {
        return headerNav.getText();
    }

    // Mengambil teks footer
    public String getFooterText() {
        return footerSection.getText();
    }

    // Mengambil URL halaman saat ini
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Memilih course berdasarkan nama course
    public void selectCourse(String courseName) {

        WebElement courseLink = driver.findElement(
                By.xpath("//*[contains(text(),'" + courseName + "')]")
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                courseLink
        );

        js.executeScript(
                "arguments[0].click();",
                courseLink
        );
    }
}