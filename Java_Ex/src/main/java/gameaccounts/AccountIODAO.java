package gameaccounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccountIODAO implements DAO {
    public static ArrayList<Account> accountList;

    @Override
    public void list() {

    }

    @Override
    public void createAccount(String name, String password, boolean isAdmin) {

    }

    @Override
    public void deleteAccount(String text, String path) {

    }

    @Override
    public void updateAccount(ArrayList<String> text, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            for(String i:text) {
                writer.append(i).append("\n");
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println("Failed to save account information");
            e.printStackTrace();
        }
    }
}
