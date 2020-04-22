package data;

import model.Accounts;

import java.util.List;

public interface ITransaction <T, ID> {

    T findTransactionbyId (Accounts account, int id);
    List<T> allTransactionByAccount (Accounts accounts);
    void insertTransaction (Accounts account, float amount, String type);

}
