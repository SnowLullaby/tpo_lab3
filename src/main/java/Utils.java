import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.PageLoadStrategy;

import java.time.Duration;

public class Utils {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public void setupDriver() {
        String browser = System.getProperty("browser", "chrome").trim().toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        switch (browser) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                if (headless) {
                    options.addArguments("-headless");
                }
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                if (headless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1440,1080");
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        if ("firefox".equals(browser)) {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        } else {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        }

        driver.manage().window().setSize(new Dimension(1440, 1080));

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWaitTime() {
        return wait;
    }

    public JavascriptExecutor getJsExecutor() {
        return js;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
