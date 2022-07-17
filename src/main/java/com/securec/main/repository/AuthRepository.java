package com.securec.main.repository;

import com.securec.main.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Auth, UUID> {
    Auth findByAuthCode(String authCode);
}