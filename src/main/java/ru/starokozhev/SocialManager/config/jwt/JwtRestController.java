package ru.starokozhev.SocialManager.config.jwt;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping("${jwt.api}")*/
public class JwtRestController {
    /*@Autowired
    private JwtService service;

    @PostMapping("token")
    public JwtResponse token(JwtRequest request) throws NotFoundException {
        return service.getToken(request);
    }

    @PostMapping("auth")
    public JwtResponse tokenAsBody(@RequestBody JwtRequest request) throws NotFoundException {
        return service.getToken(request);
    }*/
}
