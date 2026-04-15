package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class HomePage extends BasePage {
    private final String baseUrl;

    public HomePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String baseUrl) {
        super(driver, wait, js);
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    public void open() {
        open(baseUrl);
    }

    public void clickLogin() {
        clickFirstVisibleXpath(
                "//a[contains(normalize-space(.), 'Вход')]",
                "//button[contains(normalize-space(.), 'Вход')]"
        );
    }

    public void clickRegister() {
        clickFirstVisibleXpath(
                "//a[contains(normalize-space(.), 'Регистрация')]",
                "//button[contains(normalize-space(.), 'Регистрация')]"
        );
    }

    public void clickHeaderLink(String linkText) {
        Set<String> handles = driver.getWindowHandles();

        clickFirstVisibleXpath(
                "//header//a[normalize-space(.)='" + linkText + "']",
                "//header//button[normalize-space(.)='" + linkText + "']",
                "//*[contains(@class,'header') or contains(@class,'top') or contains(@class,'menu')]//a[normalize-space(.)='" + linkText + "']",
                "//*[contains(@class,'header') or contains(@class,'top') or contains(@class,'menu')]//button[normalize-space(.)='" + linkText + "']"
        );

        switchToNewestWindowIfOpened(handles);
    }

    public boolean isTopMenuVisible() {
        return visible(By.xpath("//a[contains(normalize-space(.), 'Возможности')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Блог')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Кейсы')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Бонусная программа')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Обучение')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Помощь')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Вход')]"))
                && visible(By.xpath("//a[contains(normalize-space(.), 'Регистрация')]"));
    }
}
