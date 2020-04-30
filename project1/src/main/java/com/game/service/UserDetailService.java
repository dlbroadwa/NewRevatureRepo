package com.game.service;

import com.game.models.PersonalInfo;

public interface UserDetailService {
    PersonalInfo getUserByEmail(String email);
    PersonalInfo getUserByUsername(String username);
}
