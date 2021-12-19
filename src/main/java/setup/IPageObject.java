package setup;

import org.openqa.selenium.By;

public interface IPageObject {
    By getLocator(String weName) throws NoSuchFieldException, IllegalAccessException, InstantiationException;
}
