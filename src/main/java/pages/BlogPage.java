package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class BlogPage extends BasePage {

    private final By newsAndCasesLink = By.xpath(
            "(//*[self::a or self::button][contains(normalize-space(.),'Новости и кейсы')])[1]"
    );

    private final By searchOpenButton = By.xpath(
            "(//button[contains(@class,'search') or contains(@class,'icon-search') or contains(@aria-label,'Поиск') or contains(@title,'Поиск')]"
                    + " | //a[contains(@class,'search') or contains(@class,'icon-search') or contains(@aria-label,'Поиск') or contains(@title,'Поиск')])[1]"
    );

    private final By searchField = By.xpath(
            "(//input[@type='search' or @name='s' or contains(@placeholder,'Поиск') or contains(@placeholder,'Найти')])[1]"
    );

    public BlogPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public void open() {
        open("https://blog.promopult.ru/");
    }

    public void openNewsAndCases() {
        click(newsAndCasesLink);
        waitForDocumentReady();
        waitForUrlContains("category", "promopult");
    }

    public void search(String query) {
        WebElement openButton = waitClickable(searchOpenButton);
        try {
            openButton.click();
        } catch (Exception ex) {
            js.executeScript("arguments[0].click();", openButton);
        }

        WebElement input = waitVisible(searchField);
        input.clear();
        input.sendKeys(query == null ? "" : query);
        input.sendKeys(Keys.ENTER);

        waitForDocumentReady();
    }

    public boolean hasNewsAndCasesCategory() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("category/promopult")
                || textPresent("Новости и кейсы");
    }

    public boolean hasSearchResultsForAdvertising() {
        String source = pageSource().toLowerCase();
        return source.contains("реклам") || source.contains("контекст") || source.contains("маркетинг");
    }

    public boolean hasEmptySearchState() {
        String source = pageSource().toLowerCase();
        return source.contains("¯\\_(ツ)_/¯")
                || source.contains("ничего не найдено")
                || source.contains("нет результатов")
                || source.contains("результаты поиска") && !source.contains("реклам");
    }
}
