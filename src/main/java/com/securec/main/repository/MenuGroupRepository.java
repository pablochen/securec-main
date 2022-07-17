package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, UUID> {
    MenuGroup findByMenuGroupCode(String menuGroupCode);
}