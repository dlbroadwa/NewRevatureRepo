package com.company.dao;

import java.util.ArrayList;

public class FileIOBookDatabase implements BookDAO {
    String databaseFilePath = "";
    ArrayList<Book> books;

    private void loadBookDatabase() {
        // File I/O stuff goes here
    }

    public FileIOBookDatabase() {
        books = new ArrayList<Book>();
    }
    public FileIOBookDatabase(String filePath) {
        this();
        databaseFilePath = filePath;
        loadBookDatabase();
    }

    @Override
    public void add(Book book) {
        books.add(book);
        // TODO write new book record to file
    }

    @Override
    public void remove(int barcode) {

    }

    @Override
    public void update(int barcode, Book newBookInfo) {
        for (int i = 0; i < books.size(); ++i) {
            if (books.get(i).getBarcode() == barcode) {
                books.set(i, newBookInfo);
                break;
            }
        }
    }

    @Override
    public Book findByBarcode(int barcodeQuery) {
        for (Book b: books) {
            if (b.getBarcode() == barcodeQuery)
                return b;
        }
    }

    @Override
    public ArrayList<Book> findByTitle(String titleQuery) {
        return null;
    }

    @Override
    public ArrayList<Book> findByAuthor(String authorQuery) {
        return null;
    }
}
