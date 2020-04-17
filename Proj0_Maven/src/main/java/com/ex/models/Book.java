package com.ex.models;

import java.time.LocalDate;

public class Book {
    private int barcode;
    private String title;
    private String author;
    private int checkedOutUser;
    private LocalDate dueDate;

    public Book() {}
    public Book(int barcode, String title, String author) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCheckedOutUser() {
        return checkedOutUser;
    }

    public void setCheckedOutUser(int checkedOutUser) {
        this.checkedOutUser = checkedOutUser;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
