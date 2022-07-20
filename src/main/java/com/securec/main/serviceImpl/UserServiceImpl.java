package com.securec.main.serviceImpl;


import com.securec.main.domain.Auth;
import com.securec.main.domain.User;
import com.securec.main.repository.UserRepository;
import com.securec.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 유저 상태변환 서비스
     */
    @Override
    public User lockUser(String userId){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().lock();
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }

    @Override
    public User unLockUser(String userId){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().unLock();
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }

    @Override
    public User dormantUser(String userId){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().dormant();
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }

    @Override
    public User unDormantUser(String userId){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().unDormant();
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }

    @Override
    public User leaveUser(String userId){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().leave();
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }

    @Override
    public User patchUserAuth(String userId, Auth auth) {
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            fetchedUser.get().setAuth(auth);
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }

}