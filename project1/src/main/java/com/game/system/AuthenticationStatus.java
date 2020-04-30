package com.game.system;

import com.game.models.PersonalInfo;

public interface AuthenticationStatus {
    void authStatus(PersonalInfo u, boolean success);
}