package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage extends BasePage {
    private final String baseUrl;

    private final By recoveryEmailField = By.xpath("(//input[contains(@type,'email') or contains(@name,'mail')])[last()]");

    public PasswordRecoveryPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String baseUrl) {
        super(driver, wait, js);
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    public void open() {
        open(baseUrl + "user.html?lostpass");
    }

    public boolean isOpened() {
        String source = pageSource();
        return exists(recoveryEmailField)
                && source.contains("Восстановление пароля")
                && source.contains("Адрес электронной почты");
    }

    public void requestPasswordReset(String email) {
        type(recoveryEmailField, email);
        clickFirstVisibleXpath(
                "//button[contains(normalize-space(.),'Отправ') or contains(normalize-space(.),'Выслать')]",
                "//input[@type='submit']"
        );
    }

    public boolean hasRecoveryConfirmationOrValidation() {
        String source = pageSource().toLowerCase();
        return source.contains("отправ")
                || source.contains("восстанов")
                || source.contains("ошиб")
                || source.contains("заполн")
                || source.contains("электронной почты");
    }
}
