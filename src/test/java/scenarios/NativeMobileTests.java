package scenarios;

import activity.Activity;
import dataprovider.TestData;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageObjects.PageObject;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {
    private void checkActivity(Activity activity)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getWait().until(ExpectedConditions.textToBe(getPo().getLocator("title"),
                ((PageObject) getPo()).getActivityTitle(activity)));

    }

    @Test(groups = {"native"},
            description = "Register the new account and the sign in",
            dataProviderClass = TestData.class, dataProvider = "nativeTestData")
    public void registerAndSignInNativeTest(String email, String username, String password)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().hideKeyboard();
        getWelement("registerBtn").click();
        checkActivity(Activity.REGISTRATION);
        getDriver().hideKeyboard();
        getWelement("emailFld").sendKeys(email);
        getWelement("usernameFld").sendKeys(username);
        getWelement("passwordFld").sendKeys(password);
        getWelement("confirmPasswordFld").sendKeys(password);
        getWelement("agree").click();
        WebElement registerBtn = getWelement("registerBtn");
        registerBtn.click();
        try {
            registerBtn.click();
        } catch (StaleElementReferenceException ignored) {

        }
        checkActivity(Activity.LOGIN);
        getWelement("loginEmailFld").sendKeys(email);
        getWelement("loginPasswordFld").sendKeys(password);
        getWelement("signInBtn").click();
        checkActivity(Activity.BUDGET);
    }
}