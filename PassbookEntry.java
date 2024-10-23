public class PassbookEntry {
    private String date;
    private String description;
    private double amount;
    private double balance;

    public PassbookEntry(String date, String description, double amount, double balance) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return date + " | " + description + " | " + amount + " | " + balance;
    }

    public String toDataString() {
        return date + "," + description + "," + amount + "," + balance;
    }

    public static PassbookEntry fromDataString(String data) {
        String[] parts = data.split(",");
        if (parts.length < 4) return null;
        String date = parts[0];
        String description = parts[1];
        double amount = Double.parseDouble(parts[2]);
        double balance = Double.parseDouble(parts[3]);
        return new PassbookEntry(date, description, amount, balance);
    }
}
