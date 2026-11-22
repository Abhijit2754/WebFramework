package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import manager.DriverManager;
import manager.WebPageObjectManager;
import org.openqa.selenium.WebDriver;
import utility.ReadWrite;

public class TestContext {
    private DriverManager driverManager;
    private WebPageObjectManager webPageObjectManager;
    private ScenarioContext scenarioContext;
    private ReadWrite readWrite;
    public static final String filePath = "./src/main/resources/configuration.properties";

    public TestContext(){
        scenarioContext = new ScenarioContext();
        readWrite = new ReadWrite(filePath);
        driverManager = new DriverManager();
        webPageObjectManager = new WebPageObjectManager(driverManager.getDriver());
    }

    public DriverManager getDriverManager(){return driverManager;}

    public WebPageObjectManager getWebPageObjectManager(){return webPageObjectManager;}

    public ScenarioContext getScenarioContext(){return scenarioContext;}

    public ReadWrite getReadWrite(){return readWrite;}
}
