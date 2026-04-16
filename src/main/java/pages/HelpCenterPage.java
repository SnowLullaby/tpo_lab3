package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class HelpCenterPage extends BasePage {

    private final By searchField = By.xpath(
            "(//input[@type='search' or @name='s' or contains(@placeholder,'Найти') or contains(@placeholder,'Поиск')])[1]"
    );

    private final By platformCategoryLink = By.xpath(
            "(//a[contains(normalize-space(.),'О платформе PromoPult')])[1]"
    );

    private final By registrationArticleLink = By.xpath(
            "(//a[contains(normalize-space(.),'Регистрация в Системе')])[1]"
    );

    public HelpCenterPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public void open() {
        open("https://help.promopult.ru/");
    }

    public void openPlatformCategory() {
        click(platformCategoryLink);
        waitForDocumentReady();
        waitForUrlContains("o-platforme-promopult");
    }

    public void openRegistrationArticle() {
        search("Регистрация в Системе");
        click(registrationArticleLink);
        waitForDocumentReady();
        waitForUrlContains("registracziya-v-sisteme");
    }

    public void search(String query) {
        WebElement input = waitVisible(searchField);
        input.clear();
        input.sendKeys(query == null ? "" : query);
        input.sendKeys(Keys.ENTER);
        waitForDocumentReady();
    }

    public boolean looksLikeHelpCenterPage() {
        waitForDocumentReady();

        String url = driver.getCurrentUrl() == null ? "" : driver.getCurrentUrl().toLowerCase();
        String source = pageSource().toLowerCase();

        return url.contains("help.promopult.ru")
                || source.contains("справочный центр")
                || source.contains("найти в справочном центре")
                || source.contains("разделы справочного центра")
                || source.contains("популярные вопросы")
                || source.contains("база знаний");
    }

    public boolean isPlatformCategoryOpened() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("o-platforme-promopult")
                || textPresent("О платформе PromoPult");
    }

    public boolean isRegistrationArticleOpened() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("registracziya-v-sisteme")
                || textPresent("Регистрация в Системе");
    }

    public boolean hasSearchResultsForRegistration() {
        String source = pageSource().toLowerCase();
        return source.contains("регистрац")
                || source.contains("регистрация в системе");
    }

    public boolean hasEmptySearchState() {
        String source = pageSource().toLowerCase();
        return source.contains("¯\\_(ツ)_/¯")
                || source.contains("нет результатов")
                || source.contains("ничего не найдено")
                || source.contains("найдено 0")
                || source.contains("0 вариантов");
    }
}
