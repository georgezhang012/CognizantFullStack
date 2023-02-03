package com.cognizant.hotel.model;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class Customer {
    String firstName, lastName, emailAccount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    @Override
    public String toString() {
        return "first name: " + firstName +"last name: " + lastName + "email account: " + emailAccount;
    }

    public Customer(String firstName, String lastName, String emailAccount) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.emailAccount=emailAccount;
        validateEmailAccount(emailAccount);
    }

    public void  validateEmailAccount(String email) {
        String regexPattern =  "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";

        if(!Pattern.compile(regexPattern).matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email account, please provide a valid one");
        }

    }
}
