package pl.camp.it;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = connect();
        //insertCategory("POEZJA", connection);
        //deleteCategory("PYTHON", connection);
        //deleteCategory("PYTHON2", connection);
        //deleteCategory("POEZJA", connection);
        //updateCategoryName("OTHER", "INNE", connection);

        //insertCategory2("NOWA", connection);

        //updateCategoryName2("NOWA", "STARA", connection);

        List<Category> categories = getAllCategories(connection);
        System.out.println(categories);

        List<Book> books = getBookWithFilter2("Wydanie", connection);
        System.out.println(books);
        disconnect(connection);
    }

    static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8","root","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    static void insertCategory(String categoryName, Connection connection) {
        try {
            String SQL = "INSERT INTO tcategory (name) VALUES ('" + categoryName + "');";
            Statement statement = connection.createStatement();
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteCategory(String categoryName, Connection connection) {
        try {
            String SQL = "DELETE FROM tcategory WHERE name='" + categoryName + "';";
            Statement statement = connection.createStatement();
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateCategoryName(String oldCategory, String newCategory, Connection connection) {
        try {
            String SQL = "UPDATE tcategory SET name='" + newCategory + "' WHERE name='" + oldCategory + "';";
            Statement statement = connection.createStatement();
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static List<Category> getAllCategories(Connection connection) {
        List<Category> categories = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tcategory";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Category category = new Category();
                category.setId(id);
                category.setName(name);

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    static List<Book> getBookWithFilter(String text, Connection connection) {
        List<Book> books = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tbook WHERE title like '%" + text + "%';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor_id(resultSet.getInt("author_id"));
                book.setCategory_id(resultSet.getInt("category_id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPieces(resultSet.getInt("pieces"));
                book.setPrice(resultSet.getDouble("price"));
                book.setTitle(resultSet.getString("title"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    static void insertCategory2(String categoryName, Connection connection) {
        try {
            String SQL = "INSERT INTO tcategory (name) VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, categoryName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateCategoryName2(String oldCategory, String newCategory, Connection connection) {
        try {
            String SQL = "UPDATE tcategory SET name=? WHERE name=?;";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, newCategory);
            statement.setString(2, oldCategory);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static List<Book> getBookWithFilter2(String text, Connection connection) {
        List<Book> books = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tbook WHERE title like ?;";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, "%"+text+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor_id(resultSet.getInt("author_id"));
                book.setCategory_id(resultSet.getInt("category_id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPieces(resultSet.getInt("pieces"));
                book.setPrice(resultSet.getDouble("price"));
                book.setTitle(resultSet.getString("title"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
