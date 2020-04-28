package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FindElement {

    @Test
    public void affiseLoginSelectorsSelenide() {
    // Selenide
        open("http://offers.staging.affise.com/user/login");

        $(By.id("email")).setValue("ivan@gmail.com");
        $(byId("email")).setValue("vlad@gmail.com");
        $("#email").setValue("dima@gmail.com");
        $("input[required='required'][type='email']").setValue("vova@gmail.com");
        $("#password").setValue("vlad12-8");

        $(byClassName("form-group")).find(By.name("email")).setValue("roma@gmail.com");
        $(byName("email")).setValue("george@gmail.com");

        $(By.className("btn-success")).click();
        $(byClassName("btn-success")).click();

        $(By.xpath("//button[@class='btn btn-success btn-block']")).click();
        $(byXpath("//*[@id='loginForm']/button")).click();

        $(byText("Sign in")).click();
        $(byTitle("success"));
        $(byValue("success"));
        $(By.tagName("button")).click();

        $(By.cssSelector("button.btn.btn-success.btn-block")).click();
        $(By.cssSelector("button[class='btn btn-success btn-block']")).click();
        $("[class='btn btn-success btn-block']").click();
        $(byCssSelector("#loginForm > button")).click();

        $(by("class", "btn btn-success btn-block")).click();
        $(byAttribute("class", "btn btn-success btn-block")).click();

        $(By.linkText("Password recovering")).click(); back();
        $(byLinkText("Password recovering")).click(); back();
        $(By.partialLinkText("Password recovering")).click(); back();
        $(byPartialLinkText("Password recovering")).click(); back();
    }

    @Test
    public void affiseLoginSelectorsSelenium() {
    // Selenium
        WebDriver driver = null;

        try {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            driver.get("http://offers.staging.affise.com");

            driver.findElement(By.className("form-group"))
                  .findElement(By.cssSelector("input#email.form-control")).sendKeys("ivan@gmail.com");

            driver.findElement(By.id("password")).sendKeys("vlad12");
            driver.findElement(By.xpath("//*[@required='required' and @type='password']")).clear();
            driver.findElement(By.name("password")).sendKeys("vlad12-8");
            driver.findElement(By.className("btn-success")).click();

        } finally {
            driver.quit();
        }
    }

}
