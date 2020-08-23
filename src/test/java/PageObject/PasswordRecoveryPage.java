package PageObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Data // @ToString, @EqualsAndHashCode, @Getter on all fields, and @Setter on all non-final fields, and @RequiredArgsConstructor!
@NoArgsConstructor  // Do not use to avoid losing the driver
@AllArgsConstructor // Do not use to avoid losing the driver
@Accessors(fluent = false) // Set true for Builder pattern
public class PasswordRecoveryPage {

    private WebDriver driver;
    private By emailField = By.id("form_email");
    private By restoreButton = By.className("btn-success");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public PasswordRecoveryPage typeEmail(String email){
        getDriver().findElement(emailField).sendKeys(email);
        return this;
    }

    public AuthorizationPage clickRestoreButton(){
        getDriver().findElement(restoreButton).click();
        return new AuthorizationPage(getDriver());
    }

}
