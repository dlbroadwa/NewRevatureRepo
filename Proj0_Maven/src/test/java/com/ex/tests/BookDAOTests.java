package com.ex.tests;

import com.ex.dao.BookDAO;
import com.ex.io.BookSQLDatabase;
import com.ex.models.Book;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAOTests {
    static DatabaseConnection dc;
    static BookDAO dao;

    @BeforeClass
    public static void init() {
        String url = "jdbc:postgresql://database-1.c7zjtw5vhjwr.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "library_admin";
        String password = "my$ecurep@ssw0rd";

        dc = new PostgreSQLConnection(url, username, password, "project0_tests");
        dao = new BookSQLDatabase(dc);

        Assert.assertTrue("Failed to initialize driver", dc.isDriverInitialized());
    }

    @Test
    public void shouldFindBook() {
        // Database already contains this record
        Book expected = new Book(12346, "Effective Java", "Joshua", "Bloch");
        Book actual = dao.findByBarcode(12346);
        Assert.assertEquals("Didn't return correct book", expected, actual);
    }
    @Test
    public void shouldAddAndRemoveBook() {
        Book b = new Book(99999, "My awesome book", "Boomer", "Okay");
        boolean result = dao.add(b);
        Assert.assertTrue("add() didn't return true", result);

        Book b2 = dao.findByBarcode(99999);
        Assert.assertEquals("Added book has incorrect information!", b, b2);

        result = dao.remove(99999);
        Assert.assertTrue("remove() didn't return true", result);
        b2 = dao.findByBarcode(99999);
        Assert.assertNull("Book wasn't removed!", b2);
    }
    @Test
    public void shouldUpdateBook() {
        Book book = dao.findByBarcode(12346); // Effective Java
        Book newBook = new Book(987654, "Ineffective C#", "asdfghjkl;", "Smith");
        newBook.setCheckedOutUser(123450000); // John Doe
        newBook.setDueDate(LocalDate.now().plusDays(1)); // Due tomorrow

        boolean result = dao.update(12346, newBook);
        Assert.assertTrue("update() didn't return true", result);

        Book returnedBook = dao.findByBarcode(987654);
        Assert.assertEquals("Updated book doesn't match expected data!", newBook, returnedBook);

        result = dao.update(987654, book); // Restore old book
        Assert.assertTrue("Couldn't restore old book data!", result);
    }

    @Test
    public void shouldFindJavaBooks() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(12346, "Effective Java", "Joshua", "Bloch"));
        expected.add(new Book(12347, "Java Suxxx", "Nobody", "McNobodyFace"));

        List<Book> actual = dao.findByTitle("java");
        Assert.assertArrayEquals("Didn't return all Java books!", expected.toArray(), actual.toArray());
    }

    @Test
    public void shouldFindMcNobodyFaceBooks() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(12348, "I liek sql", "Somebody", "McNobodyFace"));
        expected.add(new Book(12347, "Java Suxxx", "Nobody", "McNobodyFace"));

        List<Book> actual = dao.findByAuthor("nobody");
        Assert.assertArrayEquals("Didn't return all 'nobody' books!", expected.toArray(), actual.toArray());
    }

    @Test
    public void shouldFindJanesCheckedOutBooks() {
        int janeCardNum = 123450001;

        List<Book> expected = new ArrayList<>();
        Book b = new Book(22201, "The Art of Computer Programming, Volume 2: Seminumerical Algorithms",
                "Donald", "Knuth");
        b.setCheckedOutUser(janeCardNum);
        b.setDueDate(LocalDate.parse("2020-04-30"));
        expected.add(b);

        b = new Book(22203, "The Art of Computer Programming, Volume 4A: Combinatorial Algorithms, Part 1",
                "Donald", "Knuth");
        b.setCheckedOutUser(janeCardNum);
        b.setDueDate(LocalDate.parse("2020-05-15"));
        expected.add(b);

        List<Book> actual = dao.getBooksCheckedOutBy(janeCardNum);

        Assert.assertArrayEquals("Didn't return correct checked-out books!",
                expected.toArray(), actual.toArray());
    }
}
