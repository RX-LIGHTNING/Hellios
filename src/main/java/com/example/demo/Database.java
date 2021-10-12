package com.example.demo;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public final class Database {

   private static String jdbcURL = "jdbc:postgresql://localhost:5432/Hellios";
   private static String username = "postgres";
   private static String password = "root";
    private static Connection connect() throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection closed, please check your internet connection");
        }
        return connection;
    }
    public static void userInsert(String login, String password) throws SQLException {
        Connection connection = connect();
        Statement statement = connection.createStatement();
        if(!isLoggedIn(login,password)) {
            statement.executeUpdate("INSERT INTO Users (login, password) VALUES ('" + login + "','" + password + "')");
        }
        else System.out.println("Пользователь уже существует");
        connection.close();
    }
    public static boolean isLoggedIn(String login, String password) throws SQLException{
        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM users WHERE login = '"+login+"' AND password = '"+password+"' ");
        int quantity=0;
        while(result.next()) {
            quantity++;
        }
        connection.close();
        if(quantity==1){
            User.Login = login;
            return true;
        }
        else{
            return false;
        }

    }
    public static ResultSet getPhotographs(){
        ResultSet result = null;
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM photographs");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static ResultSet getOrders(){
        ResultSet result = null;
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM orders WHERE userlogin = '"+User.getLogin()+"'" );
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static void insertOrder (String userlogin, String photographname) throws SQLException {
        Connection connection = connect();
        Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO orders (userlogin, photograph) VALUES ('" + userlogin + "','" + photographname + "')");
        connection.close();
    }

}
