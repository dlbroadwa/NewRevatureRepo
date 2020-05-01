package com.game.service.accountservices;

import java.util.List;

public interface FriendService {

    void addFriend(String user,String friend);
    List<String> getFriends(String user);
    void removeFriend(String user, String friend);

}
