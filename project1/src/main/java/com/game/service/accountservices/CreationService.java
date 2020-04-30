package com.game.service.accountservices;

import com.game.system.AuthenticationStatus;

public interface CreationService {
    void signUp(String username, String password, String email, AuthenticationStatus onStatusChanged);
    boolean checkValidity(String username,String password,String email);
    void delete(String username);
}
