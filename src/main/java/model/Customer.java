package model;
public class Customer {

    private String customerId;
    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private boolean employed;

    // constructor
    public Customer(String customerId, String firstName,
                    String surname, String address,
                    String phoneNumber, String email) {

        this(customerId, firstName, surname, address,
             phoneNumber, email, false);
    }

    // Constructor with employment information
    public Customer(String customerId, String firstName,
                    String surname, String address,
                    String phoneNumber, String email,
                    boolean employed) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employed = employed;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmployed() {
        return employed;
    }

    public String getFullName() {
        return firstName + " " + surname;
    }
}
