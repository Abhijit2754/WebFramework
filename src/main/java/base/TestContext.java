package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import manager.DriverManager;
import manager.WebPageObjectManager;
import org.openqa.selenium.WebDriver;
import utility.ActionUtility;
import utility.PropertiesUtility;
import utility.ReadWrite;

import java.io.FileNotFoundException;

public class TestContext {
    private DriverManager driverManager;
    private WebPageObjectManager webPageObjectManager;
    private ScenarioContext scenarioContext;
//    private ReadWrite readWrite;
    private ActionUtility actionUtility;
    private PropertiesUtility propertiesUtility;
    public static final String filePath = "./src/main/resources/configuration.properties";

    public TestContext() throws FileNotFoundException {
        scenarioContext = new ScenarioContext();
        propertiesUtility = new PropertiesUtility(filePath);
        propertiesUtility.inititatFileReader();
        driverManager = new DriverManager();
        actionUtility = new ActionUtility(driverManager.getDriver());
        webPageObjectManager = new WebPageObjectManager(driverManager.getDriver());
    }

    public DriverManager getDriverManager(){return driverManager;}

    public WebPageObjectManager getWebPageObjectManager(){return webPageObjectManager;}

    public ScenarioContext getScenarioContext(){return scenarioContext;}

    public PropertiesUtility getPropertiesUtility(){return propertiesUtility;}

    public ActionUtility getActionUtility(){
        return actionUtility;
    }
}
