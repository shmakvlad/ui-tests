package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class Manage {

    @Test
    public void openClose(){
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//        System.setProperty("webdriver.chrome.driver", "chromedriver");

        ChromeDriverService service =
                new ChromeDriverService.Builder().withWhitelistedIps("").withVerbose(true).build();

        WebDriver driver = new ChromeDriver(service);
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(900,600));
        driver.get("https://google.com");
        driver.quit();
    }

}
