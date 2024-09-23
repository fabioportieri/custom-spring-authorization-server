package com.example.spring.authorizationserver.security;

import com.example.spring.authorizationserver.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

public class OidcUserInfoService {
    private final UserDetailsService userDetailsService;

    public OidcUserInfoService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public OidcUserInfo loadUser(String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        return OidcUserInfo.builder()
                .subject(user.getIdentifier().toString())
                .name(user.getFirstName() + " " + user.getLastName())
                .givenName(user.getFirstName())
                .familyName(user.getLastName())
                .nickname(username)
                .preferredUsername(username)
                .profile("https://example.com/" + username)
                .website("https://example.com")
                .email(user.getEmail())
                .emailVerified(true)
                .claim("roles", user.getRoles())
                .zoneinfo("Europe/Rome")
                .locale("it-IT")
                .updatedAt("1970-01-01T00:00:00Z")
                .build();
    }
}
