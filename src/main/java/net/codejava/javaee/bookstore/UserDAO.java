package net.codejava.javaee.bookstore;

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
        String sql = "SELECT nickname, email, user_password from users where email like ? and  user_password like ?";
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
        }

        statement.close();
        disconnect();

        return user;
    }
    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
//TODO change * for the attributes i need nothing more
        String sql = "SELECT * FROM users";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String nickname = resultSet.getString("nickname");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String street = resultSet.getString("street");
            String postalCode = resultSet.getString("postal_code");
            String city = resultSet.getString("city");



            User user = new User(nickname, lastName, firstName, email, phoneNumber, street, postalCode,  city);
            listUser.add(user);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listUser;
    }
}