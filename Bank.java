import java.io.*;
import java.util.HashMap;
import java.util.Map;

class Bank {
    private Map<String, User> users;
    private static final String DATABASE_FILE = "database.txt";

    public Bank() {
        users = new HashMap<>();
        loadDatabase();
    }

    public void registerUser(String username, String password, Account account) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, account));
            saveDatabase();
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public void printAccounts() {
        for (User user : users.values()) {
            System.out.println(user.getAccount());
        }
    }

    public void saveDatabase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE))) {
            for (User user : users.values()) {
                writer.write(user.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromDataString(line);
                if (user != null) {
                    users.put(user.getUsername(), user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
