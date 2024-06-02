package hooks;

import driver.DriverManager;
import io.cucumber.java.*;

public class CucumberHooks {

    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("================ afterAll ================");

    }

    @Before
    public void beforeScenario() {
        System.out.println("================ beforeScenario ================");
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("================ afterScenario ================");
        DriverManager.quit();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        System.out.println("================ beforeStep ================");
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("================ afterStep ================");
        if (scenario.isFailed()) {
//            CaptureHelpers.takeScreenshot(scenario.getName());
        }
    }

}
