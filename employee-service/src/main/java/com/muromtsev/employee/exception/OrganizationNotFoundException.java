package com.muromtsev.employee.exception;

import org.springframework.http.HttpStatus;

public class OrganizationNotFoundException extends BaseException {
    public OrganizationNotFoundException(String uuid) {
        super(
                HttpStatus.NOT_FOUND,
                "EMPLOYEE_NOT_FOUND",
                String.format("Organization with uuid %d not found", uuid)
        );
    }
}
