package dataprovider;

import java.util.ResourceBundle;
import org.testng.annotations.DataProvider;

public class TestData {
    private static final String GOOGLE_URL = "https://www.google.com/";
    private static final String GOOGLE_TITLE = "Google";

    @DataProvider
    public static Object[][] nativeTestData() {
        ResourceBundle rb = ResourceBundle.getBundle("native");
        String email = rb.getString("email");
        String username = rb.getString("username");
        String password = rb.getString("password");
        return new Object[][] {
            {email, username, password}
        };
    }

    @DataProvider
    public static Object[][] googleWebTestData() {
        ResourceBundle rb = ResourceBundle.getBundle("web");
        String word = rb.getString("word");
        return new Object[][] {
            {GOOGLE_URL, GOOGLE_TITLE, word}
        };
    }
}
