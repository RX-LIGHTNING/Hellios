package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public final class DatabaseController {

    private static final String jdbcURL = "jdbc:postgresql://192.168.100.4:5432/Hellios";
    private static final String username = "postgres";
    private static final String password = "root";

    private static final String USER_INSERT_QUERY = "INSERT INTO Users (login, password) VALUES (?,?)";
    private static final String ORDERS_INSERT_QUERY ="INSERT INTO orders (user_id, photograph_name) VALUES (?,?)";
    private static final String USER_SELECT_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String USER_STATUS_SELECT_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String PHOTOGRAPH_SELECT_QUERY = "SELECT * FROM photographs";
    private static final String ORDERS_SELECT_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    private static final String ADMIN_ORDERS_SELECT_QUERY = "SELECT * FROM orders";
    private static final String ORDERS_UPDATE_QUERY = "UPDATE orders SET status = true WHERE id = ?";
    private static final String PHOTOGRAPH_UPDATE_QUERY = "UPDATE photographs SET photograph_name = ?, description = ? WHERE id = ?";
    private static final String PHOTOGRAPH_DELETE_QUERY = "DELETE FROM photographs WHERE id = ?";
    private static final String PHOTOGRAPH_INSERT_QUERY = "INSERT INTO photographs (photograph_name, description) VALUES (?,?)";
    private static Connection connection;

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
    public static boolean getUserStatus(String login){
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_STATUS_SELECT_QUERY)) {
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getBoolean("isadmin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
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
                                resultSet.getString("description"),resultSet.getInt("id")));
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
                preparedStatement.setInt(1, User.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                            result.add(new Order(resultSet.getString("photograph_name"), resultSet.getBoolean("status")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }
    public static List<Order> getAdminOrders() {
        List<Order> result = new ArrayList<>();
        if (DatabaseController.getUserStatus(User.getLogin())) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_ORDERS_SELECT_QUERY)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (Objects.nonNull(resultSet)) {
                        while (resultSet.next()) {
                            result.add(new Order(resultSet.getString("photograph_name"), resultSet.getBoolean("status"),resultSet.getInt("user_id"),resultSet.getInt("id")));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
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
    }
    public static void updateOrder(int id) {
        if (DatabaseController.getUserStatus(User.getLogin())) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_UPDATE_QUERY)) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void updatePhotograph(int id,String photograph, String description) {
        if (DatabaseController.getUserStatus(User.getLogin())) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PHOTOGRAPH_UPDATE_QUERY)) {
                preparedStatement.setString(1,photograph);
                preparedStatement.setString(2,description);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deletePhotograph(int id) {
        if (DatabaseController.getUserStatus(User.getLogin())) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PHOTOGRAPH_DELETE_QUERY)) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void insertPhotograph(String name, String Description) {
        if (DatabaseController.getUserStatus(User.getLogin())) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PHOTOGRAPH_INSERT_QUERY)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, Description);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
