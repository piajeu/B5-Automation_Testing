package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(css = "input[type='email']")
    private WebElement txtEmail;

    @FindBy(css = "input[type='password']")
    private WebElement txtPassword;

    @FindBy(css = "button[type='submit']")
    private WebElement btnMasuk;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage(String url) {
        driver.get(url);
    }

    public void enterEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickMasuk() {
        btnMasuk.click();
    }
}