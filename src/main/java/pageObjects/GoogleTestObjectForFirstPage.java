
package pageObjects;

import base.Basepage;
import base.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class GoogleTestObjectForFirstPage extends Basepage {

    private static final Logger logger = LoggerFactory.getLogger(GoogleTestObjectForFirstPage.class);

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'About')]")
    public WebElement txt_about;

    @FindBy(xpath = "//a[contains(text(),'Store')]")
    public WebElement txt_store;

    @FindBy(xpath = "//textarea[@class=\"gLFyf\"]")
    public WebElement input_googleSearch;

    @FindBy(xpath = "(//span[text()='news today'])[1]")
    public WebElement txt_suggetionText;

    public GoogleTestObjectForFirstPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void userOpenBrowser(TestContext testContext, String arg) throws FileNotFoundException {
        driver.get(testContext.getPropertiesUtility().getValueFromPropertiesFile(arg));
    }

    public void userVerifyTheText(TestContext testContext, String arg0) {
        switch (arg0) {
            case "About" :
                isDisplayedWithTextValidation(testContext,txt_about,"innerText",arg0);
                takeScreenshot(testContext);
                isDisplayed(testContext,txt_about);
                break;
            case  "Store" :
                isDisplayedWithTextValidation(testContext,txt_store, "innerText",arg0);
                takeScreenshot(testContext);
                isDisplayed(testContext,txt_store);
                break;
        }
    }

    public void userEnterValue(TestContext testContext, String arg0) {
        sendKeys(testContext,input_googleSearch,arg0);
        takeScreenshot(testContext);
    }

    public void userClickOnSuggetion(TestContext testContext, String arg) throws Throwable {
        click(testContext,txt_suggetionText,arg);
    }
}