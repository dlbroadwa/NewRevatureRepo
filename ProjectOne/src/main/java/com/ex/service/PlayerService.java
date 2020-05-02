package com.ex.service;

import com.ex.dao.PlayerDAOImpl_PGR;
import com.ex.dao.PlayerDOA;
import com.ex.model.Player;

public class PlayerService {
    private PlayerDOA playerDOA;
    public PlayerService(){
        this.playerDOA = new PlayerDAOImpl_PGR();
    }
    public boolean addPlayer(Player player){
        try{
            playerDOA.addPlayer(player);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
