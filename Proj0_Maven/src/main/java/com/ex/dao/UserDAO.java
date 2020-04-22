package com.ex.dao;

import com.ex.models.User;

import java.util.List;

public interface UserDAO {
    /**
     * Adds a user to the database.
     * @param user the user to be added
     * @return <code>true</code> if the user was successfully added, <code>false</code> otherwise.
     */
    boolean add(User user);

    /**
     * Removes a user from the database.
     * @param cardNumber the library card number of the user to be removed
     * @return <code>true</code> if a user was deleted, <code>false</code> otherwise
     */
    boolean remove(int cardNumber);

    /**
     * Updates the information for a user in the database.
     * @param cardNumber the library card number of the user
     * @param newUserInfo the new user information
     * @return <code>true</code> if the update was successful, <code>false</code> otherwise.
     */
    boolean update(int cardNumber, User newUserInfo);

    /**
     * Retrieve the user with the specified library card number.
     * @param cardNumber the library card number of the user
     * @return the information for that user, or <code>null</code> if a user with that
     *      card number could not be found.
     */
    User getUserInfo(int cardNumber);

    /**
     * Searches for a user based on name, ignoring case.
     * @param query the name to search for
     * @return a list of all users whose name contains the given query.
     */
    List<User> findUser(String query);
}
