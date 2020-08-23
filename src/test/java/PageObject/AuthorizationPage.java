package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthorizationPage {

    private WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By privacyPolicyCheckBox = By.xpath("//input[@id='sign']");
    private By passwordRecovering = By.xpath("//a[text()='Password recovering']");
    private By signInButton = By.className("btn-success");

    // Methods
    public AuthorizationPage open(){
        getDriver().get("http://offers.staging.affise.com/");
        return this;
    }

    public MainPage clickSignIn(){
        driver.findElement(signInButton).click();
        return new MainPage(driver);
    }

    public PasswordRecoveryPage clickPasswordRecovery(){
        getDriver().findElement(passwordRecovering).click();
        return new PasswordRecoveryPage(getDriver());
    }

    public AuthorizationPage typeEmail(String email){
        getDriver().findElement(emailField).sendKeys(email);
        return this;
    }

    public AuthorizationPage typePassword(String password){
        getDriver().findElement(passwordField).sendKeys(password);
        return this;
    }

    public AuthorizationPage enablePrivacyPolicy(){
        selectCheckbox();
        return this;
    }

    public void selectCheckbox(){
        WebElement checkbox = getDriver().findElement(privacyPolicyCheckBox);
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

    // Getters and Setters
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public By getEmailField() {
        return emailField;
    }

    public void setEmailField(By emailField) {
        this.emailField = emailField;
    }

    public By getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(By passwordField) {
        this.passwordField = passwordField;
    }

    public By getPrivacyPolicyCheckBox() {
        return privacyPolicyCheckBox;
    }

    public void setPrivacyPolicyCheckBox(By privacyPolicyCheckBox) { this.privacyPolicyCheckBox = privacyPolicyCheckBox; }

    public By getPasswordRecovering() {
        return passwordRecovering;
    }

    public void setPasswordRecovering(By passwordRecovering) {
        this.passwordRecovering = passwordRecovering;
    }

    public By getSignInButton() {
        return signInButton;
    }

    public void setSignInButton(By signInButton) {
        this.signInButton = signInButton;
    }
}
