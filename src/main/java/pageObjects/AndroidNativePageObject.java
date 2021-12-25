package pageObjects;

import activity.Activity;
import org.openqa.selenium.By;

public class AndroidNativePageObject implements INativePageObject {
    private static final String ID_PREFIX = "platkovsky.alexey.epamtestapp:id/";
    private static final String BUTTON_TEMPLATE = "// android.widget.Button[@text='%s']";

    By title = By.xpath(
            "// *[contains(@resource-id, '" + ID_PREFIX + "action_bar')] // android.widget.TextView");

    By signInBtn = By.xpath(String.format(BUTTON_TEMPLATE, "SIGN IN"));

    By registerBtn = By.xpath(String.format(BUTTON_TEMPLATE, "REGISTER NEW ACCOUNT"));

    By emailFld = By.id(ID_PREFIX + "registration_email");

    By usernameFld = By.id(ID_PREFIX + "registration_username");

    By passwordFld = By.id(ID_PREFIX + "registration_password");

    By agree = By.xpath("//android.widget.CheckedTextView");

    By confirmPasswordFld = By.id(ID_PREFIX + "registration_confirm_password");

    By loginEmailFld = By.id(ID_PREFIX + "login_email");

    By loginPasswordFld = By.id(ID_PREFIX + "login_pwd");

    @Override
    public String getActivityTitle(Activity activity) {
        return activity.getAndroidTitle();
    }
}
