import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlogTest extends BaseUiTest {

    @Test
    @DisplayName("TS-05-01 Переход в блог и открытие рубрики Новости и кейсы")
    void openBlogCategoryTest() {
        homePage.open();
        homePage.clickHeaderLink("Блог");
        blogPage.openNewsAndCases();

        assertTrue(blogPage.hasNewsAndCasesCategory(), "Не удалось открыть рубрику Новости и кейсы");
    }

    @Test
    @DisplayName("TS-05-02 Положительный поиск статей в блоге")
    void positiveBlogSearchTest() {
        homePage.open();
        homePage.clickHeaderLink("Блог");
        blogPage.search(TestData.BLOG_POSITIVE_QUERY);

        assertTrue(blogPage.hasSearchResultsForAdvertising(), "По положительному запросу не найдены релевантные материалы");
    }

    @Test
    @DisplayName("TS-05-03 Негативный поиск статей в блоге по пустому запросу")
    void emptyBlogSearchTest() {
        homePage.open();
        homePage.clickHeaderLink("Блог");
        blogPage.search("");

        assertTrue(blogPage.hasEmptySearchState() || !blogPage.hasSearchResultsForAdvertising(),
                "При пустом поиске не должно быть ложноположительных результатов");
    }

    @Test
    @DisplayName("TS-05-04 Негативный поиск статей в блоге по нерелевантному запросу")
    void irrelevantBlogSearchTest() {
        homePage.open();
        homePage.clickHeaderLink("Блог");
        blogPage.search(TestData.BLOG_NEGATIVE_QUERY);

        assertTrue(blogPage.hasEmptySearchState() || !blogPage.hasSearchResultsForAdvertising(),
                "По нерелевантному запросу не должно быть релевантных результатов");
    }
}
