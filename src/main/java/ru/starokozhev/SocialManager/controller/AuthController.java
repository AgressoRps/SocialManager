package ru.starokozhev.SocialManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.starokozhev.SocialManager.config.AuthenticationManager;
import ru.starokozhev.SocialManager.config.jwt.JwtResponse;
import ru.starokozhev.SocialManager.config.jwt.JwtService;
import ru.starokozhev.SocialManager.dto.UserWrapper;
import ru.starokozhev.SocialManager.repository.UserRepository;

import javax.validation.Valid;

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/signin")
    private UserWrapper authenticateUser(UserWrapper wrapper) {

        return null;

    }

}
