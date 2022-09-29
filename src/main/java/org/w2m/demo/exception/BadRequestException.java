package org.w2m.demo.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(String.format("[%s] Bad request, %s", HttpStatus.BAD_REQUEST.value(), message));
    }
}
