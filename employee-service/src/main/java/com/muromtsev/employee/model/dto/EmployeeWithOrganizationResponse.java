package com.muromtsev.employee.model.dto;

import com.muromtsev.employee.model.OrganizationInfo;
import jakarta.annotation.Nullable;

public record EmployeeWithOrganizationResponse(
        EmployeeResponse employeeResponse,
        @Nullable OrganizationInfo organizationInfo

) {
}
