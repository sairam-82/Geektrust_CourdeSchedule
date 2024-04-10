package com.example.geektrust.entities;

import com.example.geektrust.exceptions.IncorrectInputException;
import com.example.geektrust.utils.Helper;

public class Employee {

    private final String name;
    private final String emailAddress;


    public Employee(String emailAddress) throws IncorrectInputException {
        if (Helper.VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress).matches()) {
            this.emailAddress = emailAddress;
            this.name = this.emailAddress.substring(0, this.emailAddress.indexOf('@'));
        } else {
            throw new IncorrectInputException("INPUT_DATA_ERROR");
        }
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

}
