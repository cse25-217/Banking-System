package model;
import java.time.LocalDate;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String branch;
    private LocalDate openingDate;
    private Customer customer;

    public Account(String accountNumber, double balance,
                   String branch, Customer customer) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                "Opening balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.customer = customer;
        this.openingDate = LocalDate.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getBranch() {
        return branch;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be greater than zero."
            );
        }

        balance += amount;
    }

    protected void deductBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new IllegalArgumentException(
                "Insufficient funds."
            );
        }

        balance -= amount;
    }

    public abstract String getAccountType();

    public abstract double calculateInterest();
}
