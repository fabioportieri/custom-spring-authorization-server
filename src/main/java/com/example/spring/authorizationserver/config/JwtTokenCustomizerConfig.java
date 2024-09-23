package com.example.spring.authorizationserver.config;

import com.example.spring.authorizationserver.security.AuthoritiesConstants;
import com.example.spring.authorizationserver.security.OidcUserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;
import java.util.Map;

import static org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames.ID_TOKEN;
import static org.springframework.security.oauth2.server.authorization.OAuth2TokenType.ACCESS_TOKEN;

@Configuration
public class JwtTokenCustomizerConfig {
    
    @Value("${client-id}")
    private String clientId;
    public static final List<String> ALL_ROLES = List.of(AuthoritiesConstants.USER,
            AuthoritiesConstants.EDITOR,
            AuthoritiesConstants.USER_MANAGER,
            AuthoritiesConstants.REVISER,
            AuthoritiesConstants.TEMPLATE_MANAGER,
            AuthoritiesConstants.CATEGORIES_MANAGER);
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(OidcUserInfoService userInfoService) {
        return (context) -> {
            context.getJwsHeader().type("jwt");
            if (!AuthorizationGrantType.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType())) {
                if (ID_TOKEN.equals(context.getTokenType().getValue()) || ACCESS_TOKEN.equals(context.getTokenType())) {
                    OidcUserInfo userInfo = userInfoService.loadUser(
                            context.getPrincipal().getName());
                    context.getClaims().claims(claims ->
                            claims.putAll(userInfo.getClaims()));
                    if (ACCESS_TOKEN.equals(context.getTokenType())) {
                        context.getClaims().audience(
                                List.of(
                                        context.getRegisteredClient().getClientId(),
                                        "api://default"
                                )
                        );
                    }
                }
            } else {
                if (context.getPrincipal().getName().equals(clientId)) {
                    context.getClaims()
                            .claim("roles", ALL_ROLES)
                            .claim("preferred_username", clientId)
                            .audience(
                                    List.of(
                                            context.getRegisteredClient().getClientId(),
                                            "api://default"
                                    )
                            );
                }
                
    //              serve per profilare in base a un client id 
    //                context.getClaims().claim("resource_access", Map.of(
    //                        "ms-amm-trasparente", Map.of(
    //                                "roles", ALL_ROLES
    //                        )
    //                ));
            }
        };
    }
}
