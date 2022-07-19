package com.securec.main.service;


import com.securec.main.domain.User;
import com.securec.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserCrudService {
    public boolean createUser(User user);
    public Optional<User> getUserByUserId(String userId);
    public Optional<List<User>> getUsersByUserId(String userId);
    public User updateUser(String userId, User user);
    public User patchUser(String userId, User user);
}