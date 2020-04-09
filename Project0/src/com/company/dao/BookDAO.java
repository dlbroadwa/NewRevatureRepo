package com.company.dao;

import java.util.ArrayList;

public interface BookDAO {
    void add(Book book);
    void remove(int barcode);
    void update(int barcode, Book newBookInfo);

    Book findByBarcode(int barcodeQuery);
    ArrayList<Book> findByTitle(String titleQuery);
    ArrayList<Book> findByAuthor(String authorQuery);
}
