package com.ex.io;

import com.ex.dao.BookDAO;
import com.ex.models.Book;
import com.ex.services.SQLUserIDService;
import com.ex.services.DatabaseIDService;
import com.ex.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookSQLDatabase implements BookDAO {
    private final DatabaseConnection dc;

    public BookSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

    @Override
    public boolean add(Book obj) {
        // First make sure that the book doesn't already exist
        if (findByBarcode(obj.getBarcode()) != null)
            return false;

        int addedRowCount = 0;
        String sql = "INSERT INTO " + dc.getSchema() + ".books (barcode, title, author) values (?, ?, ?)";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, obj.getBarcode());
            statement.setString(2, obj.getTitle());
            statement.setString(3, obj.getAuthor());

            addedRowCount = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addedRowCount == 1;
    }

    @Override
    public boolean remove(int barcode) {
        int deletedRowCount = 0;
        String sql = "DELETE FROM " + dc.getSchema() + ".books where barcode = ?";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, barcode);

            deletedRowCount = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deletedRowCount > 0;
    }

    @Override
    public boolean update(int barcode, Book newObj) {
        DatabaseIDService service = new SQLUserIDService(dc);
        int uid = service.toID(newObj.getCheckedOutUser());

        int updatedRowCount = 0;
        String sql = "UPDATE " + dc.getSchema() +
                ".books set barcode=?, title=?, author=?, user_id=?, due_date=? where barcode = ?";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, newObj.getBarcode());
            statement.setString(2, newObj.getTitle());
            statement.setString(3, newObj.getAuthor());
            statement.setInt(4, uid);
            statement.setInt(5, barcode);

            updatedRowCount = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updatedRowCount > 0;
    }

    private List<Book> getResults(ResultSet rs) throws SQLException {
        List<Book> results = new ArrayList<>();

        while (rs.next()) {
            Book book = new Book();
            book.setBarcode(rs.getInt("barcode"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCheckedOutUser(rs.getInt("card_number"));
            Date date = rs.getDate("due_date");
            if (date != null)
                book.setDueDate(date.toLocalDate());
            results.add(book);
        }

        return results;
    }

    @Override
    public Book findByBarcode(int barcodeQuery) {
        Book result = null;
        String sql = "SELECT barcode, title, author, card_number, due_date FROM " +
                dc.getSchema() + ".books B LEFT JOIN " + dc.getSchema() +
                ".users U ON B.user_id = U.id WHERE barcode = ?";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, barcodeQuery);

            try (ResultSet rs = statement.executeQuery(sql)) {
                List<Book> results = getResults(rs);
                if (!results.isEmpty())
                    result = results.get(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    private List<Book> runFindQuery(String query, String param) {
        List<Book> result = new ArrayList<>();

        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, param);

            try (ResultSet rs = statement.executeQuery()) {
                result = getResults(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Book> findByTitle(String titleQuery) {
        List<Book> result = new ArrayList<>();

        // ILIKE is a PostgreSQL extension so this'll probably break under a different SQL implementation
        String sql = "SELECT barcode, title, author, card_number, due_date FROM " +
                dc.getSchema() + ".books B LEFT JOIN " + dc.getSchema() +
                ".users U ON B.user_id = U.id WHERE title ILIKE %?%";

        return runFindQuery(sql, titleQuery);
    }

    @Override
    public List<Book> findByAuthor(String authorQuery) {
        List<Book> result = new ArrayList<>();

        // Once again using ILIKE. I suppose I could have just gotten the entire set of books
        // and then searched through them using Java functions, but...
        // ...oh well. :P
        String sql = "SELECT barcode, title, author, card_number, due_date FROM " +
                dc.getSchema() + ".books B LEFT JOIN " + dc.getSchema() +
                ".users U ON B.user_id = U.id WHERE author ILIKE %?%";

        return runFindQuery(sql, authorQuery);
    }
}
