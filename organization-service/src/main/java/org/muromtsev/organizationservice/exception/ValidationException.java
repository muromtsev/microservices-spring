package org.muromtsev.organizationservice.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseException {

    public ValidationException(String field, String message) {
        super(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                String.format("Field '%s': %s", field, message)
        );
    }

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message);
    }
}
