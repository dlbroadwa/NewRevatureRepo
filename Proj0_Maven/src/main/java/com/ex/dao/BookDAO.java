package com.ex.dao;

import com.ex.models.Book;

import java.util.List;

public interface BookDAO {
    boolean add(Book book);
    boolean remove(int barcode);
    boolean update(int barcode, Book newBookInfo);

    Book findByBarcode(int barcodeQuery);
    List<Book> findByTitle(String titleQuery);
    List<Book> findByAuthor(String authorQuery);
}
