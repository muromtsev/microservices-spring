package com.muromtsev.employee.service.client;

import com.muromtsev.employee.model.dto.ResponseOrganization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("organization-service")
public interface OrganizationFeignClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/organizations/{uuid}",
            consumes = "application/json"
    )
    ResponseOrganization getOrganization(@PathVariable("uuid") String organizationUuid);

}
