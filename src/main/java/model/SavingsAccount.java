package model;
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.0005;

    public SavingsAccount(String accountNumber, double balance,
                          String branch, Customer customer) {
        super(accountNumber, balance, branch, customer);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }

    public void withdraw(double amount) {
        throw new UnsupportedOperationException(
            "Withdrawals are not allowed on a Savings Account."
        );
    }
}
