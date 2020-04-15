package dao;

import models.Book;

import java.util.List;

public interface BookDAO {
    void add(Book book);
    void remove(int barcode);
    void update(int barcode, Book newBookInfo);

    Book findByBarcode(int barcodeQuery);
    List<Book> findByTitle(String titleQuery);
    List<Book> findByAuthor(String authorQuery);
}
