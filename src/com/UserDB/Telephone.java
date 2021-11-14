package com.UserDB;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telephone extends Exception implements Serializable {
    public static long serialVersionUID = 1;
    private String telephone;
    private static final String TELEPHONE_PATTERN =
            "^375\\d{9}$";

    public boolean validation(String telephone) {
        Pattern pattern = Pattern.compile(TELEPHONE_PATTERN);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }

    public Telephone(String telephone) {
        if (validation(telephone)) {
            this.telephone = telephone;
        } else {
            throw new IllegalArgumentException("Wrong telephone number entry!");
        }
    }

    @Override
    public String toString() {
        return telephone;
    }

}
