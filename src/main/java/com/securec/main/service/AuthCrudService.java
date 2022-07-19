package com.securec.main.service;


import com.securec.main.domain.Auth;

import java.util.List;
import java.util.Optional;

public interface AuthCrudService {
    public boolean createAuth(Auth auth);
    public Optional<Auth> getAuthByAuthCode(String authCode);
    public Optional<List<Auth>> getAllAuthsByAuthCode(String AuthCode);
    public Optional<Auth> getAuthByAuthName(String authName);
    public Optional<List<Auth>> getAllAuthsByAuthName(String authName);
    public Auth updateAuth(String AuthCode, Auth auth);
    public Auth patchAuth(String AuthCode, Auth auth);

}