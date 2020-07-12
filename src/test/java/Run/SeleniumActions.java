package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class SeleniumActions {

    @Test
    public void actions() {
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement menuItem = driver.findElement(By.xpath("//li/a[text()='Электроника']"));
        WebElement element = driver.findElement(By.xpath("//li/a[text()='Элемент']"));
        Actions make = new Actions(driver);

    // Навести курсор мыши на элемент
        make.moveToElement(menuItem).build().perform();

    // Drag and drop
        make.dragAndDrop(element, menuItem).build().perform();

    // Drag and drop | Нажать и не отпускать курсор мыши -> наводим курсор на элемент(перетаскиваем) -> отпускаем курсор
        make.clickAndHold(element).moveToElement(menuItem).release().build().perform();

    // Вынести действие в переменную
        Action action = make.clickAndHold(element).moveToElement(menuItem).release().build();
        action.perform();

    // Двойной клик по элементу
        make.doubleClick(element).build().perform();

    // Имитация нажатия правой кнопки мыши по элементу
        make.contextClick(element).build().perform();
    }

}
