// ATM class with handleTransactions method to test the BankAccount methods
public class ATM {
    private BankAccount account;

    // Constructor to create BankAccount with initial balance of $500
    public ATM() {
        account = new BankAccount(500);
    }

    // Method to test the BankAccount methods with try-catch blocks
    public void handleTransactions() {
        // Test withdraw method with amount $600
        try {
            account.withdraw(600);
        } catch (NegativeBalanceException e) {
            // Catch NegativeBalanceException and print message and stack trace
            System.out.println(e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // Test quickWithdraw method with amount $600
        try {
            account.quickWithdraw(600);
        } catch (NegativeBalanceException e) {
            // Catch NegativeBalanceException and print message and stack trace
            System.out.println(e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method to create an ATM object and call handleTransactions
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.handleTransactions();
    }
}