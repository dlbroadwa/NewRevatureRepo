package com.game.service.accountservices;

import com.game.system.AuthenticationStatus;

public interface CreationService {
    boolean signUp(String username, String password, String email);
    void delete(String username);
}
