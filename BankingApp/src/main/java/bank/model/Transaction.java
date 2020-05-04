package bank.model;

import java.sql.Timestamp;

/***
 * This class is to model a bank account transaction.
 *
 * @author Shawyn Kane
 */
public class Transaction {
    public static final int NEW_TRANSACTIONID = -1;
    public static final String WITHDRAWAL_DESCRIPTION = "withdrawal";
    public static final String DEPOSIT_DESCRIPTION = "deposit";
    public static final String TRANSFER_DESCRIPTION = "transfer";
    private int transactionID;
    private int userID;
    private double previousBalance;



    private double transactionAmount;
    private String description;
    private Timestamp timeOfTransaction;

    /***
     *
     * @param transactionID
     * @param previousBalance
     * @param transactionAmount
     * @param description
     * @param timeOfTransaction
     */
    public Transaction(int transactionID, int userID, double previousBalance, double transactionAmount, String description, Timestamp timeOfTransaction) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.previousBalance = previousBalance;
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.timeOfTransaction = timeOfTransaction;
    }

    /***
     *
     * @return transactionID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /***
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }
    /***
     *
     * @return previousBalance
     */
    public double getPreviousBalance() {
        return previousBalance;
    }

    /***
     *
     * @return previousBalance + transactionAmount
     */
    public double getUpdatedBalance() {
        return previousBalance + transactionAmount;
    }

    /***
     *
     * @return transactionAmount
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    /***
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /***
     *
     * @return timeOfTransaction
     */
    public Timestamp getTimeOfTransaction() { return timeOfTransaction; }

    /***
     * This method prints the TranscactionID, transaction amount, previous balance, updated balance, description, and time of transaction to the console (System.out).
     */
    public void printToScreen() {
        System.out.println("TransactionID:\t" + transactionID + "\nAmount:\t" + transactionAmount + "\nPrevious Balance:\t" + previousBalance + "\nUpdated Balance:\t" + getUpdatedBalance() + "\nDescription:\t" + description + "\nTime of Transaction:\t" + timeOfTransaction);
    }

    /***
     *
     * @return transactionID, previous balance, updated balance, transaction amount, description, and time of transaction in a comma delimited format for a .csv file.
     */
    @Override
    public String toString() {
        return transactionID + "," + previousBalance + "," + transactionAmount + "," + description + "," + (timeOfTransaction.toString());
    }
}
