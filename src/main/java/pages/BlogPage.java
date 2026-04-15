package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlogPage extends BasePage {
    public BlogPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    public void open() {
        open("https://blog.promopult.ru/");
    }

    public void openNewsAndCases() {
        open("https://blog.promopult.ru/category/promopult");
    }

    public void search(String query) {
        open("https://blog.promopult.ru/?s=" + query);
    }

    public boolean hasNewsAndCasesCategory() {
        return driver.getCurrentUrl().contains("category/promopult") || textPresent("Новости и кейсы");
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
