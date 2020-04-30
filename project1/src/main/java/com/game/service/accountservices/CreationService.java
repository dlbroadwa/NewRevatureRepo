package com.game.service.accountservices;

import com.game.system.AuthenticationStatus;

public interface CreationService {
    void signUp(String username, String password, String email);
    boolean checkValidity(String username,String password,String email);
    void delete(String username);
    void close();
}
