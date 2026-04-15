package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.Set;

public class EducationPage extends BasePage {
    private final By allWebinarsButton = By.xpath(
            "//a[contains(normalize-space(.), 'Все вебинары')] | //button[contains(normalize-space(.), 'Все вебинары')]"
    );

    public EducationPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public boolean looksLikeEducationPage() {
        String url = Objects.requireNonNull(driver.getCurrentUrl()).toLowerCase();
        String source = pageSource().toLowerCase();

        return url.contains("education")
                || url.contains("cybermarketing")
                || source.contains("обучен")
                || source.contains("вебинар");
    }

    public boolean hasAllWebinarsButton() {
        return visible(allWebinarsButton);
    }

    public String getAllWebinarsHref() {
        WebElement element = waitVisible(allWebinarsButton);
        return element.getAttribute("href");
    }

    public boolean allWebinarsTargetIsBlank() {
        WebElement element = waitVisible(allWebinarsButton);
        String target = element.getAttribute("target");
        return "_blank".equalsIgnoreCase(target);
    }

    public boolean clickAllWebinarsAndSwitchToNewTab() {
        Set<String> oldHandles = driver.getWindowHandles();

        clickFirstVisibleXpath(
                "//a[contains(normalize-space(.), 'Все вебинары')]",
                "//button[contains(normalize-space(.), 'Все вебинары')]"
        );

        switchToNewestWindowIfOpened(oldHandles);

        return driver.getWindowHandles().size() > oldHandles.size();
    }
}
