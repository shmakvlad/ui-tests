package Selenoid;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSelenoid { // Clean Selenoid

    private RemoteWebDriver driver;
    private Faker generate = new Faker();

    @BeforeAll
    public static void beforeAll() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "enableVNC",  true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("name", "Create Affiliate");
        capabilities.setCapability("timeZone", "Europe/Moscow");
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");

        Configuration.browserCapabilities = capabilities;
        Configuration.remote= "http://localhost:4444/wd/hub";
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test()
    public void createAffiliate1() {
        open("/partners/new");
        $("#EditPartner_email").setValue(generate.internet().emailAddress());
        $("#EditPartner_password").setValue(generate.internet().password(6,12));
        $("#EditPartner_manager_id").selectOption(3);
        $("#EditPartner_country").selectOptionByValue("RU");
        $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName()));
        $("#EditPartner_status").selectOptionByValue("1");
        $("#EditPartner_submit").click();
        $("#EditPartner_submit").shouldHave(text("Save"));
        screenshot("Create Affiliate");
    }

    @Test()
    public void createAffiliate2() {
        open("/partners/new");
        $("#EditPartner_email").setValue(generate.internet().emailAddress());
        $("#EditPartner_password").setValue(generate.internet().password(6,12));
        $("#EditPartner_manager_id").selectOption(3);
        $("#EditPartner_country").selectOptionByValue("RU");
        $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName()));
        $("#EditPartner_status").selectOptionByValue("1");
        $("#EditPartner_submit").click();
        $("#EditPartner_submit").shouldHave(text("Save"));
        screenshot("Create Affiliate");
    }

    @Test()
    public void createAffiliate3() {
        open("/partners/new");
        $("#EditPartner_email").setValue(generate.internet().emailAddress());
        $("#EditPartner_password").setValue(generate.internet().password(6,12));
        $("#EditPartner_manager_id").selectOption(3);
        $("#EditPartner_country").selectOptionByValue("RU");
        $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName()));
        $("#EditPartner_status").selectOptionByValue("1");
        $("#EditPartner_submit").click();
        $("#EditPartner_submit").shouldHave(text("Save"));
        screenshot("Create Affiliate");
    }

    @BeforeEach
    public void login(){
        open("/user/login");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
    }

    @AfterEach
    public void close(){
        WebDriverRunner.closeWebDriver();
    }

}
