package com.muromtsev.employee.service.client;

import com.muromtsev.employee.model.OrganizationInfo;

import java.util.Optional;

public interface OrganizationClient {

    Optional<OrganizationInfo> getOrganization(String organizationUuid);

}
