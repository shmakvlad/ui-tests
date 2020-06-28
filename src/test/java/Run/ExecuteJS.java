package Run;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.remote.BrowserType.CHROME;

public class ExecuteJS {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test
    public void SelenideTabsAlerts() {
        login();
        String script = "window.open('"+ "http://offers.staging.affise.com/stats/custom" +"','_blank');";
        executeJavaScript(script);
        switchTo().window(1);

        executeJavaScript("alert('Hello World!');");
        switchTo().alert().accept();

        executeJavaScript("confirm('Are you sure?');");
        switchTo().alert().getText();
        switchTo().alert().dismiss();
    }

    @Test
    public void executeJsSelenium(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://lenta.ru");

        // 1
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, 2500)", "");
        jse.executeScript("window.scrollBy(0, -500)", "");

        // 2
        String script = "window.open('https://www.google.com');";
        jse.executeScript(script);

        // 3
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        jse.executeScript("alert('Hello World!');");
    }

    @Test
    public void alertClick() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://lenta.ru");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("alert('Hello World!');");
        driver.switchTo().alert().accept();

        jse.executeScript("confirm('Are you sure?');");
        driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
    }

    public void login(){
        open("/user/login");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
    }

}
