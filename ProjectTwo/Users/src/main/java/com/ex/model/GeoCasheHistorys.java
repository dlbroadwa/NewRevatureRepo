package com.ex.model;

import java.time.LocalDateTime;

public class GeoCasheHistorys {
    private String email;
    private int itemID;
    private LocalDateTime dateCollected;
    private String comment;
    private int rating;

    public GeoCasheHistorys(){}

    public GeoCasheHistorys(String email, int itemID, LocalDateTime dateCollected, String comment, int rating) {
        this.email = email;
        this.itemID = itemID;
        this.dateCollected = dateCollected;
        this.comment = comment;
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public LocalDateTime getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(LocalDateTime dateCollected) {
        this.dateCollected = dateCollected;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
