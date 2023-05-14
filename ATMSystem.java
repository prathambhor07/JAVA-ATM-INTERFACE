import java.util.ArrayList;
import java.util.Scanner;
public class ATMSystem {

    private static int attempts = 0;
    private static int balance = 10000;
    private static int pin = 1234;
    private static String transactionHistory = "";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to OASIS ATM");
        authenticateUser();
    }

    private static void authenticateUser() {
        System.out.println("Please enter your PIN:");
        int inputPin = sc.nextInt();

        //checking if pin is right
        if (inputPin == pin) {
            showOptionsMenu();
        } else {
            attempts++;
            if (attempts < 3) {
                System.out.println("Incorrect PIN, please enter correct pin.");
                authenticateUser();
            } else {
                System.out.println("Maximum input attempts reached. Your card has been blocked.");
            }
        }
    }

    private static void showOptionsMenu() {
        System.out.println("Please select an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. View Transaction History");
        System.out.println("5. Change PIN");
        System.out.println("6. Exit");

        int option = sc.nextInt();

        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                viewTransactionHistory();
                break;
            case 5:
                changePIN();
                break;
            case 6:
                System.out.println("Thank you for using OASIS ATM. Visit again.");
                break;
            default:
                System.out.println("Invalid option, please try again.");
                showOptionsMenu();
                break;
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: " + balance);
        transactionHistory += "Balance check: " + balance + "\n";
        System.out.println("-------------------------------------------------------------------");
        showOptionsMenu();
    }

    private static void withdraw() {
        System.out.println("Enter amount to withdraw:");
        int amount = sc.nextInt();


        System.out.println("Please enter your PIN to confirm the transaction:");
        int inputPin = sc.nextInt();


        if (inputPin == pin) {
            if (amount > balance) {
                System.out.println("Insufficient balance in your account.");
            } else {
                balance -= amount;
                System.out.println("Withdrawal successful.");
                transactionHistory += "Withdrawal: $" + amount + "\n";
            }
        } else {
            System.out.println("Incorrect PIN, transaction cancelled.");
            System.out.println("Please try again.");
        }
        System.out.println("-------------------------------------------------------------------");
        showOptionsMenu();
    }

    private static void deposit() {
        System.out.println("Enter amount you want to deposit:");
        int amount = sc.nextInt();
        sc.nextLine(); // consume the leftover newline character

        System.out.println("Please enter your PIN to confirm the transaction:");
        int inputPin = sc.nextInt();


        if (inputPin == pin) {
            balance += amount;
            System.out.println("Deposit successful.");
            transactionHistory += "Deposit: $" + amount + "\n";
        } else {
            System.out.println("Incorrect PIN, transaction cancelled.");
            System.out.println("Please try again.");
        }
        System.out.println("-------------------------------------------------------------------");
        showOptionsMenu();
    }

    private static void viewTransactionHistory() {
        System.out.println("Transaction History:\n" + transactionHistory);
        System.out.println("-------------------------------------------------------------------");
        showOptionsMenu();
    }

    private static void changePIN() {
        System.out.println("Enter your new PIN (must be 4 digits with no spaces):");
        String newPin = sc.nextLine();

        if (newPin.length() != 4 || newPin.contains(" ")) {
            System.out.println("Your entered pin is not of appropriate format, please try again.");
            changePIN();
        } else {
            pin = Integer.parseInt(newPin);
            System.out.println("PIN changed successfully.");
            System.out.println("-------------------------------------------------------------------");
            showOptionsMenu();
        }
    }

}
