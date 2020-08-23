package PageObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false)
public class LoginPageFactory {

    private WebDriver driver;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='sign']")
    private WebElement privacyPolicyCheckBox;

    @FindBy(xpath = "//a[text()='Password recovering']")
    private WebElement passwordRecovering;

    @FindBy(className = "btn-success")
    private WebElement signInButton;

    public LoginPageFactory open(){
        getDriver().get("http://offers.staging.affise.com/");
        return this;
    }

    public MainPage clickSignIn(){
        signInButton.click();
        return new MainPage(driver);
    }

    public PasswordRecoveryPage clickPasswordRecovery(){
        passwordRecovering.click();
        return new PasswordRecoveryPage(getDriver());
    }

    public LoginPageFactory typeEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public LoginPageFactory typePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPageFactory enablePrivacyPolicy(){
        selectCheckbox();
        return this;
    }

    public void selectCheckbox(){
        WebElement checkbox = privacyPolicyCheckBox;
        if (!(checkbox.isSelected())){
            checkbox.click();
        }
    }

    public MainPage authorizeUser(String email, String password){
        this.typeEmail(email);
        this.typePassword(password);
        this.enablePrivacyPolicy();
        this.clickSignIn();
        return new MainPage(getDriver());
    }

}
