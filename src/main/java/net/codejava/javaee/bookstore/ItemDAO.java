package net.codejava.javaee.bookstore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;


    public ItemDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean insertItem(Item item, String street, String postal_code, String city) throws SQLException {
        String sql = "INSERT INTO items (item_name, item_description, bid_start_date, bid_end_date, starting_price, id_user, id_category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();
        long key = -1L;
        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, item.getItem_name());
        statement.setString(2, item.getItem_description());
        statement.setString(3, item.getBid_start_date());
        statement.setString(4, item.getBid_end_date());
        statement.setFloat(5, item.getStarting_price());
        statement.setInt(6, item.getId_user());
        statement.setInt(7, item.getId_category());

        boolean rowInserted = statement.executeUpdate() > 0;
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next()){
            key = rs.getLong(1);
            withdrawAddress(key,street,postal_code,city);
        }
        statement.close();
        disconnect();
        return rowInserted;
    }
    private boolean withdrawAddress(long id_item, String street, String postal_code, String city) throws SQLException{
        String sql = "INSERT INTO withdrawals (id_item, street, postal_code, city) VALUES (?, ?, ?, ?)";


        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setLong(1, id_item);
        statement.setString(2, street);
        statement.setString(3, postal_code);
        statement.setString(4, city);

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        return rowInserted;
    }


}