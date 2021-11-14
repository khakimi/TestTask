package com.UserDB;

import java.io.Serializable;

public class User implements Serializable {
    public static long serialVersionUID = 1;
    private String name;
    private String surname;
    private Email email;
    private Role.Level1 lv1;
    private Role.Level2 lv2;
    private boolean superAdmin;
    private Telephone telephone;


    public User(String name, String surname, String email,
                Role.Level1 lv1, Role.Level2 lv2, boolean superAdmin, String telephone) {
        this.name = name;
        this.surname = surname;
        this.email = new Email(email);
        if (superAdmin != true) {
            this.lv1 = lv1;
            this.lv2 = lv2;
        }
        this.superAdmin = superAdmin;
        this.telephone = new Telephone(telephone);
    }

    public User(){
        lv1 = Role.Level1.NONE;
        lv2 = Role.Level2.NONE;
        superAdmin = false;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Email getEmail() {
        return email;
    }

    public Role.Level1 getLv1() {
        return lv1;
    }

    public Role.Level2 getLv2() {
        return lv2;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }


    public Telephone getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public void setLv1(Role.Level1 lv1) {
        this.lv1 = lv1;
    }

    public void setLv2(Role.Level2 lv2) {
        this.lv2 = lv2;
    }

    public void setSuperAdmin(boolean superAdmin) {
        if (superAdmin == true) {
            lv1 = Role.Level1.NONE;
            lv2 = Role.Level2.NONE;
        }
        this.superAdmin = superAdmin;
    }

    public void setTelephone(String telephone) {
        this.telephone = new Telephone(telephone);
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Surname: " + surname +
                " | E-mail: " + email + " | Roles: " +
                ((lv1 != Role.Level1.NONE) ? "Level 1: " + lv1 + " " : "") +
                ((lv2 != Role.Level2.NONE) ? "Level 2: " + lv2 + " " : "") +
                ((superAdmin) ? "SUPER_ADMIN" : "") +
                " | Telephone number: " + telephone;
    }
}
