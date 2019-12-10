package ru.starokozhev.SocialManager.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.starokozhev.SocialManager.entity.User;
import ru.starokozhev.SocialManager.repository.UserRepository;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(@Lazy UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User userFromDb = userRepository.findUserByEmailOrLogin(username, username);

        if (userFromDb == null)
            throw new BadCredentialsException("Пользователь не найден");

        if ((userFromDb.getLogin().equals(username) || userFromDb.getEmail().equals(username))
                && passwordEncoder.matches(password, userFromDb.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password), Collections.emptyList());
        } else {
            throw new BadCredentialsException("Не правильный логин или пароль");
        }
    }
    @Override
    public boolean supports(Class<?>aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
