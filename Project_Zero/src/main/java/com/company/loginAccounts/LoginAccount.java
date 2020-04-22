package com.company.loginAccounts;

/***
 * This class is to model the credentials of a login account.
 * @author  Shawyn Kane
 */
public class LoginAccount {
    private String userName;
    private String pin;
    private boolean admin = false;

    /***
     *
     * @param userName
     * @param pin
     * @param admin
     */
    public LoginAccount(String userName, String pin, boolean admin) {
        this.userName = userName;
        this.pin = pin;
        this.admin = admin;
    }

    /***
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /***
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /***
     *
     * @return pin
     */
    public String getPin() {
        return pin;
    }

    /***
     *
     * @param pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /***
     *
     * @return admin
     */
    public boolean isAdmin() { return admin; }

    /***
     *
     * @param loginAccount
     * @return
     */
    public boolean equals(LoginAccount loginAccount) {
        return ((this.userName.equals(loginAccount.userName)) && (this.pin.equals(loginAccount.pin)) && (this.admin == loginAccount.admin));
    }
}
