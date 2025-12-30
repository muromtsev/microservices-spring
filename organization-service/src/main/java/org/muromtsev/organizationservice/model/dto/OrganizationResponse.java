package org.muromtsev.organizationservice.model.dto;

import java.time.LocalDateTime;


public record OrganizationResponse(
        String uuid,
        String name,
        String code,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
