package base;

import manager.DriverManager;
import manager.WebPageObjectManager;
import utility.ActionUtility;
import utility.PropertiesUtility;

import java.io.FileNotFoundException;

public class TestContext {

    private final DriverManager driverManager;
    private WebPageObjectManager webPageObjectManager;
    private final ScenarioContext scenarioContext;
    private ActionUtility actionUtility;
    private final PropertiesUtility propertiesUtility;
    public static final String filePath ="./src/main/resources/configuration.properties";

    public TestContext() throws FileNotFoundException {
        scenarioContext = new ScenarioContext();
        propertiesUtility = new PropertiesUtility(filePath);
        propertiesUtility.inititatFileReader();
        driverManager = new DriverManager();
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public PropertiesUtility getPropertiesUtility() {
        return propertiesUtility;
    }

    public ActionUtility getActionUtility() {
        if (actionUtility == null) {
            actionUtility = new ActionUtility(driverManager.getDriver());
        }
        return actionUtility;
    }

    public WebPageObjectManager getWebPageObjectManager() {
        if (webPageObjectManager == null) {
            webPageObjectManager = new WebPageObjectManager(driverManager.getDriver());
        }
        return webPageObjectManager;
    }
}
