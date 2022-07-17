package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {
    Menu findByMenuCode(String menuCode);
}