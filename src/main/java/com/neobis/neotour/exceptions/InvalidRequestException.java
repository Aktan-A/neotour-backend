package com.neobis.neotour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRequestException extends ResponseStatusException {
    public InvalidRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
