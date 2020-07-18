package Affise;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class Affiliate {

    private final Faker generate = new Faker();

    @RegisterExtension
    static ScreenShooterExtension sh = new ScreenShooterExtension(true);

    @BeforeAll
    public static void setUp() {
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test
    public void createAffiliate1() {
        step("Открыть страницу создания партнера", () -> { open("/partners/new"); });
        step("Заполнить поле Email", () -> { $("#EditPartner_email").setValue(generate.internet().emailAddress()); });
        step("Заполнить поле Password", () -> { $("#EditPartner_password").setValue(generate.internet().password(6,12)); });
        step("Заполнить поле Manager", () -> { $("#EditPartner_manager_id").selectOption(3); });
        step("Заполнить поле Country", () -> { $("#EditPartner_country").selectOptionByValue("RU"); });
        step("Заполнить поле Skype", () -> { $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName())); });
        step("Заполнить поле Status", () -> { $("#EditPartner_status").selectOptionByValue("1"); });
        step("Нажать на кнопку Add", () -> { $("#EditPartner_submit").click(); });
        step(" <--- Validation ---> " +
                "1. Партнер успешно создан. " +
                "2. Открыта станица редактирования партнера.", () -> { $("#EditPartner_submit").shouldHave(text("Save")); });
        closeWebDriver();
    }

    @Test
    public void createAffiliate2() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        step("Открыть страницу создания партнера", () -> {
            open("/partners/new");
        });
        step("Заполнить поля корректными данными", () -> {
            $("#EditPartner_email").setValue(generate.internet().emailAddress());
            $("#EditPartner_password").setValue(generate.internet().password(6,12));
            $("#EditPartner_manager_id").selectOption(3);
            $("#EditPartner_country").selectOptionByValue("RU");
            $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName()));
            $("#EditPartner_status").selectOptionByValue("1");
            $("#EditPartner_submit").click();
            $("#EditPartner_submit").shouldHave(text("Save"));
        });
        step("Закрыть вкладку", () -> {
            closeWebDriver();
        });
    }

    @BeforeEach
    public void login(){
        open("/user/login");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
        screenshot("login");
    }

}
