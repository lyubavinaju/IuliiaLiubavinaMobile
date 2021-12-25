package setup;

import dataprovider.AppiumHubProperties;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

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

    @Parameters({"hubType", "platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String hubType,
                      String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(hubType, platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, platformName);
        wait = new WebDriverWait(getDriver(), TEN_SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        ofNullable(appiumDriver).ifPresent(RemoteWebDriver::quit);
    }

    private void setAppiumDriver(String hubType, String platformName, String deviceName, String udid, String browserName, String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);
        if (platformName.equalsIgnoreCase("iOS")) capabilities.setCapability("automationName", "XCUITest");
        try {
            String key = URLEncoder.encode(AppiumHubProperties.getApiKey(), StandardCharsets.UTF_8.name());
            URL url = new URL(format(AppiumHubProperties.getUrlTemplate(hubType),
                    AppiumHubProperties.getProjectName(),
                    key,
                    System.getProperty("APPIUM_HUB")));
            appiumDriver = new AppiumDriver<>(url, capabilities);
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(TEN_SECONDS, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, String platformName) throws Exception {
        po = new PageObject(appType, platformName);
    }
}
