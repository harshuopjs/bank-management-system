import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Bank Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Accounts");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    System.out.print("Enter account holder name: ");
                    String name = scanner.next();
                    System.out.print("Enter initial deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    Account account = new Account(name, initialDeposit);
                    bank.registerUser(username, password, account);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.next();
                    System.out.print("Enter password: ");
                    password = scanner.next();
                    User user = bank.loginUser(username, password);
                    if (user != null) {
                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. View Passbook");
                            System.out.println("4. Logout");
                            System.out.print("Enter choice: ");
                            int userChoice = scanner.nextInt();

                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter deposit amount: ");
                                    double deposit = scanner.nextDouble();
                                    user.getAccount().deposit(deposit);
                                    bank.saveDatabase();
                                    break;
                                case 2:
                                    System.out.print("Enter withdraw amount: ");
                                    double withdraw = scanner.nextDouble();
                                    user.getAccount().withdraw(withdraw);
                                    bank.saveDatabase();
                                    break;
                                case 3:
                                    user.getAccount().printPassbook();
                                    break;
                                case 4:
                                    loggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }
                    }
                    break;
                case 3:
                    bank.printAccounts();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
