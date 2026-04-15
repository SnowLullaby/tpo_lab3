package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;

    protected BasePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        this.driver = driver;
        this.wait = wait;
        this.js = js;
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean exists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected boolean visible(By locator) {
        try {
            return waitVisible(locator).isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }

    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void type(By locator, String value) {
        WebElement element = waitVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String pageSource() {
        try {
            String source = driver.getPageSource();
            return source == null ? "" : source;
        } catch (org.openqa.selenium.WebDriverException e) {
            return "";
        }
    }

    protected void open(String absoluteUrl) {
        driver.get(absoluteUrl);
    }

    protected void switchToNewestWindowIfOpened(Set<String> oldHandles) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(d -> d.getWindowHandles().size() > oldHandles.size());
            Set<String> current = driver.getWindowHandles();
            for (String handle : current) {
                if (!oldHandles.contains(handle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        } catch (TimeoutException ignored) {
        }
    }

    protected void clickFirstVisibleXpath(String... xpaths) {
        for (String xpath : xpaths) {
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    element.click();
                    return;
                }
            }
        }
        throw new NoSuchElementException("No visible element found for provided XPath list");
    }

    protected boolean waitForUrlContains(String... fragments) {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                    .until(d -> {
                        String url = d.getCurrentUrl();
                        if (url == null || url.isBlank() || "about:blank".equalsIgnoreCase(url)) {
                            return false;
                        }

                        String lower = url.toLowerCase();
                        for (String fragment : fragments) {
                            if (lower.contains(fragment.toLowerCase())) {
                                return true;
                            }
                        }
                        return false;
                    });
            return true;
        } catch (org.openqa.selenium.WebDriverException e) {
            return false;
        }
    }

    protected boolean waitForDocumentReady() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15))
                    .until(d -> {
                        Object state = ((org.openqa.selenium.JavascriptExecutor) d)
                                .executeScript("return document.readyState");
                        return "interactive".equals(state) || "complete".equals(state);
                    });
            return true;
        } catch (org.openqa.selenium.WebDriverException e) {
            return false;
        }
    }

    protected boolean textPresent(String text) {
        return pageSource().contains(text);
    }
}
