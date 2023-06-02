package com.dienwagen.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.dienwagen.auth.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogoutService implements LogoutHandler {
    private final TokenService tokenService;

    @Autowired
    public LogoutService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info(authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return;

        String jwt = authHeader.substring(7);
        Token foundToken = tokenService.findByTokenValue(jwt);
        tokenService.deleteToken(foundToken);
        SecurityContextHolder.clearContext();
    }
}
