package com.ex.dao;

import com.ex.models.User;

import java.util.List;

public interface UserDAO {
    boolean add(User user);
    boolean remove(int cardNumber);
    boolean update(int cardNumber, User newUserInfo);

    User getUserInfo(int cardNumber);
    List<User> findLastName(String query);
    List<User> findFirstName(String query);
}
