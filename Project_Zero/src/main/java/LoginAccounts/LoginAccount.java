package LoginAccounts;

public class LoginAccount {
    private String userName;
    private String pin;
    private boolean admin = false;

    public LoginAccount(String userName, String pin, boolean admin) {
        this.userName = userName;
        this.pin = pin;
        this.admin = admin;
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

    public boolean isAdmin() { return admin; }

    public boolean equals(LoginAccount loginAccount) {
        return ((this.userName.equals(loginAccount.userName)) && (this.pin.equals(loginAccount.pin)) && (this.admin == loginAccount.admin));
    }
}
