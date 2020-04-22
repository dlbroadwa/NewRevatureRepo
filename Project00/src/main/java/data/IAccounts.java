package data;

import model.Accounts;
import model.Users;

import java.util.List;

public interface IAccounts <T, ID> {
    void updateBalance (Accounts obj, float amount);
    T findByAccount(String email);
    List<T> findAll();


}
