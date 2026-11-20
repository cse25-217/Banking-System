package model;
public class ChequeAccount extends Account {

    private String employerName;
    private String employerAddress;

    public ChequeAccount(String accountNumber, double balance,
                         String branch, Customer customer,
                         String employerName, String employerAddress) {

        super(accountNumber, balance, branch, customer);

        if (!customer.isEmployed()) {
            throw new IllegalArgumentException(
                "Only employed customers can open a Cheque Account."
            );
        }

        if (employerName == null || employerName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Employer name is required."
            );
        }

        if (employerAddress == null || employerAddress.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Employer address is required."
            );
        }

        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    public String getEmployerName() {
        return employerName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    @Override
    public String getAccountType() {
        return "Cheque Account";
    }

    @Override
    public double calculateInterest() {
        return 0.0;
    }

    public void withdraw(double amount) {
        deductBalance(amount);
    }
}
