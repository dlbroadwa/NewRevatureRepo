package bank.dataaccess;

public interface AccountDAOI<T, ID> {
    boolean create();
    boolean delete(ID id);
    T read(ID id);
    boolean update(T account);
}
