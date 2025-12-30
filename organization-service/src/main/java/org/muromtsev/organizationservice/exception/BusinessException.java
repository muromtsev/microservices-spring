package org.muromtsev.organizationservice.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(HttpStatus.CONFLICT, "BUSINESS_RULE_VIOLATION", message);
    }

    public static BusinessException organizationHasEmployees(String orgName) {
        return new BusinessException(
                String.format("Organization '%s' cannot be deleted is has active employees", orgName)
        );
    }
}
