import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/features",
        monochrome = true,
        glue = {"stepsDefinition", "hooks"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"},
        tags = "@register"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
