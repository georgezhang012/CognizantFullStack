package model;

import java.util.regex.Pattern;

public class Customer {

    String firstName, lastName, email;

    @Override
    public String toString() {
        return "First name: " + this.firstName + " Last name: " + this.lastName + " Email: " + this.email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        validateEmail(email);
    }

    public static boolean validateEmail(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        if(!Pattern.compile(regexPattern).matcher(emailAddress).matches()){
            throw new IllegalArgumentException("Invalid email format, please enter the right one");
        }

        return true;
    }

}
