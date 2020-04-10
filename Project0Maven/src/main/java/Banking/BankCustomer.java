package Banking;

public class BankCustomer {

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private double savings;

    private double checkings;

    private int id;

    public BankCustomer(){

        this.firstName = "DNE";
        this.lastName = "DNE";
        this.userName = "DNE";
        this.password = "DNE";
        this.savings = 0;
        this.checkings = 0;
        this.id = 0;

    }

    public BankCustomer(String fName, String lName, String uName, String pWord, double sving, double ckings, int identity){

        this.firstName = fName;
        this.lastName = lName;
        this.userName = uName;
        this.password = pWord;
        this.savings = sving;
        this.checkings = ckings;
        this.id = identity;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public double getSavings() {
        return savings;
    }

    public double getCheckings() {
        return checkings;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public void setCheckings(double checkings) {
        this.checkings = checkings;
    }

    public void setId(int id) {
        this.id = id;
    }
}
