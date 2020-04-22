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
}
