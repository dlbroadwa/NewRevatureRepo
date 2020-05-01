package com.game.service.accountservices;

import java.util.List;

public class FriendServiceImp implements FriendService {
    private final AccountDetailService accountDetailService;

    public FriendServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void addFriend(String user, String friend) {
        accountDetailService.getAccount(user).getFriends().add(friend);
    }

    @Override
    public List<String> getFriends(String user) {
        return accountDetailService.getAccount(user).getFriends();
    }

    @Override
    public void removeFriend(String user, String friend) {
        accountDetailService.getAccount(user).getFriends().remove(friend);
    }
}
