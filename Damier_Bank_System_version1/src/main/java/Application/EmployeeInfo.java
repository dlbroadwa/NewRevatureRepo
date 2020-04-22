package Application;


/**
 * This class has a unique role
 * it is build  to create getters and setters For EMPLOYEE only
 * at any point  or any class if it is being called
 * to generate or register information
 * especially if the COMPANY want to hire new a NEw Hire
 */

public class EmployeeInfo {

    private String name;
    private String Address;
    int employeeId;
    double hrs;
   // String companyName;

    public EmployeeInfo(){};

    // Constructor

    public EmployeeInfo(String name , String address , int employeeId, double hrs){
        this.name =name;
        this.Address =address;
        this.employeeId =employeeId;
        this.hrs =hrs;
    }


    public void listAllAccountHolder(){

    }
    public void deleteAccountHolder(int id){

    }

    public void addAccountHolder(String name, String address ,int SNS,int AcctNumber){

    }

    public double getHrs() {
        return hrs;
    }

    public void setHrs(double hrs) {
        this.hrs = hrs;
    }

    @Override
    public String toString() {
        return "Employee personal Information{" +

                ", Address ='" + Address + '\'' +
                ", employeeId =" + employeeId +
                ", hrs =" + hrs +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
