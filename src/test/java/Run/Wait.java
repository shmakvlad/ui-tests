package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class Wait {

    @Test
    public void implicitlyWait(){
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.get("http://offers.staging.affise.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);

    // Selenide
        sleep(10000);
    }

}
