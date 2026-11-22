
package pageObjects;

import base.Basepage;
import base.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestObject1 extends Basepage {

    private static final Logger logger =
            LoggerFactory.getLogger(TestObject1.class);

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'About')]")
    public WebElement txt_about;

    public TestObject1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void userOpenBrowser(TestContext testContext, String arg) {

        String url = testContext.getReadWrite().getFilePath(arg);

        logger.info("URL received: {}", url);
        logger.info("Driver instance: {}", driver);

        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL is empty or null");
        }

        driver.get(url);

    }

    public void userVerifyTheText(TestObject1 testObject1, String arg0) {
        if(arg0.equals(txt_about.getText())){
            System.out.println("Both text are equals");
        }
        else {
            System.out.println("Both text are not equals");
        }
    }
}
