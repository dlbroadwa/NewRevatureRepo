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
    private final String joinQueryBase;

    public BookSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
        joinQueryBase = "SELECT barcode, title, author_fname, author_lname, card_number, due_date FROM " +
                dc.getSchema() + ".books B LEFT JOIN " + dc.getSchema() +
                ".users U ON B.user_id = U.id ";
    }

    @Override
    public boolean add(Book obj) {
        // First make sure that the book doesn't already exist
        if (findByBarcode(obj.getBarcode()) != null)
            return false;

        int addedRowCount = 0;
        String sql = "INSERT INTO " + dc.getSchema() + ".books (barcode, title, author_fname, author_lname) values (?, ?, ?, ?)";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, obj.getBarcode());
            statement.setString(2, obj.getTitle());
            statement.setString(3, obj.getAuthorFirstName());
            statement.setString(4, obj.getAuthorLastName());

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
                ".books set barcode=?, title=?, author_fname=?, author_lname=?, user_id=?, due_date=? where barcode = ?";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, newObj.getBarcode());
            statement.setString(2, newObj.getTitle());
            statement.setString(3, newObj.getAuthorFirstName());
            statement.setString(4, newObj.getAuthorLastName());
            if (uid != 0)
                statement.setInt(5, uid);
            else
                statement.setNull(5, Types.INTEGER);

            if (newObj.getDueDate() != null)
                statement.setDate(6, Date.valueOf(newObj.getDueDate()));
            else
                statement.setNull(6, Types.DATE);

            statement.setInt(7, barcode);

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
            book.setAuthorFirstName(rs.getString("author_fname"));
            book.setAuthorLastName(rs.getString("author_lname"));
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
        String sql = joinQueryBase + "WHERE barcode = ?";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, barcodeQuery);

            try (ResultSet rs = statement.executeQuery()) {
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
        // There's probably still a SQL injection potential here, but fixing it will take
        // more time than I have left to finish this project :|
        titleQuery = "%" + titleQuery.toUpperCase()
                .replace("%", "\\%")
                .replace("_", "\\_") + "%";

        String sql = joinQueryBase + "WHERE UPPER(title) LIKE ? ORDER BY title";

        return runFindQuery(sql, titleQuery);
    }

    @Override
    public List<Book> findByAuthor(String authorQuery) {
        authorQuery = "%" + authorQuery.toUpperCase()
                .replace("%", "\\%")
                .replace("_","\\_") + "%";

        String query = joinQueryBase +
                "WHERE CONCAT(UPPER(author_lname), ' ', UPPER(author_fname)) LIKE ? ORDER BY title";
        return runFindQuery(query, authorQuery);
    }

    @Override
    public List<Book> getBooksCheckedOutBy(int cardNumber) {
        List<Book> books = new ArrayList<>();
        String query = joinQueryBase + "WHERE card_number=? ORDER BY due_date";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, cardNumber);

            try (ResultSet rs = statement.executeQuery()) {
                books = getResults(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }
}
