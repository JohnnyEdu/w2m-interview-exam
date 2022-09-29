package org.w2m.demo.exception;

public class DuplicatedSuperHeroException extends BadRequestException {

    public DuplicatedSuperHeroException() {
        super("this super hero already exists");
    }
}
