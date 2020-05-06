package com.game.service.accountservices;

import com.game.models.Account;

import java.util.List;

public class FriendServiceImp implements FriendService {
    private final AccountDetailService accountDetailService;

    public FriendServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public boolean addFriend(String user, String friend) {
        if(accountDetailService.checkExist(friend)){
            Account temp = accountDetailService.getAccount(user);
            temp.getFriends().add(friend);
            accountDetailService.update(temp);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getFriends(String user) {
        return accountDetailService.getAccount(user).getFriends();
    }

    @Override
    public boolean removeFriend(String user, String friend) {
        Account temp = accountDetailService.getAccount(user);
        if (temp.getFriends().contains(friend)) {
            temp.getFriends().remove(friend);
            accountDetailService.update(temp);
            return true;
        }
        return false;
    }
}
