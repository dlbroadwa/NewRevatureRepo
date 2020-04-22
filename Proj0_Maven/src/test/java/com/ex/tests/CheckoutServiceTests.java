package com.ex.tests;

import com.ex.dao.BookDAO;
import com.ex.models.Book;
import com.ex.services.CheckOutService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutServiceTests {
    @Mock
    BookDAO dao;

    @InjectMocks
    CheckOutService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    // Test that the basic checkout functionality works
    @Test
    public void shouldCheckOut() {
        int barcode = 12345;
        int cardNumber = 999;
        Book testBook = new Book(barcode, "Test title", "Test author", "Smith");
        Book expected = new Book(testBook.getBarcode(), testBook.getTitle(),
                testBook.getAuthorFirstName(), testBook.getAuthorLastName());
        expected.setCheckedOutUser(cardNumber);
        expected.setDueDate(LocalDate.now().plusWeeks(2));

        Mockito.when(dao.findByBarcode(barcode)).thenReturn(testBook);
        // User has no checked out books
        Mockito.when(dao.getBooksCheckedOutBy(cardNumber)).thenReturn(new ArrayList<>());
        Mockito.when(dao.update(barcode, expected)).thenReturn(true);

        Book actual = service.checkOut(barcode, cardNumber);
        Assert.assertEquals("Didn't return expected book!", expected,actual);
    }

    // Test that a user cannot check out a book if they have overdue items
    @Test
    public void shouldNotCheckOutOverdue() {
        int barcode = 12345;
        int cardNumber = 999;
        Book testBook = new Book(barcode, "Test title", "Test author", "Smith");
        Book dontReturnThis = new Book(testBook.getBarcode(), testBook.getTitle(),
                testBook.getAuthorFirstName(), testBook.getAuthorLastName());
        dontReturnThis.setCheckedOutUser(cardNumber);
        dontReturnThis.setDueDate(LocalDate.now().plusWeeks(2));

        Book overdueBook = new Book(54321, "blah", "blahy", "blahsome");
        overdueBook.setCheckedOutUser(cardNumber);
        overdueBook.setDueDate(LocalDate.parse("2004-04-06")); // >16 years overdue :O
        List<Book> checkedOutBooks = new ArrayList<>();
        checkedOutBooks.add(overdueBook);

        // Book should exist
        Mockito.when(dao.findByBarcode(barcode)).thenReturn(testBook);
        // Don't want the book to be checked out
        Mockito.when(dao.update(barcode, dontReturnThis)).thenThrow(new RuntimeException());
        // Give the user an overdue book
        Mockito.when(dao.getBooksCheckedOutBy(cardNumber)).thenReturn(checkedOutBooks);

        Book actual = service.checkOut(barcode, cardNumber);
        Assert.assertNull(actual);
    }

    // Test that a user cannot check out nonexistent books
    @Test
    public void shouldNotCheckOutNonexistent() {
        int barcode = 12345;
        int cardNumber = 999;

        // Book doesn't exist
        Mockito.when(dao.findByBarcode(barcode)).thenReturn(null);
        // Don't want book to be checked out
        Mockito.verify(dao, Mockito.never()).update(Mockito.anyInt(), Mockito.any());
        // User has checkout privileges
        Mockito.when(dao.getBooksCheckedOutBy(cardNumber)).thenReturn(new ArrayList<>());

        Book actual = service.checkOut(barcode, cardNumber);
        Assert.assertNull(actual);
    }

    // Test that a user cannot check out a book that's already checked out by another user
    @Test
    public void shouldNotCheckOutCheckedOut() {
        int barcode = 12345;
        int cardNumber = 999;
        Book testBook = new Book(barcode, "Title", "Auth", "or");
        testBook.setCheckedOutUser(1000); // Not 999
        testBook.setDueDate(LocalDate.parse("2020-04-25")); // Arbitrary due date

        Mockito.when(dao.findByBarcode(barcode)).thenReturn(testBook);
        // Don't want book to be checked out
        Mockito.verify(dao, Mockito.never()).update(Mockito.eq(barcode), Mockito.any());
        // Make sure user has checkout privileges
        Mockito.when(dao.getBooksCheckedOutBy(cardNumber)).thenReturn(new ArrayList<>());

        Book actual = service.checkOut(barcode, cardNumber);
        Assert.assertNull(actual);
    }

    // Test that a user CAN renew a book (by checking it out again)
    @Test
    public void shouldRenew() {
        int barcode = 12345;
        int cardNumber = 999;
        Book testBook = new Book(barcode, "foo", "bar", "baz");
        testBook.setCheckedOutUser(cardNumber);
        testBook.setDueDate(LocalDate.now()); // Due today, I guess

        Book expected = new Book(testBook.getBarcode(), testBook.getTitle(),
                testBook.getAuthorFirstName(), testBook.getAuthorLastName());
        expected.setCheckedOutUser(cardNumber);
        expected.setDueDate(LocalDate.now().plusWeeks(2));

        Mockito.when(dao.findByBarcode(barcode)).thenReturn(testBook);
        // Make sure user has checkout privileges
        Mockito.when(dao.getBooksCheckedOutBy(cardNumber)).thenReturn(new ArrayList<>());
        // Mock successful update
        Mockito.when(dao.update(barcode, expected)).thenReturn(true);

        Book actual = service.checkOut(barcode, cardNumber);
        Assert.assertEquals("Didn't renew book!", expected, actual);
    }
}
