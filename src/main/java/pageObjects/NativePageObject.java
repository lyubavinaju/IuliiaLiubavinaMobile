package pageObjects;

import org.openqa.selenium.By;

public class NativePageObject {
    private static final String ID_PREFIX = "platkovsky.alexey.epamtestapp:id/";

    By title = By.xpath(
        "// *[contains(@resource-id, '" + ID_PREFIX + "action_bar')] // android.widget.TextView");

    By signInBtn = By.id(ID_PREFIX + "email_sign_in_button");

    By registerBtn = By.id(ID_PREFIX + "register_button");

    By emailFld = By.id(ID_PREFIX + "registration_email");

    By usernameFld = By.id(ID_PREFIX + "registration_username");

    By passwordFld = By.id(ID_PREFIX + "registration_password");

    By confirmPasswordFld = By.id(ID_PREFIX + "registration_confirm_password");

    By registerNewAccountFld = By.id(ID_PREFIX + "register_new_account_button");

    By loginEmailFld = By.id(ID_PREFIX + "login_email");

    By loginPasswordFld = By.id(ID_PREFIX + "login_pwd");
}
