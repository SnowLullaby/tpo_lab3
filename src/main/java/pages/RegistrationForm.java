package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationForm extends BasePage {

    private final By modalRoot = By.xpath(
            "(//div[" +
                    ".//text()[contains(translate(., 'входрегистрация', 'ВХОДРЕГИСТРАЦИЯ'), 'ВХОД')]" +
                    " and .//text()[contains(translate(., 'входрегистрация', 'ВХОДРЕГИСТРАЦИЯ'), 'РЕГИСТРАЦИЯ')]" +
                    "])[last()]"
    );

    private final By registrationTab = By.xpath(
            ".//*[self::a or self::button or self::div or self::span]" +
                    "[contains(translate(normalize-space(.), 'регистрация', 'РЕГИСТРАЦИЯ'), 'РЕГИСТРАЦИЯ')]"
    );

    private final By activeRegistrationTab = By.xpath(
            ".//*[self::a or self::button or self::div or self::span]" +
                    "[contains(translate(normalize-space(.), 'регистрация', 'РЕГИСТРАЦИЯ'), 'РЕГИСТРАЦИЯ')" +
                    " and (contains(@class,'active') or contains(@class,'current') or contains(@style,'color'))]"
    );

    private final By emailField = By.xpath(
            ".//input[" +
                    "contains(@type,'email')" +
                    " or contains(@name,'mail')" +
                    " or contains(translate(@placeholder,'MAILEMAILПОЧТ', 'mailemailпочт'),'mail')" +
                    " or contains(translate(@placeholder,'MAILEMAILПОЧТ', 'mailemailпочт'),'почт')" +
                    "]"
    );

    private final By passwordField = By.xpath(".//input[contains(@type,'password')]");
    private final By phoneField = By.xpath(
            ".//input[" +
                    "contains(@name,'phone')" +
                    " or contains(translate(@placeholder,'ТЕЛЕФОНPHONE', 'телефонphone'),'тел')" +
                    " or contains(translate(@placeholder,'ТЕЛЕФОНPHONE', 'телефонphone'),'phone')" +
                    "]"
    );

    private final By nameField = By.xpath(
            ".//input[" +
                    "contains(@name,'name')" +
                    " or contains(translate(@placeholder,'ИМЯNAME', 'имяname'),'имя')" +
                    " or contains(translate(@placeholder,'ИМЯNAME', 'имяname'),'name')" +
                    "]"
    );

    private final By agreementCheckbox = By.xpath(".//input[@type='checkbox']");
    private final By submitButton = By.xpath(
            ".//button[contains(normalize-space(.),'Зарегистр')" +
                    " or contains(normalize-space(.),'Регистрация')]" +
                    " | .//input[@type='submit']"
    );

    private final By captchaBlock = By.xpath(
            ".//*[contains(.,'Я не робот') or contains(@title,'reCAPTCHA') or contains(@class,'captcha')]"
    );

    public RegistrationForm(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
        super(driver, wait, js);
    }

    private WebElement modal() {
        return waitVisible(modalRoot);
    }

    private WebElement modalElement(By innerLocator) {
        return modal().findElement(innerLocator);
    }

    public void ensureRegistrationTabSelected() {
        WebElement root = modal();

        if (!root.findElements(activeRegistrationTab).isEmpty()) {
            return;
        }

        for (WebElement element : root.findElements(registrationTab)) {
            if (element.isDisplayed()) {
                element.click();
                return;
            }
        }

        throw new NoSuchElementException("Registration tab not found inside auth modal");
    }

    public boolean hasRequiredFields() {
        try {
            ensureRegistrationTabSelected();
            WebElement root = modal();

            return !root.findElements(emailField).isEmpty()
                    && !root.findElements(passwordField).isEmpty()
                    && !root.findElements(phoneField).isEmpty()
                    && !root.findElements(nameField).isEmpty()
                    && !root.findElements(agreementCheckbox).isEmpty()
                    && !root.findElements(submitButton).isEmpty()
                    && !root.findElements(captchaBlock).isEmpty();
        } catch (Exception ex) {
            return false;
        }
    }

    public void fillInvalidForm(String email, String password, String phone, String name) {
        ensureRegistrationTabSelected();

        WebElement emailInput = modalElement(emailField);
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement passwordInput = modalElement(passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement phoneInput = modalElement(phoneField);
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        WebElement nameInput = modalElement(nameField);
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void submit() {
        modalElement(submitButton).click();
    }

    public boolean hasValidationState() {
        String source = pageSource().toLowerCase();
        return source.contains("ошиб")
                || source.contains("заполн")
                || source.contains("миним")
                || source.contains("captcha")
                || source.contains("робот")
                || hasRequiredFields();
    }
}
