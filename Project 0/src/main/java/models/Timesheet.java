package models;

/**
 * Timesheet class providing structure for data retrieved from and applied to database.
 */

public class Timesheet {
    private int timesheetID;
    private String userid;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    public Timesheet() {}

    public int getTimesheetID() {
        return timesheetID;
    }

    public void setTimesheetID(int timesheetID) {
        this.timesheetID = timesheetID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String employeeId) {
        this.userid = employeeId;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "TimesheetID=" + timesheetID +
                ", EmployeeID=" + userid +
                ", Monday='" + monday + '\'' +
                ", Tuesday='" + tuesday + '\'' +
                ", Wednesday='" + wednesday + '\'' +
                ", Thursday='" + thursday + '\'' +
                ", Friday='" + friday + '\'' +
                ", Saturday='" + saturday + '\'' +
                ", Sunday='" + sunday + '\'' +
                "}";
    }
}
