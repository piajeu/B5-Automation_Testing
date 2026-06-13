package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePage {

    WebDriver driver;

    @FindBy(xpath = "//h1 | //h2 | //h3[contains(text(),'Fundamental')]")
    private WebElement courseTitle;

    @FindBy(xpath = "//input[@placeholder='Kode Pendaftaran']")
    private WebElement txtRegistrationCode;

    @FindBy(xpath = "//button[contains(text(),'Daftar')]")
    private WebElement btnDaftar;

    @FindBy(xpath = "//*[contains(text(),'Sukses!')]")
    private WebElement successTitle;

    @FindBy(xpath = "//*[contains(text(),'Pendaftaran berhasil')]")
    private WebElement successMessage;

    @FindBy(xpath = "//button[contains(text(),'Tutup')]")
    private WebElement btnTutup;

    public CoursePage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public String getCourseTitle() {

        return courseTitle.getText();
    }

    public void enterRegistrationCode(String code) {

        txtRegistrationCode.clear();

        txtRegistrationCode.sendKeys(code);
    }

    public void clickDaftar() {

        btnDaftar.click();
    }

    public String getSuccessTitle() {

        return successTitle.getText();
    }

    public String getSuccessMessage() {

        return successMessage.getText();
    }

    public void clickTutup() {

        btnTutup.click();
    }
}