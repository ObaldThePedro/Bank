
package bank;

public class Account {
    private String firstname;
    private String surname;
    private String password;
    private double deposit;
    private double balance;
    private double withdrawal;
    private int accountID;
    private static int nextaccountID;

    static {
        Account.nextaccountID = 1000000;
    }

    public Account(final String firstname, final String surname, final String password, final double deposit) {
        this.accountID = 0;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.deposit = deposit;
        this.balance += deposit;
        this.accountID = Account.nextaccountID;
        ++Account.nextaccountID;
    }

    public String getFullName() {
        return String.valueOf(this.firstname) + " " + this.surname;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public double getAccountID() {
        return this.accountID;
    }

    public void setName(final String firstname, final String lastname) {
        this.firstname = firstname;
        this.surname = lastname;
    }

    public double getWithdrawal() {
        return this.withdrawal;
    }

    public void setWithdrawal(final double withdrawal) {
        this.withdrawal = withdrawal;
        this.balance -= withdrawal;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public double getDeposit() {
        return this.deposit;
    }

    public void setDeposit(final double deposit) {
        this.deposit = deposit;
        this.balance += deposit;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public String toString() {
        return "Firstname: " + this.firstname + "\n" + "Surname: " + this.surname + "\n" + "Account ID: "
                + this.accountID + "\n";
    }
}
