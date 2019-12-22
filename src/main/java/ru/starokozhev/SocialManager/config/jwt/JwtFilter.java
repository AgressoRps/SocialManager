package ru.starokozhev.SocialManager.config.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (token == null) {
            token = request.getParameter("token");
        }

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token.trim().isEmpty()) {
            log.warn("{} supplied an empty token", request.getRemoteAddr());
            filterChain.doFilter(request, response);
            return;
        }


        JwtAuthentication authentication = new JwtAuthentication(token);
        try {
            authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AccessDeniedException ex) {
            log.error("Authentication failed from {}, cause: {}", request.getRemoteAddr(), ex);
            response.sendError(403, ex.getMessage());
            /*catch (AccessDeniedException | InvalidDataException ex) {

            log.error("Authentication failed from {}, cause: {}", request.getRemoteAddr(), ex);

            if (ex instanceof AccessDeniedException)
                response.sendError(403, ex.getMessage());
            else if (ex instanceof InvalidDataException) response.sendError(401, ex.getMessage());

            return;
        }*/
        } catch (IllegalArgumentException e) {
            response.sendError(401, e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
