package ro.sci.cinema.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.StringUtils;
import ro.sci.cinema.service.ValidationException;

import java.util.*;

public class Client extends AbstractModel {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @NumberFormat(pattern = "^[0-9]{10}$")
    private String phoneNumber;

    @NotEmpty
    @Email
    private String email;

    /*public Client(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }*/

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
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(client.phoneNumber) : client.phoneNumber != null) return false;
        return email != null ? email.equals(client.email) : client.email == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
