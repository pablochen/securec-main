package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.Menu;
import com.securec.main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {
    Optional<Menu> findByMenuCode(String menuCode);
    Optional<List<Menu>> findAllByMenuCodeContains(String menuCode);

    Optional<Menu> findByMenuName(String menuName);
    Optional<List<Menu>> findAllByMenuNameContains(String menuName);
}