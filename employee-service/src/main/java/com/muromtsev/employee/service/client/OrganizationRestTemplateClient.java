package com.muromtsev.employee.service.client;

import com.muromtsev.employee.exception.OrganizationNotFoundException;
import com.muromtsev.employee.model.OrganizationInfo;
import com.muromtsev.employee.model.dto.ResponseOrganization;
import com.muromtsev.employee.model.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrganizationRestTemplateClient implements OrganizationClient {

    private final RestTemplate restTemplate;
    private final EmployeeMapper employeeMapper;

    public Optional<OrganizationInfo> getOrganization(String organizationUuid) {

        try {
            ResponseEntity<ResponseOrganization> restExchange = restTemplate.exchange(
                    "http://organization-service/v1/organizations/{uuid}",
                    HttpMethod.GET,
                    null,
                    ResponseOrganization.class,
                    organizationUuid);

            if (restExchange.getStatusCode().is2xxSuccessful() &&  restExchange.getBody() != null) {
                log.info("[Organization RestTemplate Client] Organization retrieved successfully: {}", restExchange.getStatusCode());
                return Optional.of(employeeMapper.toOrganizationInfo(restExchange.getBody()));
            }
            log.info("[Organization RestTemplate Client] Organization not found: {}", restExchange.getStatusCode());
            return Optional.empty();

        } catch (HttpClientErrorException.NotFound e) {
            log.warn("[Organization RestTemplate Client] Organization Not Found: {}", organizationUuid);
            return Optional.empty();

        } catch (Exception e) {
            log.error("[Organization RestTemplate Client] Error getting Organization Info {}: {}", organizationUuid, e.getMessage());
            throw new OrganizationNotFoundException(organizationUuid);
        }
    }

}
