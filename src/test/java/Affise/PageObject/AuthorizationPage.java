package Affise.PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {

    public static By emailField = By.id("email");
    public static By passwordField = By.id("password");
    public static By privacyPolicyCheckBox = By.xpath("//input[@id='sign']");
    public static By passwordRecovering = By.xpath("//a[text()='Password recovering']");
    public static By signInButton = By.className("btn-success");

    public static AuthorizationPage open(){
        Selenide.open("");
        return new AuthorizationPage();
    }

    public static MainPage authorizeUser(String email, String password){
        $(emailField).setValue(email);
        $(passwordField).setValue(password);
        selectCheckbox(privacyPolicyCheckBox);
        $(signInButton).click();
        return new MainPage();
    }

    public static MainPage logIn(String email, String password){
        open();
        authorizeUser(email, password);
        return new MainPage();
    }

    public static PasswordRecoveryPage clickPasswordRecovery(){
        open();
        $(passwordRecovering).click();
        return new PasswordRecoveryPage();
    }

    public static void selectCheckbox(By locator){
        SelenideElement selenideElement = $(locator);
        if (!(selenideElement.isSelected())){
            selenideElement.click();
        }
    }

}
