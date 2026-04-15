import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasesTest extends BaseUiTest {

    @Test
    @DisplayName("TS-06-01 Открытие страницы кейсов")
    void openCasesPageTest() {
        homePage.open();
        homePage.clickHeaderLink("Кейсы");

        assertTrue(casesPage.looksLikeCasesPage(), "Страница кейсов не открылась");
    }
}
