package com.hunger.saviour.portal.util;

import com.hunger.saviour.portal.exceptions.UnauthorizedException;

/**
 * TokenUtils provides utility methods for token extraction and basic validation logic.
 */
public class TokenUtils {

    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Extracts and validates a Bearer token from the Authorization header.
     *
     * @param authorizationHeader The Authorization header from the HTTP request.
     * @return The extracted token, stripped of the Bearer prefix.
     * @throws UnauthorizedException If the header is invalid.
     */
    public static String extractToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            throw new UnauthorizedException("Invalid Authorization Header");
        }
        return authorizationHeader.substring(TOKEN_PREFIX.length()).trim();
    }
}