package com.ex.services;

import com.ex.dao.BookDAO;
import com.ex.models.Book;

public class BookManagementService {
    private final BookDAO dao;

    public BookManagementService(BookDAO dao) {
        this.dao = dao;
    }

    public boolean updateBookInfo(int barcode, Book newInfo) {
        if (barcode <= 0 || newInfo.getBarcode() <= 0)
            return false;

        return dao.update(barcode, newInfo);
    }

    public boolean addNewBook(int barcode, String title, String authorFName, String authorLName) {
        // Barcode must be positive
        if (barcode <= 0)
            return false;

        return dao.add(new Book(barcode, title, authorFName, authorLName));
    }

    public boolean removeBook(int barcode) {
        if (barcode <= 0)
            return false;
        Book book = dao.findByBarcode(barcode);
        // Cannot remove book that's currently checked out
        if (book == null || book.getCheckedOutUser() != 0)
            return false;

        return dao.remove(barcode);
    }
}
