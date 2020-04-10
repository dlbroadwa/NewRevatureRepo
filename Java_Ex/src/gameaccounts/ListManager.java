package gameaccounts;

import UI.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListManager implements DAO {
    private FileWriter writer;
    private FileReader reader;
    private static ArrayList<Account> accountList;
    //private ArrayList<Account> accountListOnline;
    //an idea I might implement
    private Thread save;
    private int id;
    private Menu menu;

    public ListManager(int size){
        accountList = new ArrayList<Account>(100);
        menu=new Menu();
    }

    public Boolean checkCredentials(String username, String password) {
        for (int i = 0; i<accountList.size(); i++){
            if (accountList.get(i).getName()==username){
                if (accountList.get(i).getPassword()==password){
                    id=i;
                    System.out.println("Welcome back "+accountList.get(i).getName());
                    return true;
                }
                else {
                    System.out.println("Password does not match");
                    return false;
                }
            }
        }
        System.out.println("No such account found");
        return false;
    }

    public void updateAccount() {
        String temp = "";
        for (int i = 0; i<accountList.size(); i++) {
            if (accountList.get(i) ==null){
                continue;
            }
            temp += accountList.get(i).getName()+";"+ accountList.get(i).getPassword()+";" + accountList.get(i).getBalance()+"\n";
            System.out.println(temp + " saved");
            //writer.write("\r\n");
        }
        try {
            writer = new FileWriter("Resources/AccountList.txt");
            writer.write(temp);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
        return;
    }

    public void updateAccount(Account account){
        String temp = "";
        temp = account.getName()+";"+ account.getPassword()+";" + account.getBalance()+"\n";
        try {
            writer = new FileWriter("Resources/AccountList.txt");
            writer.append(temp);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
        return;
    }

    //optimized the list of players once the final index goes out of bounds
    //no longer used due to arrayList
//    public void optimize() {
//        int i=0;
//        int j=99;
//        /*two indexes starting from the beginning and end
//         * of the array. The two following loops will keep
//         * track of which indexes are null or not and shift
//         * non-null objects to the front end.
//         */
//        while (true) {
//            for (; i<100; i++) {
//                if (accountList[i]==null) {
//                    break;
//                }
//            }
//            for (; j>i; j--) {
//                if (accountList[j]!=null) {
//                    accountList[i]=accountList[j];
//                    accountList[j]=null;
//                    continue;
//                }
//            }
//            finalIndex=j;
//            break;
//        }
//        System.out.println("Account List has been optimized");
//        return;
//    }

    public void boot() {
        try {
            reader = new FileReader("Resources/AccountList.txt");

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            //line split into four strings
            String[] sline = new String[4];
            while ((line = bufferedReader.readLine()) != null) {
                sline=line.split(";");
                createAccount(sline[0],sline[1],Boolean.parseBoolean(sline[2]),Integer.parseInt(sline[3]));
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
        return;
    }

    public String getAccountInfo(int id) {
        String result;
        if (accountList.get(id) != null) {
            result = "name: " + accountList.get(id).getName() + " balance: " + accountList.get(id).getBalance();
        }
        else {
            result = null;
        }
        return result;
    }

    public void createAccount(String name, String password, boolean isAdmin) {
        accountList.add(new Account(name, password, isAdmin));
        System.out.println(name + "'s account has been created.");
        updateAccount(accountList.get(accountList.size()-1));
        return;
    }

    public void createAccount(String name, String password, boolean isAdmin, int deposit) {
        accountList.add(new Account(name, password, isAdmin, deposit));
        System.out.println(name + "'s account has been created.");
        updateAccount(accountList.get(accountList.size()-1));
        return;
    }

    public void deleteAccount(int index) {
        if (index>=100|index<0){
            System.out.println("Please enter a valid index");
            return;
        }
        if (accountList.get(index) ==null){
            System.out.println("Account does not exist");
            return;
        }
        String name = accountList.get(index).getName();
        accountList.remove(index);
        System.out.println(name + "'s account has been deleted.");
        return;
    }

    public void depositM(int index, int deposit) {
        accountList.get(index).insert(deposit);
        System.out.println(accountList.get(index).getName() +"'s balance is now" + accountList.get(index).getBalance());
        return;
    }

    public void spendC(int index, int request){
        accountList.get(index).spend(request);
        System.out.println(accountList.get(index).getName() +"'s balance is now" + accountList.get(index).getBalance());
    }

    public void list() {
        String temp;
        for (int i = 0; i<accountList.size(); i++) {
            temp = getAccountInfo(i);
            if (temp==null) {
                continue;
            }
            System.out.print(i+":\t");
            System.out.println(temp);
        }
        return;
    }

    public boolean checkDuplicates(String name){
        //checks if the name is already in list
        //returns true if it passes the check
        for (int i=0;i<accountList.size();i++){
            if(name== accountList.get(i).getName()){
                System.out.println("Name has already been taken");
                return false;
            }
        }
        return true;
    }

    public void createAccount(String username, String password) {
        accountList.add(new Account(username, password));
        System.out.println(username + "'s account has been created.");
        updateAccount(accountList.get(accountList.size()-1));
        return;
    }

}
