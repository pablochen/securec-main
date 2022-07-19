package com.securec.main.serviceImpl;


import com.securec.main.domain.User;
import com.securec.main.repository.UserRepository;
import com.securec.main.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser(User user){
        try{
            userRepository.save(user);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public Optional<User> getUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    @Override
    public Optional<List<User>> getUsersByUserId(String userId){
        return userRepository.findAllByUserIdContains(userId);
    }

    @Override
    public User updateUser(String userId, User user){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            user.setUserId(userId);
            return userRepository.save(user);
        }
        else return null;
    }

    @Override
    public User patchUser(String userId, User user){
        final Optional<User> fetchedUser = userRepository.findByUserId(userId);
        if(fetchedUser.isPresent()){
            if(!user.userNameIsNull()){
                fetchedUser.get().setUserName(user.getUserName());
            }
            if(!user.nickNameIsNull()){
                fetchedUser.get().setNickName(user.getNickName());
            }
            if(!user.authIsNull()){
                fetchedUser.get().setAuth(user.getAuth());
            }
            return userRepository.save(fetchedUser.get());
        }
        else return null;
    }
}