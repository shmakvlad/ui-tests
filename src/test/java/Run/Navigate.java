package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Navigate {

    @Test
    public void navigateBackForwardRefresh() {

        open("https://google.com");
        $(By.name("q")).setValue("hello").pressEnter();
        back(); forward(); refresh();
        $("#searchform").find(By.name("q")).setValue("world").getWrappedDriver().navigate().back();
    }

}
