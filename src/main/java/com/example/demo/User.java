package com.example.demo;

import java.sql.SQLException;

public final class User {
    public static String Login;
    public static Integer id;

    public static String getContact() {
        return Contact;
    }

    public static void setContact(String contact) {
        Contact = contact;
    }

    public static String Contact;
    public static String getLogin() {
        return Login;
    }
    public static boolean getStatus(){
        return DatabaseController.getUserStatus(getLogin());
    }
    public static Integer getId() {
        return id;
    }

    public static void setLogin(String login){
        Login = login;
    }

    public static void setId(Integer id) {
        User.id = id;
    }
    public static void clearUser() {
        User.id = null;
        User.Login = null;
    }
}
