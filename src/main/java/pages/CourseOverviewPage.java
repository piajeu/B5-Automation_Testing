package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CourseOverviewPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CourseOverviewPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        PageFactory.initElements(driver, this);
    }

    // ==========================
    // ELEMENTS
    // ==========================

    @FindBy(css = "input[placeholder='Kode Pendaftaran']")
    private WebElement txtRegistrationCode;

    @FindBy(css = "button.button-enroll")
    private WebElement btnDaftar;

    // Locator generic popup sukses

    @FindBy(xpath = "//*[contains(text(),'Pendaftaran berhasil')]")
    private WebElement successMessage;

    @FindBy(id = "swal2-title")
    private WebElement errorTitle;

    @FindBy(id = "swal2-html-container")
    private WebElement errorMessage;

    // ==========================
    // ACTIONS
    // ==========================

    public void enterRegistrationCode(String code) {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtRegistrationCode
                )
        );

        scrollToElement(txtRegistrationCode);

        txtRegistrationCode.clear();

        txtRegistrationCode.sendKeys(code);
    }

    public void leaveRegistrationCodeEmpty() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        txtRegistrationCode
                )
        );

        scrollToElement(txtRegistrationCode);

        txtRegistrationCode.clear();
    }

    public void clickDaftar() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        btnDaftar
                )
        );

        scrollToElement(btnDaftar);

        try {

            btnDaftar.click();

        } catch (Exception e) {

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].click();",
                    btnDaftar
            );
        }
    }

    // ==========================
    // VALIDATIONS
    // ==========================

    public boolean isCourseOverviewDisplayed() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(
                            txtRegistrationCode
                    )
            );

            return txtRegistrationCode.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public String getSuccessMessage() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(
                            successMessage
                    )
            );

            return successMessage.getText();

        } catch (Exception e) {

            return "";
        }
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    // ==========================
    // HELPER
    // ==========================

    private void scrollToElement(
            WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    public String getErrorTitle() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        errorTitle
                )
        );

        return errorTitle.getText();
    }

    public String getErrorMessage() {

        wait.until(
                ExpectedConditions.visibilityOf(
                        errorMessage
                )
        );

        return errorMessage.getText();
    }
}