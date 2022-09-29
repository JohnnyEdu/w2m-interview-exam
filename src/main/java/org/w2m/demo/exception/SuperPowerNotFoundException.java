package org.w2m.demo.exception;

import org.springframework.http.HttpStatus;

public class SuperPowerNotFoundException extends NotFoundException {

    public SuperPowerNotFoundException() {
        super(String.format("[%s] Super power not found", HttpStatus.NOT_FOUND.value()));
    }
}
