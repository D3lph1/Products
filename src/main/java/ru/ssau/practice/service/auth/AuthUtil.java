package ru.ssau.practice.service.auth;

import org.springframework.security.core.Authentication;
import ru.ssau.practice.entity.User;

public final class AuthUtil
{
    /**
     * Private constructor to ensure non-instantiation because this class
     * contains only static methods.
     */
    private AuthUtil()
    {
    }

    public static User retrieveUser(Authentication auth)
    {
        UserContainer userContainer = (UserContainer) auth.getPrincipal();
        if (userContainer == null) {
            throw new IllegalArgumentException("Authentication can not be null");
        }

        return userContainer.getUser();
    }
}
