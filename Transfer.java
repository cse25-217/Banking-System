
public class Transfer extends Transaction {

    private String sourceAccountNumber;
    private String destinationAccountNumber;

    public Transfer(String transactionId, double amount,
                    String description,
                    String sourceAccountNumber,
                    String destinationAccountNumber) {

        super(transactionId, amount, "TRANSFER", description);

        if (sourceAccountNumber == null || sourceAccountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Source account number is required."
            );
        }

        if (destinationAccountNumber == null || destinationAccountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Destination account number is required."
            );
        }

        if (sourceAccountNumber.equals(destinationAccountNumber)) {
            throw new IllegalArgumentException(
                "Cannot transfer to the same account."
            );
        }

        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }
}
