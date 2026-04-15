import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfSystemProperty(named = "runProtectedFlows", matches = "true")
public class AuthorizationTest extends BaseUiTest {

    @Test
    @DisplayName("TS-01-01 Открытие формы входа")
    void openLoginFormTest() {
        homePage.open();
        homePage.clickLogin();

        assertTrue(loginForm.hasLoginForm(),
                "Форма входа не открылась или не содержит ожидаемых полей");
    }

    @Test
    @DisplayName("TS-01-02 Успешная авторизация")
    void successfulAuthorizationTest() {
        Assumptions.assumeTrue(
                TestData.isLiveAuthEnabled(),
                "Положительный тест авторизации отключен"
        );

        homePage.open();
        homePage.clickLogin();

        assertTrue(loginForm.hasLoginForm(),
                "Форма входа не открылась перед попыткой авторизации");

        loginForm.login(TestData.AUTH_EMAIL, TestData.AUTH_PASSWORD);

        assertFalse(loginForm.stayedOnLoginForm(),
                "После успешного входа пользователь не должен оставаться на форме авторизации");
    }

    @Test
    @DisplayName("TS-01-03 Невалидный вход с пустыми полями")
    void invalidAuthorizationWithEmptyFieldsTest() {
        homePage.open();
        homePage.clickLogin();

        assertTrue(loginForm.hasLoginForm(),
                "Форма входа не открылась перед проверкой пустых полей");

        loginForm.login("", "");

        assertTrue(loginForm.hasValidationOrError(),
                "Не найдены признаки ошибки/валидации для пустой формы входа");
    }

    @Test
    @DisplayName("TS-01-04 Невалидный вход с неверным паролем")
    void invalidAuthorizationWithWrongPasswordTest() {
        homePage.open();
        homePage.clickLogin();

        assertTrue(loginForm.hasLoginForm(),
                "Форма входа не открылась перед проверкой неверного пароля");

        loginForm.login(TestData.AUTH_EMAIL, TestData.WRONG_PASSWORD);

        assertTrue(loginForm.hasValidationOrError(),
                "Не найдены признаки ошибки при неверном пароле");
    }
}
