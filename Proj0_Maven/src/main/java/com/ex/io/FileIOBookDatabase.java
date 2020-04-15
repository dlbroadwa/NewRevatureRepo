package com.ex.io;

import com.ex.dao.BookDAO;
import com.ex.models.Book;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileIOBookDatabase implements BookDAO {
    String databaseFilePath = "";
    ArrayList<Book> books;

    private Book getBook(String line) {
        String[] fields = line.split("\t", 3);

        if (fields.length != 3) {
            System.err.println("WARNING: Could not parse record: " + line);
            return null;
        }

        Book b = new Book();
        try {
            b.setBarcode(Integer.parseInt(fields[0]));
            b.setTitle(fields[1]);
            b.setAuthor(fields[2]);
            return b;
        } catch (NumberFormatException e) {
            System.err.println("WARNING: Unparsable barcode, skipping: " + fields[0]);
            return null;
        }
    }

    private void loadBookDatabase() {
        // Read to a temp array first, in case the operation fails for whatever reason
        ArrayList<Book> newBooks = new ArrayList<Book>();
        try (BufferedReader br = new BufferedReader(new FileReader(databaseFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Book b = getBook(line);
                if (b != null)
                    books.add(b);
            }
            // Now that everything is read, replace the old database
            this.books = newBooks;
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Couldn't open book database file for reading!");
        } catch (IOException e) {
            System.err.println("ERROR: Couldn't read book database file!");
        }
    }
    private void saveBookDatabase() {
        // Write to a temp file first, in case the power goes out in the middle of this function or something
        String tmpDatabasePath = databaseFilePath + ".tmp";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmpDatabasePath))) {
            for (Book b: books) {
                String line = b.getBarcode() + "\t" + b.getTitle() + "\t" + b.getAuthor() + "\n";
                bw.write(line);
            }
        } catch (IOException e) {
            System.err.println("ERROR: Couldn't write new database file!");
            return;
        }

        // Now that the temp file has been made, replace the old database with it
        try {
            Files.move(Paths.get(tmpDatabasePath), Paths.get(databaseFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("ERROR: Couldn't replace database file!");
        }
    }

    public FileIOBookDatabase() {
        books = new ArrayList<>();
    }
    public FileIOBookDatabase(String filePath) {
        this();
        databaseFilePath = filePath;
        loadBookDatabase();
    }

    @Override
    public void add(Book book) {
        books.add(book);
        saveBookDatabase();
    }

    @Override
    public void remove(int barcode) {
        books.removeIf(b -> b.getBarcode() == barcode);
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
        return null;
    }

    @Override
    public ArrayList<Book> findByTitle(String titleQuery) {
        // Convert to lowercase for case-insensitive searching
        final String lowercaseQuery = titleQuery.toLowerCase();
        return books.stream().filter(b -> b.getTitle().toLowerCase().contains(lowercaseQuery)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Book> findByAuthor(String authorQuery) {
        final String lowercaseQuery = authorQuery.toLowerCase();
        return books.stream().filter(b -> b.getAuthor().toLowerCase().contains(lowercaseQuery)).collect(Collectors.toCollection(ArrayList::new));
    }
}
