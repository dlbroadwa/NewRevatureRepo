package com.game.service.accountservices;

public interface CreationService {
    boolean signUp(String username, String password, String email);
    void delete(String username);
}
