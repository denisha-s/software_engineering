/*
 * Denisha Saviela
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Custom exception class for negative balance
public class NegativeBalanceException extends Exception {
    private double negativeBalance;

    // Default constructor with generic error message
    public NegativeBalanceException() {
        super("Error: negative balance");
    }

    // Constructor with message including negative balance and writing to log file
    public NegativeBalanceException(double negativeBalance) {
        super("Amount exceeds balance by " + negativeBalance);
        this.negativeBalance = negativeBalance;
        try (PrintWriter writer = new PrintWriter(new FileWriter("logfile.txt", true))) {
            writer.println("Amount exceeds balance by " + negativeBalance);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // toString method to return a user-friendly message
    @Override
    public String toString() {
        return "Balance of " + negativeBalance + " not allowed";
    }
}