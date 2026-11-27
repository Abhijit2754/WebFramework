
package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public DriverManager() {
    }

    public WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            String driverName = System.getProperty(
                    "driverName", "CHROME"
            ).trim().toUpperCase(Locale.ROOT);

            logger.info(
                    "Creating {} driver on Thread: {}",
                    driverName,
                    Thread.currentThread().getId()
            );
            driver = createDriver(driverName);
            DRIVER.set(driver);
        }

        return driver;
    }

    // Get current thread's driver without creating a new browser
    public WebDriver getCurrentDriver() {
        return DRIVER.get();
    }

    // Create browser driver
    private WebDriver createDriver(String driverName) {

        WebDriver driver;

        switch (driverName) {

            case "CHROME":
                driver = createChromeDriver();
                break;

            case "EDGE":
                driver = createEdgeDriver();
                break;

            case "FIREFOX":
                driver = createFirefoxDriver();
                break;

            case "SAFARI":
                driver = createSafariDriver();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + driverName
                );
        }

        return driver;
    }

    // Chrome
    private WebDriver createChromeDriver() {

        logger.info("Launching Chrome browser");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");

        return createBrowser(options, "CHROME");
    }

    // Edge
    private WebDriver createEdgeDriver() {

        logger.info("Launching Edge browser");

        EdgeOptions options = new EdgeOptions();

        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");

        options.setExperimentalOption(
                "excludeSwitches",
                new String[]{"enable-automation"}
        );

        return createBrowser(options, "EDGE");
    }

    // Firefox
    private WebDriver createFirefoxDriver() {

        logger.info("Launching Firefox browser");

        FirefoxOptions options = new FirefoxOptions();

        return createBrowser(options, "FIREFOX");
    }

    // Safari
    private WebDriver createSafariDriver() {

        logger.info("Launching Safari browser");

        SafariOptions options = new SafariOptions();

        return createBrowser(options, "SAFARI");
    }

    // Local browser or Selenium Grid
    private WebDriver createBrowser(
            org.openqa.selenium.MutableCapabilities options,
            String browserName) {

        WebDriver driver;

        String gridUrl = System.getProperty("gridUrl");

        try {

            if (gridUrl != null && !gridUrl.trim().isEmpty()) {

                logger.info("Connecting to Selenium Grid: {}", gridUrl);

                driver = new RemoteWebDriver(
                        new URL(gridUrl),
                        options
                );

            } else {

                logger.info("Starting local {} browser", browserName);

                switch (browserName) {

                    case "CHROME":
                        driver = new ChromeDriver((ChromeOptions) options);
                        break;

                    case "EDGE":
                        driver = new EdgeDriver((EdgeOptions) options);
                        break;

                    case "FIREFOX":
                        driver = new FirefoxDriver((FirefoxOptions) options);
                        break;

                    case "SAFARI":
                        driver = new SafariDriver((SafariOptions) options);
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Unsupported browser: " + browserName
                        );
                }
            }

            driver.manage().window().maximize();

            return driver;

        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "Invalid Selenium Grid URL: " + gridUrl, e
            );
        }
    }

    public void quitDriver() {

        WebDriver driver = DRIVER.get();

        try {

            if (driver != null) {

                logger.info(
                        "Closing browser on Thread: {}",
                        Thread.currentThread().getId()
                );

                driver.quit();

                logger.info("Browser closed successfully");
            }

        } catch (Exception e) {

            logger.error("Error while closing browser", e);

        } finally {

            DRIVER.remove();
        }
    }
}
