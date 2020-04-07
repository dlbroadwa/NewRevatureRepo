package gameaccounts;

public interface DAO {
    public void list();
    public String getAccountInfo(int id);
    public void createAccount(String name, int deposit);
    public void createAccount(String name);
    public void updateAccount(Account obj);
}
