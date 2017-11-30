package ro.sci.cinema.domain;

public class Client {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;


    public Client(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
