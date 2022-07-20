package com.securec.main.service;


import com.securec.main.domain.Auth;
import com.securec.main.domain.AuthMenuGroup;
import com.securec.main.domain.MenuGroup;

import java.util.List;
import java.util.Optional;

public interface AuthMenuGroupCrudService {
    public boolean createAuthMenuGroup(Auth auth, MenuGroup menuGroup);
    public boolean deleteAuthMenuGroupByAuthAndMenuGroup(Auth auth, MenuGroup menuGroup);
    public boolean getAuthMenuGroupByAuthAndMenuGroup(Auth auth, MenuGroup menuGroup);
}