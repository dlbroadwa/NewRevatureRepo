package com.ex.keeper_dao;

import java.util.List;

public interface UserDAO<U,P> {
    List<U> findAll();
}
