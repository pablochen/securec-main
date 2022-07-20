package com.securec.main.serviceImpl;


import com.securec.main.domain.Auth;
import com.securec.main.repository.AuthRepository;
import com.securec.main.service.AuthCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthCrudServiceImpl implements AuthCrudService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public boolean createAuth(Auth auth){
        try{
            authRepository.save(auth);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public Optional<Auth> findAuthByAuthCode(String authCode){
        return authRepository.findByAuthCode(authCode);
    }

    @Override
    public Optional<List<Auth>> findAllAuthsByAuthCode(String authCode){
        return authRepository.findAllByAuthCodeContains(authCode);
    }

    @Override
    public Optional<Auth> findAuthByAuthName(String authName){
        return authRepository.findByAuthName(authName);
    }

    @Override
    public Optional<List<Auth>> findAllAuthsByAuthName(String authName){
        return authRepository.findAllByAuthNameContains(authName);
    }

    @Override
    public Auth updateAuth(String authCode, Auth auth){
        final Optional<Auth> fetchedAuth = authRepository.findByAuthCode(authCode);
        if(fetchedAuth.isPresent()){
            auth.setAuthCode(authCode);
            return authRepository.save(auth);
        }
        else return null;
    }

    @Override
    public Auth patchAuth(String authCode, Auth auth){
        final Optional<Auth> fetchedAuth = authRepository.findByAuthCode(authCode);
        if(fetchedAuth.isPresent()){
            if(!auth.authOrdIsNull()){
                fetchedAuth.get().setAuthOrd(auth.getAuthOrd());
            }
            if(!auth.authNameIsNull()){
                fetchedAuth.get().setAuthName(auth.getAuthName());
            }
            return authRepository.save(fetchedAuth.get());
        }
        else return null;
    }

    @Override
    public Optional<Auth> getAuthByAuthCode(String authCode) {
        return authRepository.getByAuthCode(authCode);
    }

}