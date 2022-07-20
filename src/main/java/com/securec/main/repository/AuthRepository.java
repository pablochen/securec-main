package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByAuthCode(String authCode);
    Optional<List<Auth>> findAllByAuthCodeContains(String authCode);

    Optional<Auth> findByAuthName(String authName);
    Optional<List<Auth>> findAllByAuthNameContains(String authName);

    Optional<Auth> getByAuthCode(String authCode);

}