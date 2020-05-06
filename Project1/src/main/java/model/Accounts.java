package model;

/**
 * Class description: The class will describe what bank user account profile will hold.
 * the class will have account ID, current balance and holders.
 */

public class Accounts {
    private int account_id ;
    private float balance;
    Users holder ;
    private String accountType;

    public Accounts (){
        this.account_id = -1;
        this.balance = 0;
        this.holder = new Users ();
        this.accountType = "default";
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Users getHolder() {
        return holder;
    }

    public void setHolder(Users holder) {
        this.holder = holder;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }




}
