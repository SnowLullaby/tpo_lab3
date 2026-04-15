package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm extends BasePage {

    private final By modalRoot = By.xpath(
            "(//div[" +
                    ".//text()[contains(translate(., 'входрегистрация', 'ВХОДРЕГИСТРАЦИЯ'), 'ВХОД')]" +
                    " and .//text()[contains(translate(., 'входрегистрация', 'ВХОДРЕГИСТРАЦИЯ'), 'РЕГИСТРАЦИЯ')]" +
                    "])[last()]"
    );

    private final By loginTab = By.xpath(
            ".//*[self::a or self::button or self::div or self::span]" +
                    "[contains(translate(normalize-space(.), 'вход', 'ВХОД'), 'ВХОД')]"
    );

    private final By activeLoginTab = By.xpath(
            ".//*[self::a or self::button or self::div or self::span]" +
                    "[contains(translate(normalize-space(.), 'вход', 'ВХОД'), 'ВХОД')" +
                    " and (contains(@class,'active') or contains(@class,'current') or contains(@style,'color'))]"
    );

    private final By emailField = By.xpath(
            ".//input[" +
                    "contains(@type,'email')" +
                    " or contains(@name,'mail')" +
                    " or contains(@name,'login')" +
                    " or contains(translate(@placeholder,'MAILЛОГИНEMAIL', 'mailлогинemail'),'mail')" +
                    " or contains(translate(@placeholder,'MAILЛОГИНEMAIL', 'mailлогинemail'),'лог')" +
                    "]"
    );

    private final By passwordField = By.xpath(".//input[contains(@type,'password')]");
    private final By submitButton = By.xpath(
            ".//button[contains(normalize-space(.),'Войти в систему') " +
                    "or contains(normalize-space(.),'Войти') " +
                    "or contains(normalize-space(.),'Вход')] " +
                    "| .//input[@type='submit']"
    );

    private final By forgotPasswordLink = By.xpath(
            ".//*[self::a or self::button or self::span]" +
                    "[contains(translate(normalize-space(.), 'напомнитьпароль', 'НАПОМНИТЬПАРОЛЬ'), 'ПАРОЛ')]"
    );

    public LoginForm(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    private WebElement modal() {
        return waitVisible(modalRoot);
    }

    private WebElement modalElement(By innerLocator) {
        return modal().findElement(innerLocator);
    }

    public void ensureLoginTabSelected() {
        WebElement root = modal();

        if (!root.findElements(activeLoginTab).isEmpty()) {
            return;
        }

        for (WebElement element : root.findElements(loginTab)) {
            if (element.isDisplayed()) {
                element.click();
                return;
            }
        }

        throw new NoSuchElementException("Login tab not found inside auth modal");
    }

    public boolean hasLoginForm() {
        try {
            ensureLoginTabSelected();
            WebElement root = modal();

            return !root.findElements(emailField).isEmpty()
                    && !root.findElements(passwordField).isEmpty()
                    && !root.findElements(submitButton).isEmpty();
        } catch (Exception ex) {
            return false;
        }
    }

    public void fillEmail(String email) {
        WebElement element = modalElement(emailField);
        element.clear();
        element.sendKeys(email);
    }

    public void fillPassword(String password) {
        WebElement element = modalElement(passwordField);
        element.clear();
        element.sendKeys(password);
    }

    public void submit() {
        modalElement(submitButton).click();
    }

    public void login(String email, String password) {
        ensureLoginTabSelected();
        fillEmail(email);
        fillPassword(password);
        submit();
    }

    public void openPasswordRecovery() {
        modalElement(forgotPasswordLink).click();
    }

    public boolean stayedOnLoginForm() {
        try {
            return hasLoginForm();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean hasValidationOrError() {
        String source = pageSource().toLowerCase();
        return source.contains("ошиб")
                || source.contains("невер")
                || source.contains("заполн")
                || source.contains("invalid")
                || source.contains("incorrect")
                || stayedOnLoginForm();
    }
}
