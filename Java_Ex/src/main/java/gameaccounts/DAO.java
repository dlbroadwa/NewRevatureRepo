package gameaccounts;

public interface DAO {
    public void list();
    public String getAccountInfo(int id);
    public void createAccount(String name, String password, boolean isAdmin);
    public void updateAccount();
}
