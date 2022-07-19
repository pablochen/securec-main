package com.securec.main.repository;

import com.securec.main.domain.Auth;
import com.securec.main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<List<User>> findAllByUserIdContains(String userId);
    Optional<User> findByUserId(String userId);
    Optional<User> deleteByUserId(String userId);

}