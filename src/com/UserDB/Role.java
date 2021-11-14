package com.UserDB;

import java.io.Serializable;

public class Role implements Serializable {
    public static long serialVersionUID = 1;
    enum Level1 {NONE, USER, CUSTOMER}
    enum Level2{NONE, ADMIN, PROVIDER}
}
