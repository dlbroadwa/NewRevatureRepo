package data;

import model.Accounts;
import model.Users;

import java.util.List;

/**
 * Accounts DAO interface to direct implementing class specific Data operations with database
 */
public interface IAccounts <T, ID> {
    void updateBalance (Accounts obj, float amount);
    T findByAccount(String email);
    List<T> findAll();


}
