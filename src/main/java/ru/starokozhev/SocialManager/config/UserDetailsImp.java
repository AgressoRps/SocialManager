package ru.starokozhev.SocialManager.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.starokozhev.SocialManager.dto.UserWrapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImp implements UserDetails {
    private final UserWrapper user;

    private Set<GrantedAuthority> authorities = new HashSet<>();

    private String password;

    public UserDetailsImp(UserWrapper user, String password) {
        if (user == null)
            throw new NullPointerException("UserWrapper is null");

        this.user = user;
        this.password = password;
        //authorities.add(user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername () {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getDateClose() == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getDateClose() == null;
    }

    public UserWrapper getUser() {
        return user;
    }
}
