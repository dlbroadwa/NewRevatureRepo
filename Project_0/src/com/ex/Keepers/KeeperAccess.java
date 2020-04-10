package com.ex.Keepers;
import com.ex.DAO.FileIoDAO;
import com.ex.MainAndMenu.*;

import java.util.List;
import java.util.Scanner;

/*KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals*/

public class KeeperAccess implements Screen {
    public Screen doScreen(Runner anInterface) {

        return new UserScreeningScreen();
    }

//    @Override
//    public Integer save(Object o) {
//        return null;
//    }
//
//    @Override
//    public Object getById(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void update(Object o, Integer id) {
//
//    }
//
//    @Override
//    public void delete(Object o) {
//
//    }
//
//    @Override
//    public List<Object> getAll() {
//        return null;
//    }
}
