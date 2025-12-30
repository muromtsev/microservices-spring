package org.muromtsev.organizationservice.exception;

import org.springframework.http.HttpStatus;

public class OrganizationNotFoundException extends BaseException {

    public OrganizationNotFoundException(String uuid) {
        super(
                HttpStatus.NOT_FOUND,
                "ORGANIZATION_NOT_FOUND",
                String.format("Organization with UUID %s not found", uuid)
        );
    }

    public OrganizationNotFoundException(Long id) {
        super(
                HttpStatus.NOT_FOUND,
                "ORGANIZATION_NOT_FOUND",
                String.format("Organization with ID %s not found", id)
        );
    }
}
