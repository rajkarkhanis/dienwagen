package com.dienwagen.auth.repository;

import com.dienwagen.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByUserId(Integer userId);

    Optional<Token> findByValue(String tokenValue);
}