package utility;

import base.Basepage;
import base.TestContext;
import com.beust.ah.A;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

public class ActionUtility {

    Actions actions;
    Logger logger = LoggerFactory.getLogger(ActionUtility.class);

    protected void waitForElementToBeVisible(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public ActionUtility(WebDriver driver)
    {
        actions = new Actions(driver);
    }

    public void click(TestContext testContext, WebElement element, String elementName)
    {
        try
        {
            waitForElementToBeVisible(testContext.getDriverManager().getDriver(),element);
            actions.click(element).perform();
            logger.info(elementName+ " is Clicked");
        }catch (ElementClickInterceptedException e)
        {
            logger.info(elementName +" is not clicked");
            Assert.fail(elementName +" is not clicked");
        }
    }

    public void doubleClick(TestContext testContext, WebElement element, String elementName)
    {
        try {
            waitForElementToBeVisible(testContext.getDriverManager().getDriver(),element);
            actions.doubleClick(element).perform();
            logger.info(elementName + " is clicked double");
        }
        catch (ElementClickInterceptedException e)
        {
            logger.info(elementName+" is not clicked two times");
            Assert.fail(elementName+" is not clicked two times");
        }
    }

    public void
}
