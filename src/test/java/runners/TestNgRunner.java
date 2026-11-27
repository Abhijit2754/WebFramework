package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.ReportListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(ReportListener.class)

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stefDefination"},
        plugin = {
                "pretty",
                "html:target/CucumberReports/CucumberReport.html",
                "json:target/cucumber-reports/cucumber.json",
        },
        monochrome = true,
        dryRun = false
)

public class TestNgRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}