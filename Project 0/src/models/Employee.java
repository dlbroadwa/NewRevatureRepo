package models;

public class Employee {
    private int employeeID;
    private String password;
    private String firstName;
    private String lastName;
    private float hourly_Salary;

    public Employee() {}

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getHourly_Salary() {
        return hourly_Salary;
    }

    public void setHourly_Salary(float hourly_Salary) {
        this.hourly_Salary = hourly_Salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hourly_Salary=" + hourly_Salary +
                '}';
    }
}
