package org.muromtsev.organizationservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidUuidException extends BaseException {

    public InvalidUuidException(String invalidUuid) {
        super(
                HttpStatus.BAD_REQUEST,
                "INVALID_UUID",
                String.format("Invalid UUID format: '%s'. Expected format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                        invalidUuid
                ));
    }

    public InvalidUuidException(String invalidUuid, String fieldName) {
        super(
                HttpStatus.BAD_REQUEST,
                "INVALID_UUID_FORMAT",
                String.format("Field '%s' has invalid UUID format: '%s'",
                        fieldName, invalidUuid)
        );
    }
}
