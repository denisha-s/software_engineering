// BankAccount class with withdraw and quickWithdraw methods
public class BankAccount {
    private double balance;

    // Constructor to set initial balance
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Withdraw method that throws NegativeBalanceException if balance is too low
    public void withdraw(double amount) throws NegativeBalanceException {
        if (amount > balance) {
            throw new NegativeBalanceException(amount - balance);
        }
        balance -= amount;
    }

    // Quick withdraw method that throws NegativeBalanceException if balance is too low
    public void quickWithdraw(double amount) throws NegativeBalanceException {
        if (amount > balance) {
            throw new NegativeBalanceException();
        }
        balance -= amount;
    }

    // Getter method for balance
    public double getBalance() {
        return balance;
    }
}