package com.ex.DAO;

import com.ex.MainAndMenu.Runner;

/*DAO intended to act as a switch between FileIO and SQL*/

public interface DAO {

    DAO doDAO(Runner anInterface);

}
