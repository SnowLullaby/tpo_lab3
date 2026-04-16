import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCenterTest extends BaseUiTest {

    @Test
    @DisplayName("TS-09-01 Переход в справочный центр")
    void openHelpCenterTest() {
        homePage.open();
        homePage.clickHeaderLink("Помощь");

        assertTrue(helpCenterPage.looksLikeHelpCenterPage(), "Справочный центр не открылся");
    }

    @Test
    @DisplayName("TS-09-02 Положительный переход по категории help-центра")
    void openHelpCategoryTest() {
        homePage.open();
        homePage.clickHeaderLink("Помощь");
        helpCenterPage.openPlatformCategory();

        assertTrue(helpCenterPage.isPlatformCategoryOpened(), "Категория О платформе PromoPult не открылась");
    }

    @Test
    @DisplayName("TS-09-03 Положительное открытие статьи Регистрация в Системе")
    void openRegistrationArticleTest() {
        homePage.open();
        homePage.clickHeaderLink("Помощь");
        helpCenterPage.openRegistrationArticle();

        assertTrue(helpCenterPage.isRegistrationArticleOpened(), "Статья Регистрация в Системе не открылась");
    }

    @Test
    @DisplayName("TS-09-04 Положительный поиск инструкции в help-центре")
    void positiveHelpSearchTest() {
        homePage.open();
        homePage.clickHeaderLink("Помощь");
        helpCenterPage.search(TestData.HELP_POSITIVE_QUERY);

        assertTrue(helpCenterPage.hasSearchResultsForRegistration(), "Положительный поиск по help-центру не дал релевантных результатов");
    }

    @Test
    @DisplayName("TS-09-05 Негативный поиск инструкции по пустому запросу")
    void emptyHelpSearchTest() {
        homePage.open();
        homePage.clickHeaderLink("Помощь");
        helpCenterPage.search("");

        assertTrue(helpCenterPage.hasEmptySearchState(), "При пустом поиске не должно быть релевантных результатов");
    }

    @Test
    @DisplayName("TS-09-06 Негативный поиск инструкции по нерелевантному запросу")
    void irrelevantHelpSearchTest() {
        homePage.open();
        homePage.clickHeaderLink("Помощь");
        helpCenterPage.search(TestData.HELP_NEGATIVE_QUERY);

        assertTrue(helpCenterPage.hasEmptySearchState(), "По нерелевантному запросу не должно быть результатов");
    }
}
