package gameaccounts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class ListManager implements DAO {
    static int finalIndex = 0;
    private FileWriter writer;
    private FileReader reader;
    private Account[] accountList;

    public ListManager(int size){
        accountList = new Account[size];
    }
    public void saveList() {
        String temp = "";
        try {
            writer = new FileWriter("AccountList.txt");

            for (int i = 0; i<finalIndex; i++) {
                if (accountList[i]==null){
                    continue;
                }
                temp += accountList[i].getName()+";"+accountList[i].getBalance()+"\n";
                System.out.println(temp + " saved");
                //writer.write("\r\n");
            }
            writer.write(temp);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
        return;
    }

    //optimized the list of players once the final index goes out of bounds
    public void optimize() {
        int i=0;
        int j=99;
        /*two indexes starting from the beginning and end
         * of the array. The two following loops will keep
         * track of which indexes are null or not and shift
         * non-null objects to the front end.
         */
        while (true) {
            for (; i<100; i++) {
                if (accountList[i]==null) {
                    break;
                }
            }
            for (; j>i; j--) {
                if (accountList[j]!=null) {
                    accountList[i]=accountList[j];
                    accountList[j]=null;
                    continue;
                }
            }
            finalIndex=j;
            break;
        }
        System.out.println("Account List has been optimized");
        return;
    }

    public void boot() {
        try {
            reader = new FileReader("AccountList.txt");

            BufferedReader bufferedReader = new BufferedReader(reader);
            if (bufferedReader.readLine()==null){
                return;
            }
            String line;
            String[] sline;
            while ((line = bufferedReader.readLine()) != null) {
                sline=line.split(";");
                createAccount(sline[0],Integer.parseInt(sline[1]));
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
        if (accountList[id] != null) {
            result = "name: " + accountList[id].getName() + " balance: " + accountList[id].getBalance();
        }
        else {
            result = null;
        }
        return result;
    }

    @Override
    public void updateAccount(Account obj) {
        //may remove, saveList does this function
    }

    public void createAccount(String name, int deposit) {
        accountList[finalIndex++]=new Account(name, deposit);
        if (finalIndex>=100){
            System.out.println("Optimizing...");
        }
        System.out.println(name + "'s account has been created.");
        return;
    }

    @Override
    public void createAccount(String name) {
        accountList[finalIndex++]=new Account(name);
        if (finalIndex>=100){
            System.out.println("Optimizing...");
        }
        System.out.println(name + "'s account has been created.");
        return;
    }

    public void deleteAccount(int index) {
        if (index>=100|index<0){
            System.out.println("Please enter a valid index");
            return;
        }
        if (accountList[index]==null){
            System.out.println("Account does not exist");
            return;
        }
        String name = accountList[index].getName();
        accountList[index]=null;
        System.out.println(name + "'s account has been deleted.");
        return;
    }

    public void depositM(int index, int deposit) {
        accountList[index].insert(deposit);
        System.out.println(accountList[index].getName() +"'s balance is now" + accountList[index].getBalance());
        return;
    }

    public void spendC(int index, int request){
        accountList[index].spend(request);
        System.out.println(accountList[index].getName() +"'s balance is now" + accountList[index].getBalance());
    }

    public void list() {
        String temp;
        for (int i = 0; i<=finalIndex; i++) {
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
        for (int i=0;i<finalIndex;i++){
            if(name==accountList[i].getName()){
                System.out.println("Name has already been taken");
                return false;
            }
        }
        return true;
    }
}
