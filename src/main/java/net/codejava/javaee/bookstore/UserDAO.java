package net.codejava.javaee.bookstore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;


    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {

                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        String sql = "SELECT id_user, nickname, email,street, postal_code, city, user_password, is_admin from users where email like ? and  user_password like ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        User user = null;

        if (result.next()) {
            user = new User();
            user.setNickname(result.getString("nickname"));
            user.setEmail(email);
            user.setStreet(result.getString("street"));
            user.setPostal_code(result.getString("postal_code"));
            user.setCity(result.getString("city"));
            user.setId_user(result.getInt("id_user"));
            user.setIs_admin(result.getInt("is_admin"));
        }

        statement.close();
        disconnect();

        return user;
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (nickname,last_name,first_name,email,user_password,phone_number,street,postal_code,city,credit,is_admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getNickname());
        statement.setString(2, user.getLast_name());
        statement.setString(3, user.getFirst_name());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getUser_password());
        statement.setString(6, user.getPhone_number());
        statement.setString(7, user.getStreet());
        statement.setString(8, user.getPostal_code());
        statement.setString(9, user.getCity());
        statement.setInt(10, user.getCredit());
        statement.setInt(11, user.getIs_admin());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();

        String sql = "SELECT id_user, nickname, last_name, first_name, email, phone_number, street, postal_code, city FROM users";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_user = resultSet.getInt("id_user");
            String nickname = resultSet.getString("nickname");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String street = resultSet.getString("street");
            String postalCode = resultSet.getString("postal_code");
            String city = resultSet.getString("city");



            User user = new User(id_user, nickname, lastName, firstName, email, phoneNumber, street, postalCode,  city);
            listUser.add(user);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listUser;
    }

    public User getUser(int actual_id_user) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE id_user LIKE ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, actual_id_user);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id_user = resultSet.getInt("id_user");
            String nickname = resultSet.getString("nickname");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("user_password");
            String phoneNumber = resultSet.getString("phone_number");
            String street = resultSet.getString("street");
            String postalCode = resultSet.getString("postal_code");
            String city = resultSet.getString("city");

            user = new User(id_user, nickname, lastName, firstName, email,password, phoneNumber, street, postalCode,  city);
        }

        resultSet.close();
        statement.close();

        return user;
    }
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET nickname = ?, last_name = ?, first_name = ?, email = ?, user_password = ?, phone_number = ?, street = ?, postal_code = ?, city = ? WHERE id_user = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getNickname());
        statement.setString(2, user.getLast_name());
        statement.setString(3, user.getFirst_name());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getUser_password());
        statement.setString(6, user.getPhone_number());
        statement.setString(7, user.getStreet());
        statement.setString(8, user.getPostal_code());
        statement.setString(9, user.getCity());
        statement.setInt(10, user.getId_user());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users where id_user LIKE ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId_user());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }
}