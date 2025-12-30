package com.muromtsev.employee.service.client;

import com.muromtsev.employee.model.dto.ResponseOrganization;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {

    RestTemplate restTemplate;

    public OrganizationRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public @Nullable ResponseOrganization getOrganization(int organizationId){
        ResponseEntity<ResponseOrganization> restExchange = restTemplate.exchange(
                "http://organization-service/v1/organization/{organizationId}",
                HttpMethod.GET,
                null,
                ResponseOrganization.class,
                organizationId);
        return restExchange.getBody();
    }

}
