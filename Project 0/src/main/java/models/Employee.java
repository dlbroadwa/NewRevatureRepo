package models;

public class Employee {
    private int employeeID;
    private String userid;
    private String first_name;
    private String last_name;
    private String hourly_Salary;

    public Employee() {}

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getHourly_Salary() {
        return hourly_Salary;
    }

    public void setHourly_Salary(String hourly_Salary) {
        this.hourly_Salary = hourly_Salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", hourly_Salary=" + hourly_Salary +
                '}';
    }
}
