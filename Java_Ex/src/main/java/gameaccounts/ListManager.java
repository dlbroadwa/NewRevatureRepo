package gameaccounts;

import java.io.*;
import java.util.ArrayList;

public class ListManager extends AccountIODAO{
    //private ArrayList<Account> accountListOnline;
    //an idea I might implement
    private Thread save;
    private int id = 1000;

    public ListManager(){

        accountList = new ArrayList<Account>(100);
        save = new Thread(() -> updateAccount());
    }
//    save = new Thread(){
//        @Override
//        public void run() {
//            updateAccount();
//        }
//    };
    public Boolean checkCredentials(String username, String password) {
        System.out.println(username+"\t"+password);
        for (int i = 0; i<accountList.size(); i++){
            if (accountList.get(i).getName().equals(username)){
                if (accountList.get(i).getPassword().equals(password)){
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

    public void saveThread(){
        save.start();
    }

    public void updateAccount() {
        Account curr;
        StringBuilder temp = new StringBuilder();
        for (Account account : accountList) {
            if (account == null) {
                continue;
            }
            curr = account;
            temp.append(curr.getName()).append(";").append(curr.getPassword()).append(";").append(curr.getIsAdmin()).append(";").append(curr.getBalance()).append("\n");
            System.out.println(curr.getName() + "'s account has been saved");
            //writer.write("\r\n");
        }
        try {
            FileWriter writer = new FileWriter("Resources/AccountList.txt");
            writer.write(temp.toString());
            writer.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
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

    public Account getCurr(){
        return (id!=1000)?accountList.get(id):null;
    }

    public void boot() {
        try {
            FileReader reader = new FileReader("Resources/AccountList.txt");

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            //line split into four strings
            String[] sline;
            while ((line = bufferedReader.readLine()) != null) {
                sline=line.split(";");
                accountList.add(new Account(sline[0],sline[1],Boolean.parseBoolean(sline[2]),Integer.parseInt(sline[3])));
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
    }

    public void send(String name, String line){
        Account temp = find(name);
        if (temp==null){
            return;
        }
        temp.receive(line,getCurr().getName());
    }

    private Account find(String name) {
        for (Account account : accountList) {
            if (name.equals(account.getName())) {
                return account;
            }
        }
        System.out.println("Account not found");
        return null;
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
        saveThread();
        //updateAccount();
        //updateAccount(accountList.get(accountList.size()-1));
    }

    public void deleteAccount(int index) {
        if (index>=accountList.size()|index<0){
            System.out.println("Please enter a valid index");
            return;
        }
        if (accountList.get(index) ==null){
            System.out.println("Account does not exist");
        }
        String name = accountList.get(index).getName();
        if(accountList.get(index).getIsAdmin()){
            File file = new File("Resources/"+name+"Messages.txt");
            if (file.delete()){
                System.out.println(name+"'s messages deleted");
            }
        }
        accountList.remove(index);
        save.start();
        System.out.println(name + "'s account has been deleted.");
    }

    public void depositM(int index, int deposit) {
        accountList.get(index).insert(deposit);
        System.out.println(accountList.get(index).getName() +"'s balance is now" + accountList.get(index).getBalance());
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
    }

    public boolean checkDuplicates(String name){
        //checks if the name is already in list
        //returns true if it passes the check
        for (Account account : accountList) {
            if (name.equals(account.getName())) {
                System.out.println("Name has already been taken");
                return true;
            }
        }
        return false;
    }

    public void createAccount(String username, String password) {
        accountList.add(new Account(username, password));
        System.out.println(username + "'s account has been created.");
        saveThread();
        //updateAccount();
        //updateAccount(accountList.get(accountList.size()-1));
        //will use above once it is working
    }

    public Account signUp(String username, String password){
        createAccount(username, password);
        id=accountList.size()-1;
        return getCurr();
    }

    public void readMessages() {
        getCurr().viewAll();
    }
}
