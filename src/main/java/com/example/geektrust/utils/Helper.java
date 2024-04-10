package com.example.geektrust.utils;

import java.util.regex.Pattern;

public class Helper {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String REJECTED = "REJECTED";
    public static final String ACCEPTED = "ACCEPTED";
    public static final String CANCEL_REJECTED = "CANCEL_REJECTED";
    public static final String CANCEL_ACCEPTED = "CANCEL_ACCEPTED";

    public static String getCommand(String input) {
        String command = "";
        switch (input) {
            case "ADD-COURSE-OFFERING":
                command= "ADD_COURSE_OFFERING";
                break;

            case "ALLOT-COURSE":
                command = "ALLOT_COURSE";
                break;

            default:
                command = input;
                break;
        }
        return command;
    }
}
