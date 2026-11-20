package model;
import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private double amount;
    private String transactionType;
    private String description;
    private LocalDateTime transactionDate;

    public Transaction(String transactionId, double amount,
                       String transactionType, String description) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Transaction amount must be greater than zero."
            );
        }

        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: BWP " + amount);
        System.out.println("Type: " + transactionType);
        System.out.println("Description: " + description);
        System.out.println("Date: " + transactionDate);
    }
}
