package gameaccounts;

import java.util.ArrayList;

public interface DAO {
    public void list();
    public void createAccount(String name, String password, boolean isAdmin);
    public void deleteAccount(String name, String path);
    public void updateAccount(ArrayList<String> text, String path);
}
