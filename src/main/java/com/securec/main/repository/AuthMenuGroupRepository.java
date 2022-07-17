package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.AuthMenuGroup;
import com.securec.main.domain.MenuGroup;
import com.securec.main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthMenuGroupRepository extends JpaRepository<AuthMenuGroup, UUID> {
    AuthMenuGroup findByAuth(Auth auth);
    MenuGroup findByMenuGroup(MenuGroup menuGroup);
}