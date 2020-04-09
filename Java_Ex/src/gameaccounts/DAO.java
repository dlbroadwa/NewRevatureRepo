package gameaccounts;

public interface DAO {
    public void list();
    public String getAccountInfo(int id);
    public void createAccount(String name, String password);
    public void updateAccount(Account obj);
}
