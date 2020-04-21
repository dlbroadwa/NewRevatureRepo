package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.io.BarcodeReader;
import com.ex.io.InputSource;
import com.ex.models.Book;
import com.ex.services.BookSearchService;

import java.util.ArrayList;
import java.util.List;

public class BookSearchScreen implements Screen {
    private Screen prevScreen;
    private BookSearchService.SearchType searchType;

    private void showTitle() {
        switch (searchType) {
            case TITLE:
                System.out.println("Search by title\n");
                break;
            case AUTHOR:
                System.out.printf("Search by author\n");
                break;
            case BARCODE:
                System.out.println("Search by barcode\n");
                break;
        }
    }
    private void displayResults(List<Book> books) {
        System.out.println("Search results:\n");
        if (!books.isEmpty()) {
            for (Book b : books) {
                System.out.println("Title: " + b.getTitle());
                System.out.println("Author: " + b.getAuthor());
                System.out.println("Barcode: " + b.getBarcode() + "\n");
            }
        }
        else {
            System.out.println("No results found.\n");
        }
    }

    public BookSearchScreen(BookSearchService.SearchType searchType) {
        this.searchType = searchType;
    }
    public BookSearchScreen(BookSearchService.SearchType searchType, Screen screen) {
        this(searchType);
        prevScreen = screen;
    }

    @Override
    public Screen doScreen(Application app) {
        showTitle();
        System.out.print("Enter query, or press Enter to go back: ");

        BookSearchService service = ((LibraryApp)app).getBookSearchService();
        List<Book> results;

        if (searchType == BookSearchService.SearchType.BARCODE) {
            BarcodeReader barcodeReader = ((LibraryApp)app).getBarcodeReader();
            int barcode = barcodeReader.readBarcode();
            if (barcode == -1)
                return prevScreen;

            results = new ArrayList<>();
            if (barcode > 0)
                results.add(service.executeSearch(barcode));
        }
        else {
            InputSource inputSource = ((LibraryApp)app).getInputSource();
            String query = inputSource.getInput();

            results = service.executeSearch(searchType, query);
        }

        displayResults(results);

        return prevScreen;
    }
}
