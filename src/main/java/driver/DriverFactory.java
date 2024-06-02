package driver;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public WebDriver createDriver() {
        String browserName = System.getProperty("browser");
        browserName = "chrome";
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
        return driver;
    }

    public WebDriver createDriver(String browserName) {
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
        return driver;
    }

    private WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome" -> driver = initChromeDriver();
            case "firefox" -> driver = initFirefoxDriver();
            case "edge" -> driver = initEdgeDriver();
            default -> {
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome browser default...");
                driver = initChromeDriver();
            }
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        if (GlobalConstants.HEADLESS) {
            options.addArguments("--headless=new");
            options.addArguments("window-size=1800,900");
        } else {
            options.addArguments("--start-maximized");
        }
        options.addArguments("--disable-infobars", "--disable-new-bookmark-apps");

        driver = new ChromeDriver(options);

        return driver;
    }

    private WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");

        EdgeOptions options = new EdgeOptions();

        if (GlobalConstants.HEADLESS) {
            options.addArguments("--headless=new");
            options.addArguments("window-size=1800,900");
        } else {
            options.addArguments("--start-maximized");
        }
        options.addArguments("disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new EdgeDriver(options);
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("Launching Firefox browser...");

        FirefoxOptions options = new FirefoxOptions();

        if (GlobalConstants.HEADLESS) {
            options.addArguments("--headless=new");
            options.addArguments("window-size=1800,900");
        } else {
            options.addArguments("--start-maximized");
        }
        driver = new FirefoxDriver(options);
        return driver;
    }

    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }


}