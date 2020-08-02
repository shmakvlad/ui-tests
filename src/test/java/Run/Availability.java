package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Availability {

    private final WebDriver driver = new ChromeDriver();

    @Test
    public void available() {
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://html.com/attributes/button-disabled/");

        WebElement button = driver.findElement(By.xpath("//*[@id='post-76']/div/div[3]/button"));
        button.isEnabled();
        button.isSelected(); button.isDisplayed();

        WebElement radio = driver.findElement(By.xpath("//span/input[contains(@value,'female')][1]"));
        radio.isSelected();
        radio.isEnabled(); radio.isDisplayed();

        WebElement link = driver.findElement(By.xpath("//*[@id='search-2']/form/input"));
        link.isDisplayed();
        link.isEnabled(); link.isSelected();

        driver.quit();
    }

    @Test
    public void exist() {
    // Selenium
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");

        System.out.println(check("input#gh-btn"));
        System.out.println(check("input#gh-btn.class"));

        driver.quit();
    }

    public boolean check(String rbXpath) {
        List<WebElement> elements = driver.findElements(By.cssSelector(rbXpath));
        if (elements.size() > 0) return true;
        else return false;
    }

}
