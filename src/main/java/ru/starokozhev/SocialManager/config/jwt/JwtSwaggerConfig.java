package ru.starokozhev.SocialManager.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

//@Configuration
public class JwtSwaggerConfig {

    /*@Autowired
    private Docket docket;

    @Value("${jwt.api}")
    private String api;

    @PostConstruct
    public void applySecurityConfig() {
        docket.securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(securitySchemes()));
    }

    private ApiKey securitySchemes() {
        return new ApiKey("JWT", "token", "query");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths((s) -> s != null && !s.startsWith(api))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }*/

}
