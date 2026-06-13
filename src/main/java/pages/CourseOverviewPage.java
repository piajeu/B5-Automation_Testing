package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CourseOverviewPage {

    WebDriver driver;

    @FindBy(css = "input[type='search']")
    private WebElement txtSearchCourse;

    @FindBy(id = "enrollment_code")
    private WebElement txtEnrollmentCode;

    @FindBy(xpath = "//button[contains(text(),'Daftar')]")
    private WebElement btnDaftar;

    @FindBy(className = "alert-success")
    private WebElement successMessage;

    public CourseOverviewPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void searchCourse(String courseName) {

        txtSearchCourse.clear();

        txtSearchCourse.sendKeys(courseName);
    }

    public void selectCourse(String courseName) {

        WebElement courseCard =
                driver.findElement(
                        By.xpath("//*[contains(text(),'" + courseName + "')]")
                );

        courseCard.click();
    }

    public void enterEnrollmentCode(String code) {

        txtEnrollmentCode.clear();

        txtEnrollmentCode.sendKeys(code);
    }

    public void clickDaftar() {

        btnDaftar.click();
    }

    public String getSuccessMessage() {

        return successMessage.getText();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }
}