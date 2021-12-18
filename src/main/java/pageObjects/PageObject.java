package pageObjects;

import java.lang.reflect.Field;
import org.openqa.selenium.By;
import setup.IPageObject;

public class PageObject implements IPageObject {

    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType) throws Exception {
        System.out.println("Current app type: " + appType);
        switch (appType) {
            case "web":
                somePageObject = new WebPageObject();
                break;
            case "native":
                somePageObject = new NativePageObject();
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
}
