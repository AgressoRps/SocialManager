package ru.starokozhev.SocialManager.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationManager {

    Authentication authenticate(Authentication authentication) throws AuthenticationException;

}
