package com.game.service.accountservices;

import java.util.List;

public class FriendServiceImp implements FriendService {
    private AccountDetailService accountDetailService;

    public FriendServiceImp(AccountDetailService accountDetailService){
        this.accountDetailService = accountDetailService;
    }

    @Override
    public void addFriend(String username) {
        accountDetailService.getCurr().getFriends().add(username);
    }

    @Override
    public List<String> getFriends() {
        return accountDetailService.getCurr().getFriends();
    }

    @Override
    public void removeFriend(String username) {
        accountDetailService.getCurr().getFriends().remove(username);
    }
}
