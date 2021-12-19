package scenarios;

import activity.Activity;
import dataprovider.TestData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {
    private void checkActivity(Activity activity)
        throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getWait().until(driver ->
            (((AndroidDriver<?>) driver).currentActivity()).contains(activity.getName()));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(getPo().getLocator("title")));
        Assert.assertEquals(activity.getTitle(), getWelement("title").getText());
    }

    @Test(groups = {"native"},
          description = "Register the new account and the sign in",
          dataProviderClass = TestData.class, dataProvider = "nativeTestData")
    public void simpleNativeTest2(String email, String username, String password)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().hideKeyboard();
        getWelement("registerBtn").click();
        checkActivity(Activity.REGISTRATION);
        getDriver().hideKeyboard();
        getWelement("emailFld").sendKeys(email);
        getWelement("usernameFld").sendKeys(username);
        getWelement("passwordFld").sendKeys(password);
        getWelement("confirmPasswordFld").sendKeys(password);
        getWelement("registerNewAccountFld").click();
        checkActivity(Activity.LOGIN);
        getWelement("loginEmailFld").sendKeys(email);
        getWelement("loginPasswordFld").sendKeys(password);
        getWelement("signInBtn").click();
        checkActivity(Activity.BUDGET);
    }
}