package com.ex.services;

import com.ex.dao.BookDAO;
import com.ex.models.Book;

public class CheckInService {
    private final BookDAO bookDatabase;

    public CheckInService(BookDAO dao) {
        bookDatabase = dao;
    }

    /**
     * Check in the book with the specified barcode.
     * It is acceptable to check in a book that is not currently checked out by a user.
     * @param barcode The barcode of the book to be checked in
     * @return The book that was checked in. If no book with that barcode was found,
     * or if an error occurred when checking in the book, the return value is <code>null</code>.
     */
    public Book checkIn(int barcode) {
        Book book = bookDatabase.findByBarcode(barcode);
        if (book != null) {
            book.setCheckedOutUser(0);
            book.setDueDate(null);
            if (!bookDatabase.update(barcode, book)) {
                System.err.println("Error checking in book " + barcode + "!");
                book = null;
            }
        }
        return book;
    }
}
