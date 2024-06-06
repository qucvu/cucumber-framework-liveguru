package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/java/features",
        monochrome = true,
        glue = {"stepsDefinition", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/TestRunnerCategoryCMS.html",
                "json:target/cucumber-reports/TestRunnerCategoryCMS.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@register"
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
