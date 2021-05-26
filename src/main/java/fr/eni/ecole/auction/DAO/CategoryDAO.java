package fr.eni.ecole.auction.DAO;


import fr.eni.ecole.auction.BO.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class CategoryDAO {
        private String jdbcURL;
        private String jdbcUsername;
        private String jdbcPassword;
        private Connection jdbcConnection;


        public CategoryDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

        public List<Category> listCategories() throws SQLException {
            List<Category> listCategory = new ArrayList<>();

            String sql = "SELECT * FROM categories";

            connect();

            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id_category = resultSet.getInt("id_category");
                String category_name = resultSet.getString("category_name");

                Category category = new Category(id_category, category_name);
                listCategory.add(category);
            }

            resultSet.close();
            statement.close();

            disconnect();

            return listCategory;
        }
}
