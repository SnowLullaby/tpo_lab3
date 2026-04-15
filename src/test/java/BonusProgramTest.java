import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusProgramTest extends BaseUiTest {

    @Test
    @DisplayName("TS-07-01 Открытие раздела бонусной программы")
    void openBonusProgramTest() {
        homePage.open();
        homePage.clickHeaderLink("Бонусная программа");

        assertTrue(
                bonusProgramPage.looksLikeBonusProgramPage(),
                "Не открылся раздел бонусной программы"
        );
    }
}
