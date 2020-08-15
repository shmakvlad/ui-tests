package Run;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CheckboxRadioButtons {

    @Test
    public void checkboxSelenide() {
    // Selenide
        open("http://offers.staging.affise.com/stats/custom");
        $("input.form-control.date-range").click();
        $$("div.ranges li").findBy(text("Today")).click();

        ElementsCollection data = $$("div ul li label");
        data.findBy(text("Day")).click();
        data.findBy(text("Affiliate")).click();
        data.findBy(text("Offer")).click();
        data.find(text("Clicks")).click();
        data.findBy(text("Total conversions")).click();

        $(byValue("View")).click();
        $(".table.table-stats").shouldBe(exist);
        $(".table.table-stats").shouldBe(visible);
        $(".table.table-stats").shouldBe(enabled);
    }

    @Test
    public void checkboxSelenium() {
    // Selenium
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.eldorado.ru/c/televizory/f/smart-tv");
        driver.findElement(By.xpath("//span/a[text()='Samsung']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung']/parent::span")).click();

        WebElement checkbox = driver.findElement(By.xpath("//a[text()='Samsung']"));
        checkbox.click(); checkbox.isSelected(); driver.quit();
    }

    @Test
    public void radioSelenium() {
    // Selenium
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://visa.vrm.lt/epm/pages/applications/applicationEdit.xhtml");
        driver.findElement(By.xpath("//label[contains(text(),'C - Short term')]")).click();
        driver.findElement(By.xpath("//table[@id='applicationEditForm:EUCitizenRelative']//label[contains(text(),'Yes')]")).click();
        driver.quit();
    }

    @Test
    public void modalForm() {
    // Selenide
        open("http://offers.staging.affise.com/stats/custom");
        $("input.form-control.date-range").click();

    // ElementsCollection
        ElementsCollection dateRange = $(By.tagName("body")).find(".ranges").findAll(By.tagName("li"));
        $(By.tagName("body")).find(".ranges").findAll("ul li");
        $(By.tagName("body")).find(".ranges").$$x("ul/li");
        $$(byXpath("//div[@class='ranges']/ul/li"));
        $$("div.ranges li");
        $$("div.ranges li").filterBy(text("Yesterday"));
        dateRange.size(); dateRange.get(3); dateRange.first(); dateRange.texts(); dateRange.isEmpty();

    // SelenideElement
        dateRange.find(text("Yesterday"));
        dateRange.findBy(text("Yesterday"));
        dateRange.findBy(cssClass("Yesterday"));
        SelenideElement element = dateRange.find(attribute("data-range-key","Yesterday"));
        element.getText(); element.getTagName(); element.getValue(); element.getAttribute("data-range-key");
        $(By.tagName("body")).find(".ranges").findAll("ul li").findBy(text("Yesterday"));
        $$("div.ranges li").filterBy(text("Yesterday")).findBy(text("Yesterday"));
        $$("div.ranges li").findBy(text("Yesterday"));
    }

    @Test
    public void helperMethodsSelenide() {
    // Selenide
        open("http://offers.staging.affise.com/stats/custom");
        $("input.form-control.date-range").click();
        $$("div.ranges li").findBy(text("Today")).click();

        selectCheckbox("Day");
        selectCheckbox("Affiliate");
        selectCheckbox("Offer");
        selectCheckbox("Clicks");
        selectCheckbox("Total conversions");

        clickButton("View");
        statsDisplayed();
    }

    public void selectCheckbox(String name){
        String rbXpath = "//label[contains(text(),'%s')]//preceding-sibling::input";
        SelenideElement selenideElement = $(byXpath(String.format(rbXpath, name)));
        if (!(selenideElement.isSelected())){
            selenideElement.click();
        }
    }

    public void clickButton(String name){
        SelenideElement selenideElement = $(byValue(name));
        selenideElement.click();
    }

    public void statsDisplayed(){
        SelenideElement selenideElement = $(".table.table-stats");
        selenideElement.shouldBe(exist);
        selenideElement.shouldBe(visible);
        selenideElement.shouldBe(enabled);
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
