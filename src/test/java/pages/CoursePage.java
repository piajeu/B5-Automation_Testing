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

    public void checkForReload() {
        try {
            Thread.sleep(2000); // Wait a moment initially to let it load
            String pageSource = driver.getPageSource();
            if (!pageSource.contains("Lanjutkan Kursus") && !pageSource.contains("Lanjutkan")) {
                System.out.println("Course details not fully loaded, refreshing page...");
                driver.navigate().refresh();
                Thread.sleep(5000); // Wait 5 seconds after refresh
                PageFactory.initElements(driver, this);
            }
        } catch (Exception e) {
            // Ignore
        }
    }

    public void clickLanjutkanKursus() {
        checkForReload();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(btnLanjutkanKursus));
        
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnLanjutkanKursus);
        try { Thread.sleep(1000); } catch (Exception e) {}
        js.executeScript("arguments[0].click();", btnLanjutkanKursus);
    }
}
