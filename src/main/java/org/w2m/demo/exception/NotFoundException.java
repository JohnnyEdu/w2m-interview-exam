package org.w2m.demo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super(String.format("[%s] Entity not found", HttpStatus.NOT_FOUND.value()));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
