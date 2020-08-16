package Run;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoadFileSendKeysScreenshot {

    @Test
    public void loadFile() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://images.google.com");
        driver.findElement(By.xpath("//div[@aria-label='Пошук па відарысе']")).click();
        driver.findElement(By.xpath("//a[text()='Запампаваць відарыс']")).click();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("/Users/shmakvlad/Downloads/screenshot.png");

        driver.quit();
    }

    @Test
    public void sendKeys() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        String select = Keys.chord(Keys.COMMAND, "A");
        String copy = Keys.chord(Keys.COMMAND, "C");
        String cut = Keys.chord(Keys.COMMAND, "X");
        String paste = Keys.chord(Keys.COMMAND, "V");

        driver.get("http://en.wikipedia.org/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("test text");
        searchInput.sendKeys(select);
        searchInput.sendKeys(copy);
        searchInput.sendKeys(cut);
        searchInput.sendKeys(paste);
        searchInput.sendKeys(Keys.ENTER);

        makeScreenshot(driver);
        driver.quit();
    }

    public void makeScreenshot(WebDriver driver){
        String fileName = new SimpleDateFormat("hh_mm_ss").format(new Date()) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./build/reports/tests/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
