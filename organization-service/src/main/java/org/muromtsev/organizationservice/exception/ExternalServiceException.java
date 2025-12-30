package org.muromtsev.organizationservice.exception;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends BaseException{

    public ExternalServiceException(String serviceName, String message) {
        super(
                HttpStatus.SERVICE_UNAVAILABLE,
                "EXTERNAL_SERVICE_ERROR",
                String.format("Service '%s' error: %s", serviceName, message)
        );
    }
}
