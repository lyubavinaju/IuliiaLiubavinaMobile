package activity;

public enum Activity {
    REGISTRATION("Registration", "Registration"),
    LOGIN("EPAM Test App", "EPAM Test App"),
    BUDGET("BudgetActivity", "Budget");
    private String androidTitle;
    private String iosTitle;

    Activity(String androidTitle, String iosTitle) {
        this.androidTitle = androidTitle;
        this.iosTitle = iosTitle;
    }

    public String getAndroidTitle() {
        return androidTitle;
    }

    public String getIosTitle() {
        return iosTitle;
    }
}
