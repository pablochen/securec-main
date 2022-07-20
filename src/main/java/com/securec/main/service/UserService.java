package com.securec.main.service;


import com.securec.main.domain.Auth;
import com.securec.main.domain.User;
import com.securec.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User lockUser(String userId);
    public User unLockUser(String userId);
    public User dormantUser(String userId);
    public User unDormantUser(String userId);
    public User leaveUser(String userId);
    public User patchUserAuth(String userId, Auth auth);
}