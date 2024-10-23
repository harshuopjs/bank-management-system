import java.util.Arrays;

// Other imports...

public class User {
    private String username;
    private String password;
    private Account account;

    public User(String username, String password, Account account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public Account getAccount() {
        return account;
    }

    public String toDataString() {
        return username + "," + password + "," + account.toDataString();
    }

    public static User fromDataString(String data) {
        String[] parts = data.split(",");
        if (parts.length < 3) return null;
        String username = parts[0];
        String password = parts[1];
        Account account = Account.fromDataString(String.join(",", Arrays.copyOfRange(parts, 2, parts.length)));
        return new User(username, password, account);
    }
}
