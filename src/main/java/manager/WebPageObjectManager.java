package manager;

import org.openqa.selenium.WebDriver;
import pageObjects.GoogleTestObjectForFirstPage;

public class WebPageObjectManager {
    private WebDriver driver;
    private GoogleTestObjectForFirstPage testObject1;

    public WebPageObjectManager(WebDriver driver){
        this.driver = driver;
    }

    public GoogleTestObjectForFirstPage getTestStepdefs()
    {
        return (testObject1 == null) ? testObject1 = new GoogleTestObjectForFirstPage(driver) : testObject1;
    }
}