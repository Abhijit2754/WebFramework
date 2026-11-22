
package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverManager.class);

    private WebDriver driver;

    public DriverManager() {
    }

    public WebDriver getDriver() {
        String driverName = System.getProperty("driverName") == null? "CHROME": System.getProperty("driverName");
        if (driver == null) driver = createDriver(driverName);
        return driver;
    }

    private WebDriver createDriver(String driverName) {

        return switch (driverName.toUpperCase()) {
            case "CHROME" -> createChromeDriver();
            case "EDGE" -> createEdgeDriver();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + driverName);
        };
    }

    private WebDriver createChromeDriver() {
        logger.info("Launching Chrome browser");
        WebDriver chromeDriver = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

//        options.addArguments("--start-fullscreen");
//        options.addArguments("--disable-infobars");
//        options.addArguments("--disable-notifications");

        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    private WebDriver createEdgeDriver() {
        logger.info("Launching Edge browser");
        WebDriver edgeDriver = null;
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--ignore-certificate-errors"); // certificates
        options.addArguments("--allow-insecure-localhost");

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        edgeDriver = new EdgeDriver(options);
        edgeDriver.manage().window().maximize();
        return edgeDriver;
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                logger.info("Closing browser") ;
                driver.quit();
                logger.info("Browser closed successfully");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver = null;
            }
        } else {
            logger.info("Driver is already null");
        }
    }
}
