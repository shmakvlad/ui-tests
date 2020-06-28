package Run;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.remote.BrowserType.CHROME;

public class switchTabs {

    private final Faker generate = new Faker();

    @BeforeAll
    public static void setUp() {
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test
    public void SelenideTabsJs() {
        String script = "window.open('"+ "http://offers.staging.affise.com/stats/custom" +"','_blank');";
        executeJavaScript(script);
        switchTo().window(1); switchTo().window(0); switchTo().window(1);
        sleep(2000);
    }

    @Test
    public void SelenideTabs() {
        getWebDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        getWebDriver().findElement(By.xpath("//a[contains(text(),'Statistics')]")).sendKeys(Keys.COMMAND, Keys.ENTER);
        switchTo().window(1);
        sleep(3000);
        switchTo().window(0);
        sleep(3000);

        switchTo().window(1);
        open("https://www.facebook.com");
        sleep(3000);
        closeWindow();

        switchTo().window(0);
        open("https://www.news.google.com");
        sleep(3000);
        closeWebDriver();
    }

    @Test
    public void SelenideTabsWindowHandles() {
        sleep(2000);
        getWebDriver().findElement(By.xpath("//a[contains(text(),'Statistics')]")).sendKeys(Keys.COMMAND, Keys.ENTER);

        Set<String> handles = getWebDriver().getWindowHandles();
        List<String> tabs = new ArrayList<>(handles);
//        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());

        switchTo().window(tabs.get(1));
        sleep(2000);
        switchTo().window(tabs.get(0));
        sleep(2000);

        switchTo().window(tabs.get(1));
        open("https://www.facebook.com");
        sleep(2000);
        getWebDriver().close();

        switchTo().window(tabs.get(0));
        open("https://www.news.google.com");
        sleep(2000);
        getWebDriver().quit();
    }

    @Test
    public void createAffiliateSelenium(){
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    // Login
        driver.get(Configuration.baseUrl);
        sleep(3000);
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
    // New tab
        String selectLinkOpeninNewTab = Keys.chord(Keys.COMMAND, Keys.ENTER);
        driver.findElement(By.xpath("//a[contains(text(),'Statistics')]")).sendKeys(selectLinkOpeninNewTab);
        driver.findElement(By.xpath("//a[contains(text(),'Statistics')]")).sendKeys(Keys.COMMAND, Keys.ENTER);

    // New empty tab | Not working, bug in Selenium
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "T");
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND, "t");

    // Switch Tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.switchTo().window(tabs.get(0));

        driver.switchTo().window(tabs.get(1));
        driver.get("https://www.facebook.com");
        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.get("https://www.news.google.com");
        driver.quit();
    }

    @BeforeEach
    public void login(){
        open("http://offers.staging.affise.com");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
    }

}
