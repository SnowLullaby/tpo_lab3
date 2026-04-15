import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EducationTest extends BaseUiTest {

    @Test
    @DisplayName("TS-08-01 Открытие страницы обучения")
    void openEducationTest() {
        homePage.open();
        homePage.clickHeaderLink("Обучение");

        assertTrue(
                educationPage.looksLikeEducationPage(),
                "Страница обучения не открылась"
        );
    }

    @Test
    @DisplayName("TS-08-02 Переход по кнопке \"Все вебинары\"")
    void openAllWebinarsTest() {
        homePage.open();
        homePage.clickHeaderLink("Обучение");

        assertTrue(
                educationPage.looksLikeEducationPage(),
                "Страница обучения не открылась перед нажатием кнопки \"Все вебинары\""
        );

        assertTrue(
                educationPage.hasAllWebinarsButton(),
                "Кнопка \"Все вебинары\" не найдена"
        );

        assertEquals(
                "https://www.cybermarketing.ru/",
                educationPage.getAllWebinarsHref(),
                "Кнопка \"Все вебинары\" ведёт не на CyberMarketing"
        );

        assertTrue(
                educationPage.allWebinarsTargetIsBlank(),
                "Кнопка \"Все вебинары\" должна открываться в новой вкладке"
        );

        assertTrue(
                educationPage.clickAllWebinarsAndSwitchToNewTab(),
                "После нажатия на кнопку \"Все вебинары\" новая вкладка не открылась"
        );
    }
}
