package com.ex.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author that-team
 * This class defines a geocache location. An item can be placed into a geocache by a user and then
 * retrieved by other users. We anticipate that many items will be picked up and placed
 * into the world again, as is customary in the geocaching scene. We also anticipate that the same
 * geocache location will be reused, as is also customary.
 *
 * @param cacheID -  a unique, identifying number for the item, generated by the Database
 * @param itemID - the ID number for the item being placed into this geocache
 * @param imageurl - a picture of where the general area in which the geocache is located, uploaded by the user who places it,
 *              intended to assist other users when looking for it, this field contains a URL linking
 *              to the storage location
 * @param gpsLocation - JSON data including the longitude and latitude of the geocache's location
 * @param difficultyLevel - a ranking, set by the user who places the item. The difficulty ranking
 *                   will inform other users how hard it will be to find. It will also be used
 *                   so that a user may filter geocaches near them, and it will be used for users
 *                   to gain experience by finding the item or placing it.
 *
 */

@Entity
@Table(name="\"GeoCashe\"", schema = "\"that-team_schema\"")
public class GeoCashe {
    private String imageurl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geo_cashe_id")
    private int geoCasheID;

    @ManyToOne
    @JoinColumn(name = "difficulty_level")
    private DifficultyLevel difficultyLevel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    private Item itemID;

    @Column(name = "gps_location")
    private String GPSLocation;

    @OneToMany(mappedBy = "itemID", cascade = CascadeType.ALL)
    private List<GeoCasheHistorys> historys;



    public GeoCashe(){}

    public GeoCashe(Item itemID, String imageurl, String GPSLocation, DifficultyLevel difficultyLevel) {
        this.itemID=itemID;
        this.imageurl = imageurl;
        this.GPSLocation = GPSLocation;
        this.difficultyLevel = difficultyLevel;
    }

    public int getGeoCasheID() {
        return geoCasheID;
    }

    public Item getItemID() {
        return itemID;
    }

    public void setItemID(Item itemID) {
        this.itemID = itemID;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getGPSLocation() {
        return GPSLocation;
    }

    public void setGPSLocation(String GPSLocation) {
        this.GPSLocation = GPSLocation;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
