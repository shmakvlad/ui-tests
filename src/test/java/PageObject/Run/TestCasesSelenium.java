package PageObject.Run;

import PageObject.AuthorizationPage;
import PageObject.LoginPageFactory;
import PageObject.MainPage;
import PageObject.PasswordRecoveryPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Comment if will be use @BeforeEach, @AfterEach
public class TestCasesSelenium {

    private WebDriver driver;

//    Another option is to initialize page classes before tests and then work with class variables.
//    private MainPage mainPage;
//    private AuthorizationPage authorizationPage;
//    private PasswordRecoveryPage passwordRecoveryPage;

    @BeforeAll
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void openAuthorizationPage(){
        AuthorizationPage authorizationPage = new AuthorizationPage(driver).open();
        assertEquals("http://offers.staging.affise.com/", driver.getCurrentUrl());
    }

    @Test
    public void passwordRecovering(){
        PasswordRecoveryPage passwordRecoveryPage = new AuthorizationPage(driver).open().clickPasswordRecovery();
        passwordRecoveryPage.typeEmail("adminwrite@gmail.com").clickRestoreButton();
    }

    @Test
    public void logIn() {
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);

        // 1
        authorizationPage.open();
        MainPage mainPage = authorizationPage.authorizeUser("ivan@gmail.com","vlad12-8");

        // 2
        authorizationPage.open().authorizeUser("ivan@gmail.com","vlad12-8");
    }

    @Test
    public void loginPageFactory() throws InterruptedException {
        LoginPageFactory loginPageFactory = PageFactory.initElements(driver, LoginPageFactory.class);
        loginPageFactory.open().open();
        loginPageFactory.authorizeUser("ivan@gmail.com","vlad12-8");
    }

    @AfterAll
    public void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

}
