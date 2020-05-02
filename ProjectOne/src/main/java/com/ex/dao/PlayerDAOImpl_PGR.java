package com.ex.dao;

import com.ex.model.Person;
import com.ex.model.Player;
import com.ex.model.Position;
import com.ex.model.Team;
import com.ex.service.ConnectionService;
import com.ex.service.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDAOImpl_PGR implements PlayerDOA{
    private ConnectionService connectionSvc;

    public PlayerDAOImpl_PGR() {
        connectionSvc = new PostgreSQLConnection();
    } {



    }

    @Override
    public void addPlayer(Player player) {

    }
}
