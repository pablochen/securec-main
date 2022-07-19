package com.securec.main.service;


import com.securec.main.domain.Auth;
import com.securec.main.domain.AuthMenuGroup;

import java.util.List;
import java.util.Optional;

public interface AuthMenuGroupCrudService {
    public boolean createAuthMenuGroup(Auth auth);
    public Optional<List<AuthMenuGroup>> getAllAuthMenuGroupsByAuthCode(String authCode);
    public Optional<List<AuthMenuGroup>> getAllAuthMenuGroupsByMenuGroupCode(String menuGroupCode);
    public AuthMenuGroup deleteAuthMenuGroup(String authCode, String menuGroupCode);

}