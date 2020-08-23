package Affise.PageObject.Run;

import Affise.PageObject.AuthorizationPage;
import Affise.PageObject.MainPage;
import Affise.PageObject.PasswordRecoveryPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCasesSelenide {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = CHROME;
//        Configuration.browser = "Selenoid.Provider.SelenoidWebDriverProvider";
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test
    public void logInUnderUser() {
        AuthorizationPage.logIn("ivan@gmail.com", "vlad12-8");
    }

    @Test
    public void passwordRecovering() {
        // 1 option with not static methods in class
        AuthorizationPage authorizationPage = AuthorizationPage.clickPasswordRecovery()
                        .typeEmail("adminwrite@gmail.com")
                        .clickRestoreButton();

        // 2 option with static methods in class
        AuthorizationPage.clickPasswordRecovery();
        PasswordRecoveryPage.typeEmail("adminwrite@gmail.com");
        PasswordRecoveryPage.clickRestoreButton();
    }

    @Test
    public void logIn() {
        // Generate Data
        String email = "ivan@gmail.com";
        String password = "vlad12-8";

        // 1 option with static methods in class
        AuthorizationPage.open();
        MainPage mainPage = AuthorizationPage.authorizeUser(email, password);

        // 2 option with not static methods in class
        MainPage authorizationPage = new AuthorizationPage().open()
                .authorizeUser(email, password);

        // Assertions
        SelenideElement element = $(By.xpath("//*[@id='root']/div[3]//div[4]//span"));
        element.shouldBe(Condition.visible);
        assertEquals(element.getText(), email);
    }

    @AfterAll
    public static void closeDriver(){
        WebDriverRunner.closeWebDriver();
    }

}
