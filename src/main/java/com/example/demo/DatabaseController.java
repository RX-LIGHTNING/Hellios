package com.example.demo;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;



public final class DatabaseController {

    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/Hellios";
    private static final String username = "postgres";
    private static final String password = "root";

    private static final String USER_INSERT_QUERY = "INSERT INTO Users (login, password, contacts) VALUES (?,?,?)";
    private static final String ORDERS_INSERT_QUERY ="INSERT INTO orders (user_id, photograph_name,user_name,user_contact,order_date) VALUES (?,?,?,?,?)";
    private static final String USER_SELECT_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String IS_USER_EXIST_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String USER_STATUS_SELECT_QUERY = "SELECT * FROM users WHERE login = ?";
    private static final String USER_SELECT_INFO_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String USER_UPDATE_QUERY = "UPDATE users SET contacts = ? WHERE login = ?";
    private static final String PHOTOGRAPH_SELECT_QUERY = "SELECT * FROM photographs";
    private static final String ORDERS_SELECT_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    private static final String ADMIN_ORDERS_SELECT_QUERY = "SELECT * FROM orders";
    private static final String ORDERS_UPDATE_QUERY = "UPDATE orders SET status = 1 WHERE id = ?";
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
    public static void userContactUpdate(String Contact){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE_QUERY)) {
            preparedStatement.setString(1,Contact);
            preparedStatement.setString(2,User.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void userInsert(String login, String password,String contacts) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        salt = login.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        password = Base64.getEncoder().encodeToString(hash);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT_QUERY)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,contacts);
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
    public static boolean isLoggedIn(String login, String password) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        boolean result = false;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        salt = login.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        password = Base64.getEncoder().encodeToString(hash);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_QUERY)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User.setLogin(resultSet.getString("login"));
                User.setId(resultSet.getInt("id"));
                User.setContact(resultSet.getString("contacts"));
                preparedStatement.close();
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static SelectedUser getUserInfo(int id) throws SQLException {
        SelectedUser result = new SelectedUser("213","123");
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_INFO_QUERY)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new SelectedUser(resultSet.getString("login"),resultSet.getString("contacts"));
                preparedStatement.close();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isUserExist(String login) throws SQLException {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_USER_EXIST_QUERY)) {
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
                            result.add(new Order(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("user_id"),
                                    resultSet.getString("photograph_name"),
                                    resultSet.getInt("status"),
                                    resultSet.getString("user_name"),
                                    resultSet.getString("user_contact"),
                                    resultSet.getDate("order_date")));
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
                            result.add(new Order(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("user_id"),
                                    resultSet.getString("photograph_name"),
                                    resultSet.getInt("status"),
                                    resultSet.getString("user_name"),
                                    resultSet.getString("user_contact"),
                                    resultSet.getDate("order_date")));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return result;
    }
    public static void insertOrder(String photographname,String username, String usercontact,Date date) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_INSERT_QUERY)) {
            preparedStatement.setInt(1,User.getId());
            preparedStatement.setString(2,photographname);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,usercontact);
            preparedStatement.setDate(5,date);
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
