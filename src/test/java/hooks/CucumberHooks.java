package hooks;

import commons.ConfigLoaderEnvironment;
import driver.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CucumberHooks {

    @BeforeAll
    public static void beforeAll() {
        ConfigLoaderEnvironment.getConfig();
    }

    @AfterAll
    public static void afterAll() {

    }

    @Before
    public void beforeScenario() {
    }

    @After
    public void afterScenario(Scenario scenario) {
        DriverManager.quit();
    }


    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot Failed");
        }
    }

}
