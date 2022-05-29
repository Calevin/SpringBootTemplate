package com.calevin.springbootapitemplate.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5560878765864962301L;

    public NotFoundException(Long id) {
        super("Could not find record with ID: " + id);
    }

    public NotFoundException() {
        super("Could not find record");
    }
}
