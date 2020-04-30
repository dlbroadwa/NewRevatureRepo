package com.game.service.accountservices;

import com.game.system.AuthenticationStatus;

public interface UserAuthentication {
    void authenticate(String username, String password,
                      AuthenticationStatus onStatusChange);
}