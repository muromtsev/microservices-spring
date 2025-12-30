package org.muromtsev.organizationservice.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.muromtsev.organizationservice.model.dto.OrganizationRequest;
import org.muromtsev.organizationservice.model.dto.OrganizationResponse;
import org.muromtsev.organizationservice.model.entity.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    void toOrganization(
            @MappingTarget Organization organization,
            OrganizationRequest organizationRequest);

    OrganizationResponse toOrganizationResponse(Organization organization);


}
