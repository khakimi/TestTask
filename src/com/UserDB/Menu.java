package com.UserDB;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private String menuInterface;
    private HashMap<Integer, User> userList;
    private Scanner scanner;
    private String input;


    public Menu() {
        scanner = new Scanner(System.in);
        userList = new HashMap<>();
        menuInterface = '\n' + " ".repeat(17) + "Menu" + '\n' + "-".repeat(38)
                + " ".repeat(17) + '\n' +
                '\t' + "1. Print list of users\n" +
                '\t' + "2. Import list of users from file\n" +
                '\t' + "3. Save list of users to file\n" +
                '\t' + "4. Add user into list\n" +
                '\t' + "5. Edit user\n" +
                '\t' + "6. Exit\n";
    }

    public void printMenu() {
        System.out.println(menuInterface);
    }

    public void menuAction() throws FileNotFoundException {
        while (true) {
            printMenu();
             input = scanner.next();
            switch (input) {
                case "1":
                    printUsers();
                    break;
                case "2":
                    loadFromFile();
                    break;
                case "3":
                    saveToFile();
                    break;
                case "4":
                    inputUser();
                    break;
                case "5":
                    System.out.println("\tChoose user number from list: ");
                    printUsers();
                    if (!userList.isEmpty()) {
                        Integer temp = scanner.nextInt();
                        while (true) {
                            if (temp < 0 || temp > userList.size()) {
                                System.out.println("\tThere is no such user! Try Again");
                                temp = scanner.nextInt();
                            } else
                                break;
                        }
                        editUser(temp);
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("\tInvalid input! Try again");
            }
        }
    }

    public void printUsers() {
        if (userList.size() != 0) {
            for (HashMap.Entry entry : userList.entrySet()) {
                System.out.println(entry.getKey() + ". "
                        + entry.getValue());
            }
        } else
            System.out.println("\tThere is no user in list!\n");
    }

    public void loadFromFile() throws FileNotFoundException {
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("usersData.bin"))) {
            userList = (HashMap<Integer, User>) inputStream.readObject();
            System.out.println("\tUsers data was successfully imported! ");
            printUsers();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveToFile() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("usersData.bin"))){
            outputStream.writeObject(userList);
            System.out.println("\tUsers data was successfully exported! ");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void addUser(User user){
        userList.put(userList.size(), user);
    }

    public void inputUser() {
        User user = new User();
        System.out.println("Enter user's name: ");
        nameInput(user);
        System.out.println("Enter user's surname: ");
        surnameInput(user);
        System.out.println("Enter user's e-mail: ");
        emailInput(user);
        System.out.println("Is the user a SUPER_ADMIN? (t, f): ");
        superAdminInput(user);
        System.out.println("Enter user's level1 role (USER, CUSTOMER, NONE): ");
        roleLevel1Input(user);
        System.out.println("Enter user's level2 role (ADMIN, PROVIDER, NONE): ");
        roleLevel2Input(user);
        System.out.println("Enter user's telephone number: ");
        telephoneInput(user);
        addUser(user);
        System.out.println("\tHere is what you entered - " + user + '\n');
    }





    public void editUser(int n) {
        User tempUser = userList.get(n);
        while (true) {
            System.out.println("\tWhat do you want to edit?\n" +
                    '\t' + "1. Name\n" +
                    '\t' + "2. Surname\n" +
                    '\t' + "3. E-mail\n" +
                    '\t' + "4. Super_Admin status\n" +
                    '\t' + "5. Level1 role\n" +
                    '\t' + "6. Level2 role\n" +
                    '\t' + "7. Telephone number\n" +
                    '\t' + "8. Back to menu\n");
            String temp = scanner.next();
            switch (temp) {
                case "1":
                    System.out.println("\tEnter user's new name: ");
                    nameInput(tempUser);
                    break;
                case "2":
                    System.out.println("\tEnter user's new surname: ");
                    surnameInput(tempUser);
                    break;
                case "3":
                    System.out.println("\tEnter user's new e-mail: ");
                    emailInput(tempUser);
                    break;
                case "4":
                    System.out.println("\tEnter user's new Super_Admin status (t, f): ");
                    superAdminInput(tempUser);
                    break;
                case "5":
                    if (tempUser.isSuperAdmin())
                        System.out.println("\tThe user is Super_Admin, you can't set one more role for him. You need to change Super_Admin status!");
                    else {
                        System.out.println("\tEnter user's new role of level1(USER, CUSTOMER, NONE) : ");
                        roleLevel1Input(tempUser);
                    }
                    break;
                case "6":
                    if (tempUser.isSuperAdmin())
                        System.out.println("\tThe user is Super_Admin, you can't set one more role for him. You need to change Super_Admin status!");
                    else {
                        System.out.println("\tEnter user's new role of level2(ADMIN, PROVIDER, NONE) : ");
                        roleLevel2Input(tempUser);
                    }
                    break;
                case "7":
                    System.out.println("\tEnter user's new telephone number: ");
                    telephoneInput(tempUser);
                    break;
                case "8":
                    return;
                default:
                    System.out.println("\tInvalid input! Try again");
            }
            System.out.println("\tHere is what you changed - " + tempUser + '\n');
        }

    }

    private void nameInput(User user){
        String newName = scanner.next();
        user.setName(newName);
    }


    private void surnameInput(User user){
        String newName = scanner.next();
        user.setSurname(newName);
    }

    private void emailInput(User user){
        while (true) {
            try {
                String newEmail = scanner.next();
                user.setEmail(newEmail);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("\tTry again");
            }
        }
    }

    private void superAdminInput(User user){
        while (true) {
            String answer = scanner.next();
            if (answer.equals("t") || answer.equals("f")) {
                if (answer.equals("t")) {
                    user.setSuperAdmin(true);
                } else {
                    user.setSuperAdmin(false);
                }
                break;
            } else
                System.out.println("\tInvalid input! Try again");
        }
    }

    private void roleLevel1Input(User user){
        while (true) {
            try {
                String newRole = scanner.next();
                user.setLv1(Role.Level1.valueOf(newRole));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("\tInvalid input! Try again");
            }
        }
    }

    private void roleLevel2Input(User user){
        while (true) {
            try {
                String newRole = scanner.next();
                user.setLv2(Role.Level2.valueOf(newRole));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("\tInvalid input! Try again");
            }
        }
    }

    private void telephoneInput(User user){
        while (true) {
            try {
                String newTelephone = scanner.next();
                user.setTelephone(newTelephone);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("\tTry again");
            }
        }
    }
}
