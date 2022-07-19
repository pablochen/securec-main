package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.AuthMenuGroup;
import com.securec.main.domain.MenuGroup;
import com.securec.main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthMenuGroupRepository extends JpaRepository<AuthMenuGroup, UUID> {
    /*
    Optional<List<AuthMenuGroup>> findAllByAuthCodeContains(String authCode);
    Optional<List<AuthMenuGroup>> findAllByMenuGroupCodeContains(String menuGroupCode);
    Optional<AuthMenuGroup> deleteByAuthCode(String authCode);
    Optional<AuthMenuGroup> deleteByMenuGroupCode(String menuGroupCode);
    */
}