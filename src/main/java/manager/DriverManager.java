
package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

        return chromeDriver;
    }

    private WebDriver createEdgeDriver() {

        logger.info("Launching Edge browser");

        WebDriver edgeDriver = new EdgeDriver();
        edgeDriver.manage().window().maximize();
        return edgeDriver;
    }

    public void quitDriver() {

        System.out.println("QuitDriver method called");

        if (driver != null) {
            try {
                System.out.println("Closing browser: " + driver);

                driver.quit();

                System.out.println("Browser closed successfully");

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                driver = null;
            }

        } else {
            System.out.println("Driver is already null");
        }
    }
}
