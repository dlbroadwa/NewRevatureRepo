package com.ex.services;

import com.ex.dao.BookDAO;
import com.ex.dao.UserDAO;
import com.ex.models.Book;
import com.ex.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfoService {
    private final UserDAO uDao;
    private final BookDAO bDao;

    public UserInfoService(UserDAO ud, BookDAO bd) {
        this.uDao = ud;
        this.bDao = bd;
    }

    public User getUserInfo(int cardNumber) {
        if (cardNumber <= 0)
            return null;

        return uDao.getUserInfo(cardNumber);
    }

    public boolean updateUserInfo(int cardNumber, User newInfo) {
        if (cardNumber <= 0 || newInfo.getCardNumber() <= 0)
            return false;

        return uDao.update(cardNumber, newInfo);
    }

    public List<Book> getCheckedOutBooks(int cardNumber) {
        if (cardNumber <= 0)
            return new ArrayList<>();

        return bDao.getBooksCheckedOutBy(cardNumber);
    }
}
