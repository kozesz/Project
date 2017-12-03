package ro.sci.cinema.domain;

import org.springframework.util.StringUtils;
import ro.sci.cinema.service.ValidationException;

import java.util.*;

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

    public void validate(Client client) throws ValidationException {
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(client.getFirstName())) {
            errors.add("First Name is Empty");
        }

        if (StringUtils.isEmpty(client.getLastName())) {
            errors.add("Last Name is Empty");
        }
        if (StringUtils.isEmpty(client.getPhoneNumber())) {
            errors.add("Phone number is Empty");
        }
        if (StringUtils.isEmpty(client.getEmail())) {
            errors.add("E-mail is Empty");
        }


        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nClient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
