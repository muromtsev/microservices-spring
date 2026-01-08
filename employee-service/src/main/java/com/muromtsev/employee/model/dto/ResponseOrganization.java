package com.muromtsev.employee.model.dto;


public record ResponseOrganization(
        String uuid,
        String name,
        String code,
        String description
) {
}
