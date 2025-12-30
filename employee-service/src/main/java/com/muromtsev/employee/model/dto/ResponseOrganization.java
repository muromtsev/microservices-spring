package com.muromtsev.employee.model.dto;

public record ResponseOrganization(
        String name,
        String contactName,
        String contactEmail,
        String contactPhone
) {
}
