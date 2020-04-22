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

    /**
     * Finds the book with the specified barcode.
     * @param barcodeQuery the barcode to search for
     * @return the book with that barcode, or <code>null</code> if such a book could not be found.
     */
    Book findByBarcode(int barcodeQuery);

    /**
     * Finds all books whose title contains the given search query, ignoring case.
     * @param titleQuery the substring to search for
     * @return a list of all books whose title contains that query.
     */
    List<Book> findByTitle(String titleQuery);

    /**
     * Finds all books whose author name contains the given search query, ignoring case.
     * @param authorQuery the substring to search for
     * @return a list of all books whose author name contains that query.
     */
    List<Book> findByAuthor(String authorQuery);

    /**
     * Retrieves a list of all Books checked out by the user with the specified card number.
     * @param cardNumber the library card number of a user
     * @return all books checked out by that user, or an empty list if that user does not
     * exist.
     */
    List<Book> getBooksCheckedOutBy(int cardNumber);
}
