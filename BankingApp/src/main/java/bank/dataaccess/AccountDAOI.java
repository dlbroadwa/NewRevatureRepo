package bank.dataaccess;

public interface AccountDAOI<ID> {
    boolean create();
    boolean delete(ID id);
    boolean read(ID id);
    boolean update(String username, ID id, double amount);
    boolean transfer(String userName, int userAccountID, double amount, int transferredAccountID);
}
