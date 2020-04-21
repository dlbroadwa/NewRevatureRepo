package com.ex.dao;

import com.ex.models.Book;

import java.util.List;

public interface BookDAO {
    /**
     * Adds a book to the database.
     * @param book the book to be added to the database
     * @return <code>true</code> if the book was successfully added, <code>false</code> otherwise.
     */
    boolean add(Book book);

    /**
     * Removes a book from the database.
     * @param barcode the barcode of the book to remove from the database
     * @return <code>true</code> if a book was successfully removed, <code>false</code> otherwise.
     */
    boolean remove(int barcode);

    /**
     * Updates the information for a book in the database.
     * @param barcode the barcode of the book whose information should be updated
     * @param newBookInfo the new book information
     * @return <code>true</code> if the update was successful, <code>false</code> otherwise.
     */
    boolean update(int barcode, Book newBookInfo);

    Book findByBarcode(int barcodeQuery);
    List<Book> findByTitle(String titleQuery);
    List<Book> findByAuthor(String authorQuery);

    /**
     * Retrieves a list of all Books checked out by the user with the specified card number.
     * @param cardNumber the library card number of a user
     * @return all books checked out by that user, or an empty list if that user does not
     * exist.
     */
    List<Book> getBooksCheckedOutBy(int cardNumber);
}
