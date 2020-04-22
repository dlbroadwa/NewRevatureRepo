package Application;


/**
 * This class has a unique role
 * it is build  to create getters and setters
 * at any point  or any class if it is being called
 * to generate or register information
 * especially when a new User or Employee  will create new Account
 */


public class AccountHolderInfo {

    private String name;
    private String Address;
    private String accountNumber;

    private int SocialSecurityNumber ;
private  double balance;


   public AccountHolderInfo(){


   }

   public void tested(){
       System.out.println("test work");
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getSocialSecurityNumber() {
        return SocialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        SocialSecurityNumber = socialSecurityNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    String str2 =String.valueOf(getAccountNumber());

    public String toString() {
        return "YOU OWN IT{" +
                "name = '" + name + '\'' +
                ", Address = '" + Address + '\'' +
                ", accountNumber = " + accountNumber+
                ", SocialSecurityNumber = " + SocialSecurityNumber+
                '}';
    }
}

