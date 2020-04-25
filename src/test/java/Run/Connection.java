package Run;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;

public class Connection {

    @Test
    public void openClose(){
        WebDriver driver = new ChromeDriver();
        driver.get(Configuration.baseUrl);
        driver.close(); driver.quit();

        // Selenide
        closeWindow(); closeWebDriver();
    }

}
