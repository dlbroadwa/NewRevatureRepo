package com.ex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Table(name="\"GeoCasheHistorys\"", schema = "\"that-team_schema\"")
public class GeoCasheHistorys {

    private String email;
    private int itemID;

    @Column(name = "date_collected")
    private LocalDateTime dateCollected;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
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
