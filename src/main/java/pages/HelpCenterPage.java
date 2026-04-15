package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpCenterPage extends BasePage {
    public HelpCenterPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public void open() {
        open("https://help.promopult.ru/");
    }

    public void openPlatformCategory() {
        open("https://help.promopult.ru/category/o-platforme-promopult/");
    }

    public void openRegistrationArticle() {
        open("https://help.promopult.ru/registracziya-v-sisteme/");
    }

    public void search(String query) {
        open("https://help.promopult.ru/?s=" + query);
    }

    public boolean looksLikeHelpCenterPage() {
        waitForUrlContains("help.promopult.ru", "help");
        waitForDocumentReady();

        String url = driver.getCurrentUrl() == null ? "" : driver.getCurrentUrl().toLowerCase();
        String source = pageSource().toLowerCase();

        return url.contains("help.promopult.ru")
                || source.contains("справочный центр")
                || source.contains("вопрос-ответ")
                || source.contains("база знаний")
                || source.contains("help");
    }

    public boolean isPlatformCategoryOpened() {
        waitForUrlContains("o-platforme-promopult");
        waitForDocumentReady();

        return driver.getCurrentUrl().contains("o-platforme-promopult")
                || textPresent("О платформе PromoPult");
    }

    public boolean isRegistrationArticleOpened() {
        waitForUrlContains("registracziya-v-sisteme");
        waitForDocumentReady();

        return driver.getCurrentUrl().contains("registracziya-v-sisteme")
                || textPresent("Регистрация в Системе");
    }

    public boolean hasSearchResultsForRegistration() {
        waitForDocumentReady();

        String source = pageSource().toLowerCase();
        return source.contains("регистрац")
                || source.contains("регистрация в системе");
    }

    public boolean hasEmptySearchState() {
        waitForDocumentReady();

        String source = pageSource().toLowerCase();
        return source.contains("результаты поиска")
                || source.contains("нет результатов")
                || source.contains("ничего не найдено")
                || source.contains("найдено 0");
    }
}
