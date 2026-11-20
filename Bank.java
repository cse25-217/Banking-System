
import java.util.ArrayList;
import java.util.List;

public class Bank implements IBankService {

    private final List<Account> accounts;
    private final List<Transaction> transactions;

    public Bank() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    @Override
    public void openAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException(
                "Account cannot be empty."
            );
        }

        if (findAccount(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException(
                "Account number already exists."
            );
        }

        accounts.add(account);

        System.out.println(
            account.getAccountType() + " opened successfully."
        );
    }

    @Override
    public void deposit(String accountNumber, double amount) {

        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                "Account not found."
            );
        }

        account.deposit(amount);

        transactions.add(new Transaction(
            generateTransactionId(), amount,
            "DEPOSIT", "Money deposited"
        ));

        System.out.println("Deposit successful.");
    }

    @Override
    public void withdraw(String accountNumber, double amount) {

        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                "Account not found."
            );
        }

        if (account instanceof SavingsAccount) {
            throw new IllegalArgumentException(
                "Withdrawals are not allowed on Savings Accounts."
            );
        }

        switch (account) {
            case InvestmentAccount investmentAccount -> investmentAccount.withdraw(amount);
            case ChequeAccount chequeAccount -> chequeAccount.withdraw(amount);
            default -> {
            }
        }

        transactions.add(new Transaction(
            generateTransactionId(), amount,
            "WITHDRAWAL", "Money withdrawn"
        ));

        System.out.println("Withdrawal successful.");
    }

    @Override
    public void transfer(String sourceAccountNumber,
                         String destinationAccountNumber,
                         double amount) {

        Account source = findAccount(sourceAccountNumber);
        Account destination = findAccount(destinationAccountNumber);

        if (source == null || destination == null) {
            throw new IllegalArgumentException(
                "One or both accounts were not found."
            );
        }

        if (source instanceof SavingsAccount) {
            throw new IllegalArgumentException(
                "Savings Accounts cannot make transfers."
            );
        }

        if (destination instanceof SavingsAccount) {
            throw new IllegalArgumentException(
                "Savings Accounts cannot receive transfers through this service."
            );
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Transfer amount must be greater than zero."
            );
        }

        sourceWithdraw(source, amount);
        destination.deposit(amount);

        transactions.add(new Transfer(
            generateTransactionId(), amount,
            "Account transfer",
            sourceAccountNumber, destinationAccountNumber
        ));

        System.out.println("Transfer successful.");
    }

    private void sourceWithdraw(Account account, double amount) {

        switch (account) {
            case InvestmentAccount investmentAccount -> investmentAccount.withdraw(amount);
            case ChequeAccount chequeAccount -> chequeAccount.withdraw(amount);
            default -> {
            }
        }
    }

    @Override
    public void payMonthlyInterest() {

        for (Account account : accounts) {

            double interest = account.calculateInterest();

            if (interest > 0) {
                account.deposit(interest);

                transactions.add(new Transaction(
                    generateTransactionId(), interest,
                    "INTEREST", "Monthly interest payment"
                ));
            }
        }

        System.out.println("Monthly interest processing completed.");
    }

    @Override
    public Account findAccount(String accountNumber) {

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    private String generateTransactionId() {
        return "TXN" + (transactions.size() + 1);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
