package models;

public class Transactions
{
    private Integer trans_id;
    private Integer upc;
    private String emaill;
    private String date;
    private String time;
    private Float price;



    public Transactions( Integer id, Integer upc, String email, String date,
                         String time, Float price)
    {
        this.date = date;
        this.time = time;
        this.emaill = email;
        this.price = price;
        this.trans_id = id;
        this.upc = upc;
    }

    public Transactions( Integer id, Integer upc, String email,Float price)
    {
        this.emaill = email;
        this.price = price;
        this.trans_id = id;
        this.upc = upc;
    }

    public Integer getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(Integer trans_id) {
        this.trans_id = trans_id;
    }

    public Integer getUpc() {
        return upc;
    }

    public void setUpc(Integer upc) {
        this.upc = upc;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) { this.price = price;
    }
}
