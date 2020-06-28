package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SelenideGetSelenium {

    @Test
    public void getWrappedDriver() {
        open("https://google.com");
        $(By.name("q")).setValue("hello").getWrappedDriver().close();
        getWebDriver();
    }
}
