package dataprovider;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppiumHubProperties {
    private static ResourceBundle rb = ResourceBundle.getBundle("appium_hub");

    public static String getProjectName() {
        return rb.getString("PROJECT_NAME");
    }

    public static String getApiKey() {
        return rb.getString("API_KEY");
    }

    public static String getUrlTemplate(String hubType) {
        return rb.getString(hubType.toUpperCase(Locale.ROOT));
    }
}
