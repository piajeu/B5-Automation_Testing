package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePage {
    WebDriver driver;

    @FindBy(xpath = "//*[contains(text(), 'Lanjutkan Kursus') or contains(text(), 'Lanjutkan')]")
    private WebElement btnLanjutkanKursus;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(org.openqa.selenium.By.xpath("//*[contains(text(), 'Loading')]")));
        } catch (Exception e) {
            // Ignore
        }
        PageFactory.initElements(driver, this);
    }

    public void clickLanjutkanKursus() {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(btnLanjutkanKursus));
        
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnLanjutkanKursus);
        try { Thread.sleep(1000); } catch (Exception e) {}
        js.executeScript("arguments[0].click();", btnLanjutkanKursus);
    }
}
