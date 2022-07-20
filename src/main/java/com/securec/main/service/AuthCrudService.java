package com.securec.main.service;


import com.securec.main.domain.Auth;

import java.util.List;
import java.util.Optional;

public interface AuthCrudService {
    public boolean createAuth(Auth auth);
    public Optional<Auth> findAuthByAuthCode(String authCode);
    public Optional<List<Auth>> findAllAuthsByAuthCode(String AuthCode);
    public Optional<Auth> findAuthByAuthName(String authName);
    public Optional<List<Auth>> findAllAuthsByAuthName(String authName);
    public Auth updateAuth(String AuthCode, Auth auth);
    public Auth patchAuth(String AuthCode, Auth auth);
    public Optional<Auth> getAuthByAuthCode(String AuthCode);

}