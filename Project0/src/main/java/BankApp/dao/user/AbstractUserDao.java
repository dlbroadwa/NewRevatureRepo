package BankApp.dao.user;

import BankApp.dao.AbstractDao;
import BankApp.model.User;

public interface AbstractUserDao {

    User getUser(String username, String password);

    User getUser(int userId);

    boolean validUser(String username, String password);

    boolean updateAmount(int userId, float amount, boolean deposit);
}
