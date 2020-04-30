package com.game.service.accountservices;

import java.util.List;

public interface FriendService {

    void addFriend(String username);
    List<String> getFriends();
    void removeFriend(String username);

}
