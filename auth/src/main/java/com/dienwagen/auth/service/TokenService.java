package com.dienwagen.auth.service;

import com.dienwagen.auth.entity.Token;
import com.dienwagen.auth.entity.User;
import com.dienwagen.auth.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveToken(User user, String tokenValue) {
        Token token = new Token();
        token.setUser(user);
        token.setValue(tokenValue);
        tokenRepository.save(token);
    }

    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }

    public void revokeToken(User user) {
        Token foundToken = tokenRepository.findByUserId(user.getId()).orElseThrow();
        tokenRepository.delete(foundToken);
    }

    public Token findByTokenValue(String tokenValue) {
        return tokenRepository.findByValue(tokenValue).orElseThrow();
    }
}
