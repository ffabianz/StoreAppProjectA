package net.codejava.javaee.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean login(User user) throws SQLException {
        String sql = "SELECT nickname, user_password from users where nickname like ? and  user_password like ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getNickname());
        statement.setString(2, user.getUser_password());

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next() == true){
            resultSet.close();
            statement.close();
            return true;
        }

        resultSet.close();
        statement.close();
        disconnect();

        return false;
    }

    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
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

    public List<Book> listAllBooks() throws SQLException {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            Book book = new Book(id, title, author, price);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listBook;
    }
    public List<Item> listAllItems() throws SQLException {
        List<Item> listItem = new ArrayList<>();

        String sql = "SELECT * FROM items";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_item = resultSet.getInt("id_item");
            String item_name = resultSet.getString("item_name");
            String item_description = resultSet.getString("item_description");
            String bid_start_date = resultSet.getString("bid_start_date");
            String bid_end_date = resultSet.getString("bid_end_date");
            int starting_price = resultSet.getInt("starting_price");
            int selling_price = resultSet.getInt("selling_price");
            int id_user = resultSet.getInt("id_user");
            int id_category = resultSet.getInt("id_category");


            Item item = new Item(id_item, item_name, item_description, bid_start_date, bid_end_date, starting_price, selling_price, id_user, id_category);
            listItem.add(item);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listItem;
    }

    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, book.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            book = new Book(id, title, author, price);
        }

        resultSet.close();
        statement.close();

        return book;
    }
}
