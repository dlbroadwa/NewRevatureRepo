package com.game.service;

import com.game.system.AuthenticationStatus;

public interface UserAuthentication {
    void authenticate(String username, String password,
                      AuthenticationStatus onStatusChange);
}