package pages;

import org.openqa.selenium.By;
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

    public String getHeaderNavigationText() {
        return headerNav.getText();
    }

    public String getFooterText() {
        return footerSection.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void selectCourse(String courseName) {
        WebElement courseLink = driver.findElement(By.xpath("//*[contains(text(), '" + courseName + "')]"));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", courseLink);
        try { Thread.sleep(500); } catch (Exception e) {}
        js.executeScript("arguments[0].click();", courseLink);
    }
}
