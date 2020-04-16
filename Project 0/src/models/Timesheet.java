package models;

public class Timesheet {
    private int timesheetID;
    private int employeeID;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Saturday;
    private String Sunday;

    public Timesheet() {}

    public int getTimesheetID() {
        return timesheetID;
    }

    public void setTimesheetID(int timesheetID) {
        this.timesheetID = timesheetID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String thursday) {
        Thursday = thursday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "timesheetID=" + timesheetID +
                ", employeeID=" + employeeID +
                ", Monday='" + Monday + '\'' +
                ", Tuesday='" + Tuesday + '\'' +
                ", Wednesday='" + Wednesday + '\'' +
                ", Thursday='" + Thursday + '\'' +
                ", Friday='" + Friday + '\'' +
                ", Saturday='" + Saturday + '\'' +
                ", Sunday='" + Sunday + '\'' +
                '}';
    }
}
