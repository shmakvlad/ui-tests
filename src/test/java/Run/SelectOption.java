package Run;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.remote.BrowserType.CHROME;

public class SelectOption {

    private final Faker generate = new Faker();

    @BeforeAll
    public static void setUp() {
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test
    public void createAffiliateSelenide(){
    // Selenide
        open("/partners/new");
        $("#EditPartner_email").setValue(generate.internet().emailAddress());
        $("#EditPartner_password").setValue(generate.internet().password(6,12));
        $("#EditPartner_manager_id").selectOption("AlexNew TestNew");
        $("#EditPartner_manager_id").selectOption(10);
        $("#EditPartner_status").selectOption(1);
        $("#EditPartner_country").selectOptionByValue("RU");
        $("#EditPartner_country").selectOptionContainingText("Macedonia");
        $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName()));
        $("#EditPartner_submit").click();
        $("#EditPartner_submit").shouldHave(text("Save"));
    }

    @Test
    public void selectSelenide(){
    // Selenide
        open("http://offers.staging.affise.com/statistics/affise/daily");

        SelenideElement selenideElement = $(byXpath("//div//span[text()='Country']/.."));
        selenideElement.click();

        ElementsCollection filters = $$("span.juk8CVoJ5WisqFm2gO5ve");
        filters.find(text("Belarus")).click();
        filters.find(text("Russian Federation")).click();
    }

    @Test
    public void selectSelenium(){
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    // Login
        driver.get(Configuration.baseUrl);
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
    // Select
        driver.get(Configuration.baseUrl + "/statistics/affise/daily");
        driver.findElement(byXpath("//div//span[text()='Country']/..")).click();

        List<WebElement> collection = driver.findElements(By.cssSelector("span.juk8CVoJ5WisqFm2gO5ve"));
        for (WebElement webElement : collection){
            switch(webElement.getText()){
                case "Belarus":
                case "Russian Federation":
                case "Cyprus":
                    webElement.click();
                    break;
            }
        }
        driver.quit();
    }

    @Test
    public void createAffiliateSelenium(){
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    // Login
        driver.get(Configuration.baseUrl);
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
    // Select
        driver.get(Configuration.baseUrl + "/partners/new");

        driver.findElement(By.cssSelector("#EditPartner_email")).sendKeys(generate.internet().emailAddress());
        driver.findElement(By.cssSelector("#EditPartner_password")).sendKeys(generate.internet().password(6,12));
        driver.findElement(By.cssSelector("#EditPartner_custom_fields_1")).sendKeys(generate.funnyName().name());

        driver.findElement(By.xpath("//span[@id='select2-EditPartner_manager_id-container']")).click();
//        driver.findElement(By.xpath("//ul/li[text()='Margarita Leffler']")).click();
//        driver.findElement(byText("Margarita Leffler")).click();
        for (WebElement webElement : driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"))){
            if (webElement.getText().equals("Blake Windler")) {
                webElement.click(); break; }
        }

        driver.findElement(By.id("EditPartner_status")).click();
//        driver.findElement(By.cssSelector("#EditPartner_status option[value='1']")).click();
//        driver.findElement(byValue("2")).click();
        for (WebElement webElement : driver.findElements(By.cssSelector("#EditPartner_status option"))){
            if (webElement.getText().equals("On moderation")) {
                webElement.click(); break; }
        }

        driver.findElement(By.xpath("//span[@id='select2-EditPartner_country-container']/parent::span")).click();
        for (WebElement webElement : driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"))){
            System.out.println(webElement.getText());
            if (webElement.getText().equals("Albania")) {
                webElement.click(); break; }
        }

        driver.findElement(By.cssSelector("#EditPartner_submit")).click();
        assert driver.findElement(By.cssSelector("#EditPartner_submit")).getText().equals("Save");
        driver.quit();
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
