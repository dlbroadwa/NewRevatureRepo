package LoginAccounts;

public abstract class LoginAccount {
    String userName;
    String pin;

    public LoginAccount(String userName, String pin) {
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

    public boolean equals(LoginAccount loginAccount) {
        return ((this.userName.equals(loginAccount.userName)) && (this.pin.equals(loginAccount.pin)));
    }
}
