package activity;

public enum Activity {
    REGISTRATION("Registration", "Registration"),
    LOGIN("LoginActivity", "EPAM Test App"),
    BUDGET("BudgetActivity", "BudgetActivity");
    private String name;
    private String title;

    Activity(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
