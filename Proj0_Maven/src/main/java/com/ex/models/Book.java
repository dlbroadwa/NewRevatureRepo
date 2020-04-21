package com.ex.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int barcode;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int checkedOutUser;
    private LocalDate dueDate;

    public Book() {}
    public Book(int barcode, String title, String authorFN, String authorLN) {
        this.barcode = barcode;
        this.title = title;
        this.authorFirstName = authorFN;
        this.authorLastName = authorLN;
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

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getAuthor() {
        return authorLastName + ", " + authorFirstName;
    }

    public void setAuthorFirstName(String name) {
        this.authorFirstName = name;
    }
    public void setAuthorLastName(String name) { this.authorLastName = name; }

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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Book b = (Book)other;
        return this.barcode == b.barcode &&
                Objects.equals(this.title, b.title) &&
                Objects.equals(this.authorFirstName, b.authorFirstName) &&
                Objects.equals(this.authorLastName, b.authorLastName) &&
                this.checkedOutUser == b.checkedOutUser &&
                Objects.equals(this.dueDate, b.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, title, authorFirstName, authorLastName, checkedOutUser, dueDate);
    }
}
