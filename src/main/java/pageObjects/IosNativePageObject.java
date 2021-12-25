package pageObjects;

import activity.Activity;
import org.openqa.selenium.By;

public class IosNativePageObject implements INativePageObject {
    private String textFieldPattern =
            "//XCUIElementTypeStaticText[@label='%s'] / following-sibling::XCUIElementTypeTextField";
    private String secureTextFieldPattern =
            "//XCUIElementTypeStaticText[@label='%s'] / following-sibling::XCUIElementTypeSecureTextField";
    private static final String BUTTON_TEMPLATE = "//XCUIElementTypeStaticText[@name='%s']";

    By title = By.xpath(
            "// XCUIElementTypeNavigationBar / XCUIElementTypeStaticText");

    By signInBtn = By.xpath(String.format(BUTTON_TEMPLATE, "Sign In"));

    By registerBtn = By.xpath(String.format(BUTTON_TEMPLATE, "Register new account"));

    By emailFld = By.xpath(String.format(textFieldPattern, "Email"));

    By usernameFld = By.xpath(String.format(textFieldPattern, "Username"));

    By passwordFld = By.xpath(String.format(secureTextFieldPattern, "Password"));

    By confirmPasswordFld = By.xpath(String.format(secureTextFieldPattern, "Confirm password"));

    By agree = By.xpath("// XCUIElementTypeSwitch");

    By loginEmailFld = By.xpath(String.format(textFieldPattern, "Username or email"));

    By loginPasswordFld = By.xpath(String.format(secureTextFieldPattern, "Password"));

    @Override
    public String getActivityTitle(Activity activity) {
        return activity.getIosTitle();
    }
}
