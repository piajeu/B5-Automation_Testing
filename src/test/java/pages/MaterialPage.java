package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MaterialPage {
    WebDriver driver;

    @FindBy(css = "iframe")
    private WebElement frameVideo;

    @FindBy(css = "button.course-next-button")
    private WebElement btnNext;

    @FindBy(css = ".material-title, h1, h2")
    private WebElement lblMaterialTitle;

    public MaterialPage(WebDriver driver) {
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
            if (pageSource.contains("There are no materials") || pageSource.contains("Belum ada kursus")) {
                System.out.println("Materials not loaded, refreshing page...");
                driver.navigate().refresh();
                Thread.sleep(5000); // Wait 5 seconds after refresh
                PageFactory.initElements(driver, this);
            }
        } catch (Exception e) {
            // Ignore
        }
    }

    public void selectMaterial(String materialName) {
        checkForReload();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement materialLink = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.xpath("//*[contains(text(), '" + materialName + "')]")
        ));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", materialLink);
        try { Thread.sleep(1000); } catch (Exception e) {}
        js.executeScript("arguments[0].click();", materialLink);
    }

    public boolean isVideoFrameDisplayed() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(frameVideo));
            return frameVideo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNextButtonDisplayed() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(btnNext));
            return btnNext.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMaterialTitle() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(lblMaterialTitle));
            return lblMaterialTitle.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
