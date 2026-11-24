package base;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

public class Basepage {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected void waitForElementToBeVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(TestContext testContext, WebElement element, String elementName) throws Throwable {
        try {
            waitForElementToBeVisible(testContext.getDriverManager().getDriver(), element);
            element.click();
            logger.info(elementName + " is clicked");
            logToHtmlReport(testContext, elementName + " is clicked");
        } catch (Exception e) {
            logger.info(elementName + " unable to click");
            logToHtmlReport(testContext, elementName + " is not clicked");
            throw e;
        }
    }

    protected void logToHtmlReport(TestContext testContext, String text) {
        ((Scenario) testContext.getScenarioContext().getScenarioContext("currentScenarioObject")).log(text);
    }

    public void Screenshoot(WebDriver driver, Scenario scenario) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        scenario.attach(ts.getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
    }

    protected void takeScreenshot(TestContext testContext) {
        Screenshoot(testContext.getDriverManager().getDriver(), (Scenario) testContext.getScenarioContext().getScenarioContext("currentScenarioObject"));
    }

    protected void validate1(TestContext testContext, Object expected, Object actual, String assertionFor) {
        try {
            Assert.assertEquals(actual, expected, assertionFor);
            logger.info(" - " + assertionFor + "  |   Actual: " + actual + " |  Expected: " + expected, true);
            logToHtmlReport(testContext, " - " + assertionFor + "  |   Actual: " + actual + " |  Expected: " + expected);
        } catch (AssertionError assertionError) {
            logger.info(" - " + assertionFor + "   |   Actual: " + actual + " |  Expected: " + expected, true);
            logToHtmlReport(testContext, " - " + assertionFor + "  |   Actual: " + actual + " |  Expected: " + expected);
            Assert.fail(assertionFor + "  |   Actual: " + actual + " |  Expected: " + expected);
        }
    }

    protected void validate(TestContext testContext,Object expected,Object actual,String assertionFor) {
        String message = assertionFor+ " | Actual: " + actual+ " | Expected: " + expected;
        try {
            Assert.assertEquals(actual,expected,assertionFor);
            logger.info("PASS: {}", message);
            logToHtmlReport(testContext, "PASS: " + message);
        } catch (AssertionError e) {
            logger.error("FAIL: {}", message);
            logToHtmlReport(testContext, "FAIL: " + message);
            throw e;
        }
    }

    protected boolean isDisplayedWithTextValidation(TestContext testContext, WebElement element, String attributeName, String expectedValue) {
        try {
            new WebDriverWait(testContext.getDriverManager().getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
            String attributeValue = element.getAttribute(attributeName);
            validate(testContext, expectedValue, attributeValue, "validate element's attribute value ");
            logger.info(attributeValue + " is displayed successfully.");
            logToHtmlReport(testContext, attributeValue + " is displayed successfully.");
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException exception) {
            Assert.fail("Element is not present");
            return false;
        }
    }

    public void clear(TestContext testContext, WebElement element, String txt) {
        waitForElementToBeVisible(testContext.getDriverManager().getDriver(), element);
        try {
            element.clear();
            logger.info("text " + txt + " is cleared in text box");
            logToHtmlReport(testContext, "text " + txt + " is cleared in text box");
        } catch (Exception e) {
            logger.info("clear " + txt + " is failed");
            Assert.fail();
        }
    }

    protected boolean isDisplayed(TestContext testContext, WebElement element) {
        try {
            waitForElementToBeVisible(testContext.getDriverManager().getDriver(), element);
            String attributeValue = element.getAttribute("name");
            logger.info(attributeValue + " is displayed successfully.");
            logToHtmlReport(testContext, attributeValue + " is displayed successfully.");
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException exception) {
            Assert.fail("Element is not present");
            return false;
        }
    }

    protected void sendKeys(TestContext testContext, WebElement element, String str) {
        waitForElementToBeVisible(testContext.getDriverManager().getDriver(), element);
        try {
            element.sendKeys(str);
            logger.info("User enter text : " + str);
        } catch (Exception e) {
            logger.info("Send keys " + str + " is failed");
            Assert.fail();
        }
    }
}