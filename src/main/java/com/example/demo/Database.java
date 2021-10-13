package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public final class Database {

    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/Hellios";
    private static final String username = "postgres";
    private static final String password = "root";
    private static Connection connection;
    private static String USER_INSERT_QUERY = "INSERT INTO Users (login, password) VALUES (?,?)";
    private static String ORDERS_INSERT_QUERY ="INSERT INTO orders (user_id, photograph_name) VALUES (?,?)";
    private static String USER_SELECT_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static String PHOTOGRAPH_SELECT_QUERY = "SELECT * FROM photographs";
    private static String ORDERS_SELECT_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    public static Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = DriverManager.getConnection(jdbcURL, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void userInsert(String login, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT_QUERY)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isLoggedIn(String login, String password) throws SQLException {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_QUERY)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User.setLogin(resultSet.getString("login"));
                User.setId(resultSet.getInt("id"));
                preparedStatement.close();
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Photograph> getPhotographs() {
        List<Photograph> result=new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PHOTOGRAPH_SELECT_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
                if(Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                        result.add(new Photograph(resultSet.getString("photograph_name"),
                                resultSet.getString("description")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }

    public static List<Order> getOrders() {
        List<Order> result=new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_SELECT_QUERY)) {
            preparedStatement.setInt(1,User.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                    result.add(new Order(resultSet.getString("photograph_name"), resultSet.getBoolean("status")));
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }

    public static void insertOrder(String photographname) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_INSERT_QUERY)) {
            preparedStatement.setInt(1,User.getId());
            preparedStatement.setString(2,photographname);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Connection connection = connect();
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("INSERT INTO orders (user_id, photograph_name) VALUES ('" + User.getId() + "','" + photographname + "')");
//        connection.close();
    }

}
