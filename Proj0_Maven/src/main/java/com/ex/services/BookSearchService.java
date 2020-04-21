package com.ex.services;

import com.ex.dao.BookDAO;
import com.ex.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookSearchService {
    public enum SearchType {
        TITLE, AUTHOR, BARCODE
    }

    private final BookDAO dao;

    public BookSearchService(BookDAO dao) {
        this.dao = dao;
    }

    /**
     * Searches the book database based on a query and returns a list of results.
     * You can use this method to search by barcode, but it is preferred to use
     * the <code>int</code>-overloaded method for that purpose.
     * @param searchType What field to search on (<code>TITLE</code>, <code>AUTHOR</code>,
     *                   or <code>BARCODE</code>).
     * @param query The search query. If <code>searchType</code> is <code>BARCODE</code>,
     *              this must be parseable as an integer.
     * @return A list of search results
     * @throws NumberFormatException if doing a <code>BARCODE</code> search and the given
     *  query cannot be parsed as an integer
     */
    public List<Book> executeSearch(SearchType searchType, String query) {
        List<Book> results = null;
        switch (searchType) {
            case TITLE:
                results = dao.findByTitle(query);
                break;
            case AUTHOR:
                results = dao.findByAuthor(query);
                break;
            case BARCODE: // We shouldn't ever see this case
                results = new ArrayList<>();
                results.add(dao.findByBarcode(Integer.parseInt(query)));
                break;
        }

        return results;
    }

    /**
     * Searches the book database for the book that has the specified barcode.
     * @param barcode the barcode of the book to retrieve
     * @return A <code>Book</code> with that barcode, or <code>null</code> if no
     * such book exists.
     */
    public Book executeSearch(int barcode) {
        // Barcodes can never be non-positive
        if (barcode <= 0)
            return null;

        return dao.findByBarcode(barcode);
    }
}
