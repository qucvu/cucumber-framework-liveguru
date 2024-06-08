package stepsDefinition;

public class SharedState {
    private static SharedState instance;
    private String email;

    private SharedState() {
    }

    public static SharedState getInstance() {
        if (instance == null) {
            instance = new SharedState();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
