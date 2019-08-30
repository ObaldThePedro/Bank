
package bank;

import java.util.Scanner;
import java.util.ArrayList;

public class ATM {
    static ArrayList<Account> accounts;
    static Account account;
    static Scanner reader;
    static boolean isGoodbye;
    static boolean isinputValid;

    static {
        ATM.accounts = new ArrayList<Account>();
        ATM.reader = new Scanner(System.in);
    }

    public static void displayMenu() {
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println(" WELCOME TO THE WHITE BANK ");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------\n");
    }

    public static void displayOptions() {
        int choice = 0;
        System.out.println("Please select one of the following options");
        System.out.println("1) Create an account");
        System.out.println("2) Login into existing account");
        System.out.println("3) Quit");
        try {
            choice = Integer.parseInt(ATM.reader.next());
            if (choice > 3 || choice < 1) {
                System.out.println("Please enter a number in bounds!\n");
            }
        } catch (Exception e) {
            System.out.println("Error: Wrong input\n");
        }
        switch (choice) {
        case 1: {
            ATM.accounts.add(createAccount());
            break;
        }
        case 2: {
            login();
            break;
        }
        case 3: {
            goodbye();
            break;
        }
        }
        System.out.println();
    }

    public static void login() {
        int found_at_index = -1;
        Account a1 = null;
        if (ATM.accounts.isEmpty()) {
            System.out.println("I am sorry but we don't have any accounts created yet!");
            System.out.println("Would you like to open an account?Y\\N");
            final String answer = ATM.reader.next();
            if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
                ATM.accounts.add(createAccount());
            } else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
                goodbye();
            }
        } else {
            System.out.print("Please enter your firstname: ");
            final String firstname = ATM.reader.next();
            System.out.print("Please enter your password: ");
            final String password = ATM.reader.next();
            for (int i = 0; i < ATM.accounts.size(); ++i) {
                a1 = ATM.accounts.get(i);
                if (a1.getFirstName().equals(firstname) && a1.getPassword().equals(password)) {
                    found_at_index = i;
                    break;
                }
            }
            if (found_at_index >= 0) {
                System.out.println("Welcome " + a1.getFullName());
                displayOptions2(a1);
            } else {
                System.out.println("Sorry,account not found!");
            }
        }
    }

    public static void displayOptions2(final Account userAccount) {
        int secondchoice = 0;
        do {
            System.out.println("Please select one of the following options: ");
            System.out.println("1) Print account details");
            System.out.println("2) Withdraw money");
            System.out.println("3) Deposit money");
            System.out.println("4) Logout");
            try {
                secondchoice = Integer.parseInt(ATM.reader.next());
                if (secondchoice > 4 || secondchoice < 1) {
                    System.out.println("Please enter a number in bounds!\n");
                }
            } catch (Exception e) {
                System.out.println("Error: Wrong input\n");
            }
            switch (secondchoice) {
            default: {
                continue;
            }
            case 1: {
                printDetails(userAccount);
                continue;
            }
            case 2: {
                withdrawMoney(userAccount);
                continue;
            }
            case 3: {
                depositMoney(userAccount);
                continue;
            }
            case 4: {
                goodbye(userAccount);
                displayMenu();
                displayOptions();
                continue;
            }
            }
        } while (secondchoice != 4);
    }

    public static void withdrawMoney(final Account userAccount) {
        final double accountholderBalance = userAccount.getBalance();
        double withdrawalAmount = 0.0;
        do {
            System.out.print("Please enter the amount to withdraw from your account: ");
            try {
                withdrawalAmount = Double.parseDouble(ATM.reader.next());
                ATM.isinputValid = true;
            } catch (Exception e) {
                System.out.println("Wrong input!");
                ATM.isinputValid = false;
            }
        } while (!ATM.isinputValid);
        if (withdrawalAmount > accountholderBalance) {
            System.out.println("I am sorry but you don't have enough funds in your account to make this transaction!");
        } else {
            userAccount.setWithdrawal(withdrawalAmount);
            System.out.println("You have withdrawn: " + userAccount.getWithdrawal());
            System.out.println("Your balance now is: " + userAccount.getBalance());
        }
    }

    public static void depositMoney(final Account userAccount) {
        double depositAmount = 0.0;
        do {
            System.out.print("Please enter the amount to deposit into your account: ");
            try {
                depositAmount = Double.parseDouble(ATM.reader.next());
                ATM.isinputValid = true;
            } catch (Exception e) {
                System.out.println("Wrong input!");
                ATM.isinputValid = false;
            }
        } while (!ATM.isinputValid);
        userAccount.setDeposit(depositAmount);
        System.out.println("You deposited: " + userAccount.getDeposit());
        System.out.println("Your balance now is: " + userAccount.getBalance());
    }

    public static Account createAccount() {
        System.out.print("Please enter your firstname: ");
        final String firstname = ATM.reader.next();
        System.out.print("Please enter your surname: ");
        final String surname = ATM.reader.next();
        boolean isValidPassword;
        String password;
        do {
            System.out.print("Please enter a password: ");
            password = ATM.reader.next();
            System.out.print("Please confirm your password: ");
            final String password_confirmation = ATM.reader.next();
            if (password.equals(password_confirmation)) {
                isValidPassword = true;
            } else {
                System.out.println("Acess denied, make sure that your password is matching!");
                isValidPassword = false;
            }
        } while (!isValidPassword);
        System.out.println("Now you need to deposit a minimum of 100£ pounds to open this account");
        System.out.print("Enter the amount you want to deposit: ");
        double deposit = ATM.reader.nextDouble();
        boolean isValidDeposit;
        do {
            if (deposit < 100.0) {
                System.out.println("I am sorry, but we can't open an account unless you deposit 100£!");
                isValidDeposit = false;
                System.out.print("Enter the amount you want to deposit: ");
                deposit = ATM.reader.nextDouble();
            } else {
                System.out.println("Account successfully created, thank you!");
                isValidDeposit = true;
            }
        } while (!isValidDeposit);
        return ATM.account = new Account(firstname, surname, password, deposit);
    }

    public static void goodbye(final Account userAccount) {
        System.out.println("Goodbye dear " + userAccount.getFullName() + ", we hope to see you very soon!");
    }

    public static void goodbye() {
        System.out.println("Goodbye dear user, we hope to see you very soon!");
        ATM.isGoodbye = true;
    }

    public static void printDetails(final Account userAccount) {
        System.out.println("Firstname: " + userAccount.getFirstName());
        System.out.println("Surname: " + userAccount.getSurname());
        System.out.println("Account Balance: " + userAccount.getBalance());
        System.out.println("Account ID: " + userAccount.getAccountID() + "\n");
    }

    public static void main(final String[] args) {
        displayMenu();
        do {
            displayOptions();
        } while (!ATM.isGoodbye);
        if (!ATM.accounts.isEmpty()) {
            System.out.println("Here is the list of clients in this bank: ");
            for (int i = 0; i < ATM.accounts.size(); ++i) {
                final Account account_in_bank = ATM.accounts.get(i);
                System.out.println(account_in_bank.toString());
            }
        }
    }
}
