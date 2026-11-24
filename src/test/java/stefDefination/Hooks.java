package stefDefination;

import base.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private final TestContext testContext;
    protected Logger logger = LoggerFactory.getLogger(Hooks.class);

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Before Hook Executed");
        testContext.getScenarioContext().setScenarioContext("currentScenarioObject", scenario);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (System.getProperty("driverName") != null && !(System.getProperty("driverName").equalsIgnoreCase("noDriver"))) {
            if (scenario.isFailed()) {
                scenario.log("Scenarios Tags :"+scenario.getSourceTagNames());
                scenario.log("Scenario Name : " + scenario.getName());
                scenario.log("Scenario line number : " + scenario.getLine());
                scenario.log("Scenario file Path : " + scenario.getUri());

                TakesScreenshot ts = (TakesScreenshot) testContext.getDriverManager().getDriver();
                scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
            }

            if (testContext.getDriverManager().getDriver() != null)
                testContext.getDriverManager().quitDriver();
        }
        testContext.getDriverManager().quitDriver();
    }
}