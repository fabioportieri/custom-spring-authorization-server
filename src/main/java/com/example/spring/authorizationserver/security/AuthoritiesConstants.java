package com.example.spring.authorizationserver.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";
    public static final String USER_MANAGER = "ROLE_USER_MANAGER";
    public static final String EDITOR = "ROLE_EDITOR";
    public static final String REVISER = "ROLE_REVISER";
    public static final String TEMPLATE_MANAGER = "ROLE_TEMPLATE_MANAGER";
    public static final String CATEGORIES_MANAGER = "ROLE_CATEGORIES_MANAGER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {}
}
