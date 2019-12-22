package ru.starokozhev.SocialManager.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.starokozhev.SocialManager.repository.UserRepository;
import ru.starokozhev.SocialManager.service.UserService;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.signing-key}")
    private String signingKey;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final String JWT_USER_BLOCKED = "Аккаунт заблокирован";

    private static final String JWT_USER_NOT_FOUND = "Пользователь не найден";

    private static final String JWT_USER_WRONG_PASSWORD = "Неверный пароль";

    public JwtResponse getToken(JwtRequest request) throws NotFoundException {
        UserDetails user;

        String login = request.getLogin();
        String password = request.getPassword();

        user = userService.loadUserByUsername(login);

        try {

            if (user == null)
                throw new BadCredentialsException("Пользователь не найден");

            if (!(user.getUsername().toUpperCase().equals(login.toUpperCase()) ||
                    user.getUsername().toUpperCase().equals(login.toUpperCase()))) {
                throw new IllegalArgumentException("Отсутствуют данные");
            }

            if (!encoder.matches(password, user.getPassword()))
                throw new IllegalArgumentException(JWT_USER_WRONG_PASSWORD);

        } catch (UsernameNotFoundException ex) {
            throw new NotFoundException(JWT_USER_NOT_FOUND);
        }

        if (!user.isAccountNonLocked())
            throw new IllegalArgumentException(JWT_USER_BLOCKED);

        if (!user.isEnabled())
            throw new IllegalArgumentException(JWT_USER_BLOCKED);

        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MONTH, 1);

        long creationDateUnix = new Date().getTime();

        Claims jwtClaims = new DefaultClaims();
        jwtClaims.put(Claims.SUBJECT, user.getUsername());
        jwtClaims.put(Claims.EXPIRATION, expirationDate.getTime());
        jwtClaims.setExpiration(expirationDate.getTime());

        JwtBuilder builder = Jwts.builder();
        builder.setExpiration(expirationDate.getTime());
        builder.setClaims(jwtClaims);

        String token = builder.signWith(SignatureAlgorithm.HS256, signingKey).compact();

        return new JwtResponse()
                .setCreationDate(creationDateUnix)
                .setExpirationDate(expirationDate.getTime().getTime())
                .setToken(token)
                .setUser(user.getUsername());
    }
}
