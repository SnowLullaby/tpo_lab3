package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class BonusProgramPage extends BasePage {
    public BonusProgramPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public boolean looksLikeBonusProgramPage() {
        waitForUrlContains("partners");
        waitForDocumentReady();

        String url = driver.getCurrentUrl() == null ? "" : driver.getCurrentUrl().toLowerCase();
        String source = pageSource().toLowerCase();

        return url.contains("partners")
                || source.contains("бонусная программа")
                || source.contains("калькулятор бонусной программы")
                || source.contains("рассчитайте бонусы")
                || source.contains("бонус");
    }
}
