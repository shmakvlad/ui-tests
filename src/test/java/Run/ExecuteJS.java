package Run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class ExecuteJS {

    @Test
    public void SelenideTabs() {
        String script = "window.open('"+ "http://offers.staging.affise.com/stats/custom" +"','_blank');";
        executeJavaScript(script);
        switchTo().window(1);
        sleep(5000);
    }

    @BeforeEach
    public void login(){
        open("/user/login");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
    }

}
