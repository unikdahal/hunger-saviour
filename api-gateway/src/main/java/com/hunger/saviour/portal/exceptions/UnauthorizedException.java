
package com.hunger.saviour.portal.exceptions;


public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("User is not authorized to perform this action.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}