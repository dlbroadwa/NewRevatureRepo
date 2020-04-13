package Banking;

public class UserNameBankAccountIDPair {
    private int accountID = -1;
    private String userName;
    public UserNameBankAccountIDPair(int accountID, String userName) {
        this.accountID = accountID;
        this.userName = userName;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getCustomerID() {
        return userName;
    }

    @Override
    public String toString() {
        return accountID + "," + userName;
    }
}
