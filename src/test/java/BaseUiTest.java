import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

public abstract class BaseUiTest {
    protected Utils utils;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    protected HomePage homePage;
    protected LoginForm loginForm;
    protected RegistrationForm registrationForm;
    protected PasswordRecoveryPage passwordRecoveryPage;
    protected FeaturesPage featuresPage;
    protected BlogPage blogPage;
    protected CasesPage casesPage;
    protected BonusProgramPage bonusProgramPage;
    protected EducationPage educationPage;
    protected HelpCenterPage helpCenterPage;

    @BeforeEach
    void baseSetUp() {
        utils = new Utils();
        utils.setupDriver();
        driver = utils.getDriver();
        wait = utils.getWaitTime();
        js = utils.getJsExecutor();

        homePage = new HomePage(driver, wait, js, TestData.BASE_URL);
        loginForm = new LoginForm(driver, wait, js);
        registrationForm = new RegistrationForm(driver, wait, js);
        passwordRecoveryPage = new PasswordRecoveryPage(driver, wait, js, TestData.BASE_URL);
        featuresPage = new FeaturesPage(driver, wait, js, TestData.BASE_URL);
        blogPage = new BlogPage(driver, wait, js);
        casesPage = new CasesPage(driver, wait, js);
        bonusProgramPage = new BonusProgramPage(driver, wait, js);
        educationPage = new EducationPage(driver, wait, js);
        helpCenterPage = new HelpCenterPage(driver, wait, js);
    }

    @AfterEach
    void baseTearDown() {
        if (utils != null) {
            utils.quitDriver();
        }
    }
}
