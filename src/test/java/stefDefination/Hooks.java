
package stefDefination;

import base.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private final TestContext testContext;

    private static final Logger logger =
            LoggerFactory.getLogger(Hooks.class);

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {

        logger.info(
                "Starting Scenario: {} | Thread ID: {}",
                scenario.getName(),
                Thread.currentThread().getId()
        );

        testContext.getScenarioContext()
                .setScenarioContext("currentScenarioObject", scenario);
    }

    @After
    public void afterScenario(Scenario scenario) {

        try {

            if (scenario.isFailed()) {

                scenario.log("Scenario Tags: " +
                        scenario.getSourceTagNames());

                scenario.log("Scenario Name: " +
                        scenario.getName());

                scenario.log("Scenario Line: " +
                        scenario.getLine());

                scenario.log("Scenario URI: " +
                        scenario.getUri());

                captureScreenshot(scenario);
            }

        } catch (Exception e) {

            logger.error(
                    "Error during scenario cleanup: {}",
                    scenario.getName(),
                    e
            );

        } finally {

            // Always close the current thread's driver
            if (isBrowserExecution()) {

                testContext.getDriverManager().quitDriver();
            }

            logger.info(
                    "Finished Scenario: {} | Thread ID: {}",
                    scenario.getName(),
                    Thread.currentThread().getId()
            );
        }
    }

    private void captureScreenshot(Scenario scenario) {

        try {

            WebDriver driver =
                    testContext.getDriverManager().getDriver();

            if (driver != null) {

                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );
            }

        } catch (Exception e) {

            logger.error(
                    "Screenshot capture failed for scenario: {}",
                    scenario.getName(),
                    e
            );
        }
    }

    private boolean isBrowserExecution() {

        String driverName =
                System.getProperty("driverName", "CHROME");

        return !driverName.equalsIgnoreCase("noDriver");
    }
}
