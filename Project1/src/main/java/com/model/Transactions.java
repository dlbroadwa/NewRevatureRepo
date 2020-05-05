package com.model;

/**
 * Class description: The Transaction Class model describes the each transaction.
 * each transaction will have id, account holder, timestamp , amount and transaction type (withdraw or deposit)
 */

public class Transactions {
    int id ;
    Accounts account;
    String transaction_type;
    float trans_amount;
    String timestamp;

    /**
     * Setting default transactions with default values for all class members.
     */
    public Transactions (){
        this.id = -1;
        this.account = new Accounts();
        this.transaction_type = "default";
        this.trans_amount = 0;
        this.timestamp = "";

    }
    /**
     * Retrieving timestamp
     * @return timestamp member
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Setting the timestamp object.
     * @param timestamp is retrieved from database table myschema.users3
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Retrieving id
     * @return id member
     */
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public float getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(float trans_amount) {
        this.trans_amount = trans_amount;
    }
}
