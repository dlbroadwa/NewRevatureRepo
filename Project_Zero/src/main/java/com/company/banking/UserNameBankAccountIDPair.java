package com.company.banking;

/***
 * This class is a model for the username and bank accountID pair that represents an association between a user and bank account in the sql database.
 *
 * @author Shawyn Kane
 */
public class UserNameBankAccountIDPair {
    private int accountID = -1;
    private String userName;

    /***
     *
     * @param accountID
     * @param userName
     */
    public UserNameBankAccountIDPair(int accountID, String userName) {
        this.accountID = accountID;
        this.userName = userName;
    }

    /***
     *
     * @return accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /***
     *
     * @return userName
     */
    public String getCustomerID() {
        return userName;
    }

    /***
     *
     * @return A comma delimited string of the accountID and username for output to a .csv file.
     */
    @Override
    public String toString() {
        return accountID + "," + userName;
    }

    /***
     *
     * @param pair
     * @return
     */
    public boolean equals(UserNameBankAccountIDPair pair) {
        return (this.accountID == pair.getAccountID() && this.userName.equals(pair.userName));
    }
}
