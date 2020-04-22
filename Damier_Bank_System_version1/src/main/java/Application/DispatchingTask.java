package Application;

import ApplicationViews.*;
import Info.DAODdatabase;
import Info.DAOaccountHolder;
import Info.DAOemployee;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * This class actually is a distributor
 * it  facilitates classes to have access with other class
 * it is a bridge for the entire application
 *
 *it is instantiate objects from other classes calling them to get certain result or performance
 */


public class DispatchingTask extends App {


   private Scanner scanner;
    private String question;
    private String response;
    private String bankName;
    private  int whoYourAre;



   private AccountHolder accountHolder;
   private EmployeeInfo employeeInfo;
   private AccountHolderInfo accountHolderInfo;
   private DoNotHaveAcount doNotHaveAcount;
   private EmployeeAccount employeeAccount;
   private Transaction transaction =null;
   private DAOemployee daOemployee;
   private DAOaccountHolder daOaccountHolder;
   private Credential credential;
   private DAODdatabase daoDdatabase;

   String urlFile ="./src/Resources/DAOaccountHolder.txt";

// initialization of other classes to its constructor

    public DispatchingTask(String bankName){
        this.bankName = bankName;

        this.scanner =new Scanner(System.in);
        transaction = new Transaction(this);
        accountHolder =new AccountHolder(this);
        employeeInfo = new EmployeeInfo();
       // accountHolderInfo = new AccountHolderInfo();
        doNotHaveAcount = new DoNotHaveAcount(this);
        employeeAccount = new EmployeeAccount(this);
        daOaccountHolder = new DAOaccountHolder(urlFile);
        daoDdatabase = new DAODdatabase();
        daOemployee = new DAOemployee( "Resources/DAOemployee.txt");

    };


    /**
     * Generate getters and setters to make it easier for this class object
     * to call other classes and their methods
     * @retur
     */
    public DAODdatabase getDaoDdatabase() {
        return daoDdatabase;
    }

    public void setDaoDdatabase(DAODdatabase daoDdatabase) {
        this.daoDdatabase = daoDdatabase;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public AccountHolderInfo getAccountHolderInfo() {
        return accountHolderInfo;
    }

    public void setAccountHolderInfo(AccountHolderInfo accountHolderInfo) {
        this.accountHolderInfo = accountHolderInfo;
    }

    public DoNotHaveAcount getDoNotHaveAcount() {
        return doNotHaveAcount;
    }

    public void setDoNotHaveAcount(DoNotHaveAcount doNotHaveAcount) {
        this.doNotHaveAcount = doNotHaveAcount;
    }

    public EmployeeAccount getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(EmployeeAccount employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public DAOemployee getDaOemployee() {
        return daOemployee;
    }

    public void setDaOemployee(DAOemployee daOemployee) {
        this.daOemployee = daOemployee;
    }

    public DAOaccountHolder getDaOaccountHolder() {
        return daOaccountHolder;
    }

    public void setDaOaccountHolder(DAOaccountHolder daOaccountHolder) {
        this.daOaccountHolder = daOaccountHolder;
    }

    public int getWhoYourAre() {
        return whoYourAre;
    }

    public void setWhoYourAre(int whoYourAre) {
        this.whoYourAre = whoYourAre;
    }

    @Override
    public void run()
        {
// Calling the menu for USER Input

            try{
                /**
                 * this is where everything starts after calling this method at the Main class
                 */
                transaction.menu();

          //     daOaccountHolder.writingFile("New Name","newadd2","5344",54645611);
//                daOaccountHolder.readingFile();

            }
            catch (Exception ex){
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }

//                    }


           // System.out.println(getBankName());
        }

    /**
     * Base on the User input this method will decide what will be the next step to follow
     */

        public  void dispatch(int number) throws IOException, ClassNotFoundException {

        if(number == 1)
        {
            System.out.println("Let us Confirm Your Account with You");
            credential = new AccountHolder(this);
            credential.AskForCredential();
        }
        else if (number ==2)
        {
            System.out.println("Let us Verify your Employee Id");
            credential = new EmployeeAccount(this);
            credential.AskForCredential();
        }
        else if(number ==3)
        {
            System.out.println("Account will create very fast let us Started ");
            doNotHaveAcount = new DoNotHaveAcount(this);
            doNotHaveAcount.createAccount(this);
        }

        }
}
