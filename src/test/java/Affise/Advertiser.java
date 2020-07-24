package Affise;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.Assert.assertEquals;

public class Advertiser {

    private final Faker generate = new Faker();

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "Selenoid.Provider.SelenoidWebDriverProvider";
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @BeforeEach
    public void login(){
        step("Log in", () -> {
            open("/user/login");
            $("#email").setValue("ivan@gmail.com");
            $("#password").setValue("vlad12-8");
            $(byXpath("//input[@id='sign']")).click();
            $("button.btn.btn-success.btn-block").click();
        });
    }

    @AfterEach
    public void close(){
        step("Log out", () -> {
            WebDriverRunner.closeWebDriver();
        });
    }

    @Test
    @Issue("AF-25")
    @DisplayName("User can create advertiser")
    public void createAdvertiser() {
        step("Open the advertiser creation page", () -> {
            open("/advertiser/add");
        });
        step("Fill fields with correct data", () -> {
            $("#EditPartner_title").setValue(generate.funnyName().name());
        });
        step("Click on button Save", () -> {
            $(byValue("Save")).click();
        });
        step(" <--- Validation ---> " +
                "1. Advertiser successfully created. " +
                "2. Advertiser edit page is open.", () -> {
            SelenideElement text = $(byXpath("//div[contains(@class,'panel-heading')][1]"));
            assertEquals("Edit advertiser.", text.getText());
        });
    }

}
