package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverSetup;

public class Hooks {

    @Before
    public void setUp() {

        DriverSetup.getDriver();
    }

    @After
    public void tearDown() {

        DriverSetup.driver.quit();
    }
}