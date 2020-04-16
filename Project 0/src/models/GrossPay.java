package models;

public class GrossPay {
    private int grossPayID;
    private int employeeID;
    private float totalHours;
    private float pay;

    public GrossPay() {}

    public int getGrossPayID() {
        return grossPayID;
    }

    public void setGrossPayID(int grossPayID) {
        this.grossPayID = grossPayID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public float getPay() {
        return pay;
    }

    public void setPay(float pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "GrossPay{" +
                "grossPayID=" + grossPayID +
                ", employeeID=" + employeeID +
                ", totalHours=" + totalHours +
                ", pay=" + pay +
                '}';
    }
}
