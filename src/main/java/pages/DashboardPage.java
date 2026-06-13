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

    @FindBy(css = "li.nav-name.dropdown a.nav-link")
    private WebElement accountMenu;

    @FindBy(css = "button.dropdown-button")
    private WebElement btnLogout;

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
        String text = headerNav.getText();
        if (text == null || text.trim().isEmpty()) {
            text = headerNav.getAttribute("textContent");
        }
        return text;
    }

    // Mengambil teks footer
    public String getFooterText() {
        String text = footerSection.getText();
        if (text == null || text.trim().isEmpty()) {
            text = footerSection.getAttribute("textContent");
        }
        return text;
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

    public void clickAccountMenu() {
        try {
            accountMenu.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", accountMenu);
        }
    }

    public void clickLogout() {
        try {
            btnLogout.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnLogout);
        }
    }
}