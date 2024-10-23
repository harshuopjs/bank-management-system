import java.util.ArrayList;
import java.util.List;

class Account {
    private static int accountCounter = 1000;
    private int accountNumber;
    private String accountHolder;
    private double balance;
    private List<PassbookEntry> passbook;

    public Account(String accountHolder, double initialDeposit) {
        this.accountNumber = accountCounter++;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.passbook = new ArrayList<>();
        addPassbookEntry("Account opened", initialDeposit);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addPassbookEntry("Deposit", amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addPassbookEntry("Withdraw", -amount);
        } else {
            System.out.println("Invalid withdraw amount");
        }
    }

    private void addPassbookEntry(String description, double amount) {
        String date = java.time.LocalDate.now().toString();
        PassbookEntry entry = new PassbookEntry(date, description, amount, balance);
        passbook.add(entry);
    }

    public void printPassbook() {
        for (PassbookEntry entry : passbook) {
            System.out.println(entry);
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: " + balance;
    }

    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append(accountNumber).append(",").append(accountHolder).append(",").append(balance);
        for (PassbookEntry entry : passbook) {
            sb.append(";").append(entry.toDataString());
        }
        return sb.toString();
    }

    public static Account fromDataString(String data) {
        String[] parts = data.split(";");
        if (parts.length < 1) return null;
        String[] accountParts = parts[0].split(",");
        if (accountParts.length < 3) return null;
        int accountNumber = Integer.parseInt(accountParts[0]);
        String accountHolder = accountParts[1];
        double balance = Double.parseDouble(accountParts[2]);
        Account account = new Account(accountHolder, balance);
        account.accountNumber = accountNumber;
        for (int i = 1; i < parts.length; i++) {
            account.passbook.add(PassbookEntry.fromDataString(parts[i]));
        }
        return account;
    }
}
