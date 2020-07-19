package Selenoid.Provider;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider implements WebDriverProvider {

    private String ip = "10.201.0.80";
//    private String ip = "46.101.243.221";
//    private String ip = "localhost";

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("83.0");
        browser.setCapability("enableVNC", true);
        browser.setCapability("enableVideo", false);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://" + ip + ":4444/wd/hub").toURL(),
                    browser
            );
            driver.manage().window().setSize(new Dimension(1280, 1024));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
