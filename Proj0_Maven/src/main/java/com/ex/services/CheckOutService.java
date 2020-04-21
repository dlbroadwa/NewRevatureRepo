package com.ex.services;

import com.ex.dao.BookDAO;
import com.ex.models.Book;

import java.time.LocalDate;
import java.util.List;

public class CheckOutService {
    private final BookDAO bookDatabase;

    private boolean canUserCheckOut(int user) {
        List<Book> checkedOutBooks = bookDatabase.getBooksCheckedOutBy(user);

        // Check that the user doesn't have any overdue books
        for (Book b: checkedOutBooks) {
            if (b.getDueDate().isBefore(LocalDate.now())) {
                return false;
            }
        }

        return true;
    }

    // Implements the business rules for whether the specified user is allowed to
    // check out the specified book
    private boolean checkCheckOut(int user, Book book) {
        int barcode = book.getBarcode();

        // Check that the book exists
        if (book == null) {
            System.out.println("Error: Unrecognized barcode " + barcode + "!");
            return false;
        }

        int checkedOutUser = book.getCheckedOutUser();
        // Check that the book is available to be checked out/renewed
        if (checkedOutUser != 0 && checkedOutUser != user) {
            System.out.println("Error: Book " + barcode + " is already checked out to user " +
                    checkedOutUser);
            return false;
        }

        // Check that the user is allowed to check out books
        // (i.e., that they don't have any overdue books)
        if (!canUserCheckOut(user)) {
            System.out.println("Patron " + user + " cannot check books out due to overdue books.");
            return false;
        }

        return true;
    }

    public CheckOutService(BookDAO dao) {
        bookDatabase = dao;
    }

    public Book checkOut(int barcode, int user) {
        Book book = bookDatabase.findByBarcode(barcode);

        if (checkCheckOut(user, book)) {
            book.setCheckedOutUser(user);
            // TODO Don't hardcode this value
            LocalDate dueDate = LocalDate.now().plusWeeks(2);
            book.setDueDate(dueDate);
            if (!bookDatabase.update(barcode, book)) {
                System.err.println("Error checking out book " + barcode + " to user " + user + "!");
                book = null;
            }
        }
        else
            book = null;

        return book;
    }
}
