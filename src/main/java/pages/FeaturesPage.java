package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class FeaturesPage extends BasePage {
    private final String baseUrl;

    public FeaturesPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String baseUrl) {
        super(driver, wait, js);
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    public void open() {
        open(baseUrl + "technology");
    }

    public boolean isFeaturesPageOpened() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("technology") || textPresent("Возможности системы");
    }

    public boolean hasAdditionalMenuBelow() {
        return textPresent("Возможности системы")
                && textPresent("Поисковое продвижение")
                && textPresent("Контекстная реклама");
    }

    public void openSeo() {
        clickFirstVisibleXpath(
                "//a[contains(normalize-space(.),'Поисковое продвижение')]",
                "//a[contains(@href,'seo')]"
        );
    }

    public void openPpc() {
        clickFirstVisibleXpath(
                "//a[contains(normalize-space(.),'Контекстная реклама')]",
                "//a[contains(@href,'context')]",
                "//a[contains(@href,'ppc')]"
        );
    }

    public boolean isOnSeoPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).toLowerCase().contains("seo") || textPresent("Поисковое продвижение");
    }

    public boolean isOnPpcPage() {
        String url = Objects.requireNonNull(driver.getCurrentUrl()).toLowerCase();
        return url.contains("context") || url.contains("ppc") || textPresent("Контекстная реклама");
    }
}
