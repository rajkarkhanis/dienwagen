package com.dienwagen.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.dienwagen.auth.dto.AuthRequest;
import com.dienwagen.auth.dto.ChangePasswordDto;
import com.dienwagen.auth.dto.TokenResponse;
import com.dienwagen.auth.dto.UserDto;
import com.dienwagen.auth.entity.User;
import com.dienwagen.auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final DealerService dealerService;
    private final RoleService roleService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    // Save new user
    public TokenResponse saveUser(UserDto userDto) {
        User savedUser = userRepository.save(buildUser(userDto));
        String token = jwtService.generateToken(savedUser);
        tokenService.saveToken(savedUser, token);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return tokenResponse;
    }

    // Login user
    public TokenResponse authenticateUser(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        tokenService.revokeToken(user);
        tokenService.saveToken(user, jwt);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwt);
        return tokenResponse;
    }

    // Change password
    public TokenResponse changePassword(ChangePasswordDto changePasswordDto) {

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            return null;
        }

        User user = userRepository.findByUsername(changePasswordDto.getUsername()).orElseThrow();
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        String jwt = jwtService.generateToken(user);

        tokenService.revokeToken(user);
        tokenService.saveToken(user, jwt);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwt);

        return tokenResponse;
    }

    // Convert to Entity
    public User buildUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setDealer(dealerService.getAnyDealer());
        user.setRole(roleService.getRoleByName("CUSTOMER"));
        return user;
    }
}
