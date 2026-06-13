package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverSetup {

    public static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--incognito");

            options.addArguments("--disable-save-password-bubble");

            options.addArguments("--disable-features=PasswordCheck");

            options.addArguments("--disable-features=PasswordLeakDetection");

            options.addArguments("--disable-notifications");

            options.setExperimentalOption(
                    "prefs",
                    java.util.Map.of(
                            "credentials_enable_service", false,
                            "profile.password_manager_enabled", false
                    )
            );

            driver = new ChromeDriver(options);

            driver.manage().window().maximize();

            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;
    }
}