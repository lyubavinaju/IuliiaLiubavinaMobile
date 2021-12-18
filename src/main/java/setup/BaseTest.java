package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

public class BaseTest implements IDriver {
    private static final int TEN_SECONDS = 10;

    private static WebDriverWait wait;
    private static AppiumDriver<? extends WebElement> appiumDriver; // singleton
    IPageObject po;

    @Override
    public AppiumDriver<? extends WebElement> getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    @Override
    public WebElement getWelement(String weName)
        throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        By locator = getPo().getLocator(weName);
        new WebDriverWait(getDriver(), TEN_SECONDS)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getDriver().findElement(locator);
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName, @Optional("") String browserName,
                      @Optional("") String app) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType);
        wait = new WebDriverWait(getDriver(), TEN_SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        try {
            URL url = new URL(System.getProperty("ts.appium"));
            if (app.endsWith(".apk")) {
                capabilities.setCapability("app", (new File(app)).getAbsolutePath());
                appiumDriver = new AndroidDriver<>(url, capabilities);
            } else {
                appiumDriver = new AppiumDriver<>(url, capabilities);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(TEN_SECONDS, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType) throws Exception {
        po = new PageObject(appType);
    }
}
