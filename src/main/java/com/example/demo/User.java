package com.example.demo;

public final class User {
    public static String Login;
    public static Integer id;

    public static String getLogin() {
        return Login;
    }

    public static Integer getId() {
        return id;
    }

    public static void setLogin(String login) {
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
