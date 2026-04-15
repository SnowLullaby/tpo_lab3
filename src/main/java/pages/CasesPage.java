package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class CasesPage extends BasePage {
    public CasesPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public boolean looksLikeCasesPage() {
        waitForUrlContains("seo_top");
        waitForDocumentReady();

        String url = driver.getCurrentUrl() == null ? "" : driver.getCurrentUrl().toLowerCase();
        String source = pageSource().toLowerCase();

        return url.contains("seo_top")
                || source.contains("кейсы")
                || source.contains("seo")
                || source.contains("продвижен");
    }
}
