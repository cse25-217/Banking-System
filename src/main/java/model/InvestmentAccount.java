package model;
public class InvestmentAccount extends Account {

    private static final double INTEREST_RATE = 0.05;
    private static final double MINIMUM_DEPOSIT = 500.00;

    public InvestmentAccount(String accountNumber, double balance,
                             String branch, Customer customer) {

        super(accountNumber, balance, branch, customer);

        if (balance < MINIMUM_DEPOSIT) {
            throw new IllegalArgumentException(
                "Investment account requires a minimum opening deposit of BWP 500."
            );
        }
    }

    @Override
    public String getAccountType() {
        return "Investment Account";
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }

    public void withdraw(double amount) {
        deductBalance(amount);
    }
}
