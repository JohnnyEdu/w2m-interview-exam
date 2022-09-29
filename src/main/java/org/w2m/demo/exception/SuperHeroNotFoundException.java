package org.w2m.demo.exception;

import org.springframework.http.HttpStatus;

public class SuperHeroNotFoundException extends NotFoundException {

    public SuperHeroNotFoundException() {
        super(String.format("[%s] Super hero not found", HttpStatus.NOT_FOUND.value()));
    }
}
