package pageObjects;

import activity.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import setup.IPageObject;

import java.lang.reflect.Field;

public class PageObject implements IPageObject {

    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType, String platformName) throws Exception {
        System.out.println("Current app type: " + appType);
        switch (appType) {
            case "web":
                somePageObject = new WebPageObject();
                break;
            case "native":
                if (platformName.equalsIgnoreCase(Platform.ANDROID.name())) {
                    somePageObject = new AndroidNativePageObject();
                } else {
                    somePageObject = new IosNativePageObject();
                }
                break;
            default:
                throw new Exception("Can't create a page object for " + appType);
        }
    }

    @Override
    public By getLocator(String weName) throws NoSuchFieldException, IllegalAccessException {
        // use reflection technique
        Field field = somePageObject.getClass().getDeclaredField(weName);
        field.setAccessible(true);
        return (By) field.get(somePageObject);
    }

    public String getActivityTitle(Activity activity) {
        return ((INativePageObject) somePageObject).getActivityTitle(activity);
    }
}
