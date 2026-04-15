import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeaturesTest extends BaseUiTest {

    @Test
    @DisplayName("TS-04-01 Переход к инструменту Поисковое продвижение")
    void openSeoFeatureTest() {
        homePage.open();
        homePage.clickHeaderLink("Возможности");

        assertTrue(featuresPage.isFeaturesPageOpened() && featuresPage.hasAdditionalMenuBelow(),
                "Страница Возможности не открылась или не содержит отдельного нижнего меню инструментов");

        featuresPage.openSeo();
        assertTrue(featuresPage.isOnSeoPage(), "Не удалось открыть страницу Поисковое продвижение (SEO)");
    }

    @Test
    @DisplayName("TS-04-02 Переход к инструменту Контекстная реклама")
    void openPpcFeatureTest() {
        homePage.open();
        homePage.clickHeaderLink("Возможности");

        assertTrue(featuresPage.isFeaturesPageOpened() && featuresPage.hasAdditionalMenuBelow(),
                "Страница Возможности не открылась или не содержит отдельного нижнего меню инструментов");

        featuresPage.openPpc();
        assertTrue(featuresPage.isOnPpcPage(), "Не удалось открыть страницу Контекстная реклама");
    }
}
