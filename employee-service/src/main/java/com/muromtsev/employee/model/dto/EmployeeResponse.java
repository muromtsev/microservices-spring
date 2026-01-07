package com.muromtsev.employee.model.dto;


import java.time.LocalDateTime;

public record EmployeeResponse(
        String firstName,
        String lastName,
        String email,
        String position,
        String organizationUuid,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
