package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldButtonLink {

    @Test
    public void button() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://offers.staging.affise.com");
        WebElement button = driver.findElement(By.xpath("//form[@id='loginForm']//button"));
        assertThat(button.getText(), equalTo("Sign in"));

        button.submit(); driver.quit();
    }

    @Test
    public void field() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://offers.staging.affise.com");

        WebElement button = driver.findElement(By.xpath("//form[@id='loginForm']//button"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));

        email.sendKeys("ivan@gmail.com");
        password.sendKeys("vlad12-8");
        email.getAttribute("class"); email.getAttribute("value"); password.getAttribute("value");

        button.submit(); driver.quit();
    }

    @Test
    public void link() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://offers.staging.affise.com");

        WebElement link = driver.findElement(By.linkText("Password recovering"));
        driver.findElement(By.xpath("//a[text()='Password recovering']"));

        link.getText(); link.click(); driver.quit();
    }

}
