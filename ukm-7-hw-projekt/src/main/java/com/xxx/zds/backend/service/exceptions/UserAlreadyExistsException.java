package com.xxx.zds.backend.service.exceptions;

/**
 *
 * @author J. Loutocky
 * @email jaroslav.loutocky@cgi.com
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
