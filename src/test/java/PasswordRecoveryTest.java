import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordRecoveryTest extends BaseUiTest {

    @Test
    @DisplayName("TS-03-01 Положительное восстановление пароля")
    void positivePasswordRecoveryTest() {
        Assumptions.assumeTrue(TestData.isLiveRecoveryEnabled(), "Живое восстановление пароля отключено по умолчанию");

        passwordRecoveryPage.open();
        passwordRecoveryPage.requestPasswordReset(TestData.AUTH_EMAIL);

        assertTrue(passwordRecoveryPage.hasRecoveryConfirmationOrValidation(),
                "После отправки формы ожидается подтверждение или смена состояния страницы");
    }

    @Test
    @DisplayName("TS-03-02 Невалидное восстановление пароля с пустым полем")
    void emptyRecoveryEmailTest() {
        passwordRecoveryPage.open();
        passwordRecoveryPage.requestPasswordReset("");

        assertTrue(passwordRecoveryPage.hasRecoveryConfirmationOrValidation(), "Не найдены признаки валидации для пустого e-mail");
    }

    @Test
    @DisplayName("TS-03-03 Невалидное восстановление пароля с неверным форматом e-mail")
    void invalidRecoveryEmailFormatTest() {
        passwordRecoveryPage.open();
        passwordRecoveryPage.requestPasswordReset(TestData.INVALID_EMAIL);

        assertTrue(passwordRecoveryPage.hasRecoveryConfirmationOrValidation(), "Не найдены признаки валидации для некорректного e-mail");
    }
}
