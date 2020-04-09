package Application;

public class Customer {
    String userName;
    String pin;

    public Customer(String userName, String pin) {
        this.userName = userName;
        this.pin = pin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
