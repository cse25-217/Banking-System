
public interface IBankService {

    void openAccount(Account account);

    void deposit(String accountNumber, double amount);

    void withdraw(String accountNumber, double amount);

    void transfer(String sourceAccountNumber,
                  String destinationAccountNumber,
                  double amount);

    void payMonthlyInterest();

    Account findAccount(String accountNumber);
}
