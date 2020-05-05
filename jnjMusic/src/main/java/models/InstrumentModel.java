/**
 * The InstrumentModel Class creates the properties of the Instruments to be sent to
 * the database.
 *
 * functions():
 * toString()- returns a string that includes all the current values of the model.
 * getters- getId(), getInstrumentName(), getUsed(), getPrice()
 * setters- setId(), setInstrumentName(), setUsed(), setPrice()
 * @params setId(), setUsed(), setPrice(): integer value
 * @params setInstrumentName(): String Object
 *
 * NOTE: The used property is set to an int to allow flexibility with determining if the instrument
 * is new, used, or needs to be repaired.
 * [0 new, 1 used, Any other number is an assigned number indicating it needs to be repaired]
 */
package models;

import java.net.URL;

public class InstrumentModel
{
    private int UPC; //UPC provided by eBay
    private String sale; //sale name from eBay seller
    private String details;
    private Integer cat;
    private String catName;
    private String catDescription;
    private Float price;
    private Boolean available;
    private URL image_url;

    public InstrumentModel(Integer UPC,String sale ,
                                String details,Integer cat ,String catName,
                                String catDescription, Float price,
                                Boolean available, URL url)
    {
        this.UPC = UPC;
        this.sale = sale;
        this.details = details;
        this.cat = cat;
        this.catName = catName;
        this.catDescription = catDescription;
        this.price = price;
        this.available = available;
        this. image_url = url;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public URL getImage_url() {
        return image_url;
    }

    public void setImage_url(URL image_url) {
        this.image_url = image_url;
    }

    public String getSale()
    {
        return sale;
    }

    public void setSale(String sale)
    {
        this.sale = sale;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public Boolean getAvailable()
    {
        return available;
    }

    public void setAvailable(Boolean available)
    {
        this.available = available;
    }

    public int getUPC()
    {
        return UPC;
    }

    public void setUPC(int UPC)
    {
        this.UPC = UPC;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getCatName()
    {
        return catName;
    }

    public void setCatName(String catName)
    {
        this.catName = catName;
    }

    public String getCatDescription()
    {
        return catDescription;
    }

    public void setCatDescription(String catDescription)
    {
        this.catDescription = catDescription;
    }

    // price getter
    public float getPrice()
    {
        return this.price;
    }


    // price setter
    public void setPrice(float price)
    {
        this.price = price;
    }
}
