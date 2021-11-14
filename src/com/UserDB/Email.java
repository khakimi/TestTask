package com.UserDB;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends Exception implements Serializable {
    public static long serialVersionUID = 1;
    private String email;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private boolean validation(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public Email(String email){
        if (validation(email)){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("Wrong email entry!");
        }
    }

    @Override
    public String toString() {
        return email;
    }
}
