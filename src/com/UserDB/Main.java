package com.UserDB;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        menu.menuAction();
        try {
            Telephone tel = new Telephone("375001234567");
            Email email = new Email("jjer1e@jjge.grf");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
