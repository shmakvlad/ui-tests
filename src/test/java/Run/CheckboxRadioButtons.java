package Run;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

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
        $$("div.ranges li").findBy(text("Yesterday")).click();

        ElementsCollection data = $$("div ul li label");
        data.findBy(text("Day")).click();
        data.findBy(text("Affiliate")).click();
        data.findBy(text("Offer")).click();
        data.findBy(text("Clicks")).click();
        data.findBy(text("Total conversions")).click();

        $(byValue("View")).click();
        $(".table.table-stats").shouldBe(exist);
        $(".table.table-stats").shouldBe(visible);
        $(".table.table-stats").shouldBe(enabled);
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
        SelenideElement element = dateRange.find(attribute("data-range-key","Yesterday"));
        element.getText(); element.getTagName(); element.getValue(); element.getAttribute("data-range-key");
        $(By.tagName("body")).find(".ranges").findAll("ul li").findBy(text("Yesterday"));
        $$("div.ranges li").filterBy(text("Yesterday")).findBy(text("Yesterday"));
        $$("div.ranges li").findBy(text("Yesterday"));
    }

    @BeforeEach
    public void login(){
        open("http://offers.staging.affise.com");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $("button.btn.btn-success.btn-block").click();
    }

}
