package service;

import model.User;

/**
 * UserService interface to be implemented
 */
public interface UserService {
    User checkCredentials(String username, String password);
    boolean changePassword(String password, User user);
    boolean changeFirstName(String first, User user);
    boolean changeLastName(String last, User user);

    User findByID(String username);
    void closeAccount(User user);
    void createAccount(String username, String password, String firstName, String lastName);
}
