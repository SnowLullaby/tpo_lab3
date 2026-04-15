import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.assertTrue;

@EnabledIfSystemProperty(named = "runProtectedFlows", matches = "true")
public class RegistrationTest extends BaseUiTest {

    @Test
    @DisplayName("TS-02-01 Открытие формы регистрации")
    void openRegistrationFormTest() {
        homePage.open();
        homePage.clickRegister();

        assertTrue(registrationForm.hasRequiredFields(),
                "Форма регистрации не открылась");
    }

    @Test
    @DisplayName("TS-02-02 Проверка обязательных полей регистрации")
    void registrationRequiredFieldsTest() {
        homePage.open();
        homePage.clickRegister();

        assertTrue(registrationForm.hasRequiredFields(),
                "Не все обязательные поля регистрации найдены");
    }

    @Test
    @DisplayName("TS-02-03 Невалидная регистрация с некорректными данными")
    void invalidRegistrationTest() {
        homePage.open();
        homePage.clickRegister();

        assertTrue(registrationForm.hasRequiredFields(),
                "Форма регистрации не открылась перед заполнением");

        registrationForm.fillInvalidForm(
                TestData.AUTH_EMAIL,
                TestData.SHORT_PASSWORD,
                TestData.PHONE,
                TestData.NAME
        );
        registrationForm.submit();

        assertTrue(registrationForm.hasValidationState(),
                "Не найдены признаки ошибки/валидации при некорректной регистрации");
    }
}
