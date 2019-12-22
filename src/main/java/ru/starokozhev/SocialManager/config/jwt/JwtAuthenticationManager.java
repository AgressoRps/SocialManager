package ru.starokozhev.SocialManager.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.starokozhev.SocialManager.config.UserDetailsImp;
import ru.starokozhev.SocialManager.service.UserService;

import java.util.Date;

@Log4j2
@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${jwt.signing-key}")
    private String signingKey;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthentication) {
            return processAuthentication((JwtAuthentication) authentication);
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

    private Authentication processAuthentication(JwtAuthentication authentication) {
        String token = authentication.getToken();
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(signingKey).parse(token).getBody();

            if (claims.getExpiration().after(new Date()))
                return buildFullTokenAuthentication(authentication, claims);
            else
                throw new AccessDeniedException("Срок годности токена истек");

        } catch (Exception ex) {
            log.error(ex.toString());
            throw new IllegalArgumentException("Ошибка валидации токена");
        }
    }

    private JwtAuthentication buildFullTokenAuthentication(JwtAuthentication authentication, DefaultClaims claims) {
        String username = claims.getSubject();

        throwIfNull(username);

        UserDetailsImp user = (UserDetailsImp) userService.loadUserByUsername(username);

        if (!user.isAccountNonLocked())
            throw new AccessDeniedException("Аккаунт заблокирован");

        if (!user.isEnabled())
            throw new AccessDeniedException("Аккаунт заблокирован");

        userService.updateLastTimeAuth(user.getUser().getId());

        authentication.setAuthenticated(true);
        authentication.setPrincipal(user);
        return authentication;
    }

    private void throwIfNull(Object o) {
        if (o == null)
            throw new IllegalArgumentException("Неверный токен");
    }

}
