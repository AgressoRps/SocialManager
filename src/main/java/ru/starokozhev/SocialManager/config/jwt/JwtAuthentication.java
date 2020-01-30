package ru.starokozhev.SocialManager.config.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*@NoArgsConstructor
@Data
@Accessors(chain = true)*/
public class JwtAuthentication { /*implements Authentication {*/

    /*private String token = null;
    private boolean isAuthenticated = false;
    private UserDetails principal = null;

    public JwtAuthentication(String token) {
        this.token = token;
        isAuthenticated = true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return principal.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        if (principal != null)
            return principal.getUsername();
        else
            return null;
    }*/

}
