package Run;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Table {

    private WebElement tableElement;
    private WebDriver driver;

    public Table(WebElement tableElement, WebDriver driver) {
        this.tableElement = tableElement;
        this.driver = driver;
    }

    public List<WebElement> getRows(){
        return tableElement.findElements(By.xpath(".//tbody//tr"));
    }

    public List<WebElement> getHeading(){
        return tableElement.findElements(By.xpath(".//thead/tr/th"));
    }

    public List<List<WebElement>> getRowsColumns(){
        List<List<WebElement>> collection = new ArrayList<>();
        for (WebElement element : getRows()){
            collection.add(element.findElements(By.xpath(".//td")));
        }
        return collection;
    }

    public String getValue(int rowNumber, int columnNumber){
        List<List<WebElement>> collection = getRowsColumns();
        WebElement element = collection.get(rowNumber - 1).get(columnNumber - 1);
        return element.getText();
    }

    @Test
    public void selenideTable() {
    // Selenide
        open("http://offers.staging.affise.com/stats/custom");
        $("input.form-control.date-range").click();
        $$("div.ranges li").findBy(text("This Month")).click();

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

        assertThat(getRowsSelenide().size(), equalTo(5));
        assertThat(getValueSelenide().get(3).get("Clicks").getText(), equalTo("201"));
        assertThat(getTextValueSelenide(2, "Clicks"), equalTo("752"));
    }

    public static ElementsCollection getRowsSelenide(){
        return $$(".table.table-stats tbody tr");
    }

    public ElementsCollection getHeadingSelenide(){
        return $$(".table.table-stats thead tr th");
    }

    public List<ElementsCollection> getRowsColumnsSelenide(){
        List<ElementsCollection> collection = new ArrayList<>();
        for (SelenideElement element : getRowsSelenide()){
            collection.add(element.findAll("td"));
        }
        return collection;
    }

    public String getValueFromSelenide(int rowNumber, int columnNumber){
        List<ElementsCollection> collection = getRowsColumnsSelenide();
        SelenideElement element = collection.get(rowNumber - 1).get(columnNumber - 1);
        return element.getText();
    }

    public String getValueFromSelenide(int rowNumber, String columnName){
        List<ElementsCollection> collection = getRowsColumnsSelenide();
        int columnNumber = 0;
        for (int i = 0; i < getHeadingSelenide().size(); i++){
            if (getHeadingSelenide().get(i).getText().equals(columnName)){
                columnNumber = i; break;
            }
        }
        SelenideElement element = collection.get(rowNumber - 1).get(columnNumber);
        return element.getText();
    }

    public String getTextValueSelenide(int rowNumber, String columnName){
        return getValueSelenide().get(rowNumber - 1).get(columnName).getText();
    }

    public List<Map<String, SelenideElement>> getValueSelenide(){
        List<Map<String, SelenideElement>> collection = new ArrayList<>();
        for (ElementsCollection elementsCollection : getRowsColumnsSelenide()){
            Map<String, SelenideElement> map = new HashMap<>();
            for (int i = 0; i < getHeadingSelenide().size(); i++){
               map.put(getHeadingSelenide().get(i).getText(), elementsCollection.get(i));
            }
            collection.add(map);
        }
        return collection;
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
