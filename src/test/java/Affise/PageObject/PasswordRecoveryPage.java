package Affise.PageObject;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PasswordRecoveryPage {

    public static By emailField = By.id("form_email");
    public static By restoreButton = By.className("btn-success");

    public static PasswordRecoveryPage typeEmail(String email){
        $(emailField).setValue(email);
        return new PasswordRecoveryPage();
    }

    public static AuthorizationPage clickRestoreButton(){
        $(restoreButton).click();
        return new AuthorizationPage();
    }

}
