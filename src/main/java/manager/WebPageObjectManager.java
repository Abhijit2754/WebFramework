package manager;

import org.openqa.selenium.WebDriver;
import pageObjects.TestObject1;

public class WebPageObjectManager {
    private WebDriver driver;

    private TestObject1 testObject1;

    public WebPageObjectManager(WebDriver driver){
        this.driver = driver;
    }

    public TestObject1 getTestStepdefs()
    {
        return (testObject1 == null) ? testObject1 = new TestObject1(driver) : testObject1;
    }
}