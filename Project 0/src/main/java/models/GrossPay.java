package models;

/**
 * GrossPay class providing structure for data retrieved from and applied to database.
 * Currently not being utilized.
 */

public class GrossPay {
    private int grossPayID;
    private String userid;
    private String totalHours;
    private String pay;

    public GrossPay() {}

    public int getGrossPayID() {
        return grossPayID;
    }

    public void setGrossPayID(int grossPayID) {
        this.grossPayID = grossPayID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "GrossPay{" +
                "grossPayID=" + grossPayID +
                ", employeeID=" + userid +
                ", totalHours=" + totalHours +
                ", pay=" + pay +
                '}';
    }
}
