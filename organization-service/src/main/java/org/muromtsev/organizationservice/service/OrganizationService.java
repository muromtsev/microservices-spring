package org.muromtsev.organizationservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.muromtsev.organizationservice.exception.OrganizationNotFoundException;
import org.muromtsev.organizationservice.model.dto.OrganizationRequest;
import org.muromtsev.organizationservice.model.entity.Organization;
import org.muromtsev.organizationservice.model.dto.OrganizationResponse;
import org.muromtsev.organizationservice.model.mapper.OrganizationMapper;
import org.muromtsev.organizationservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationResponse createOrganization(OrganizationRequest requestOrganization) {

        Organization organization = new Organization();
        organization.setCreatedAt(LocalDateTime.now());
        organization.setUpdatedAt(LocalDateTime.now());

        organizationMapper.toOrganization(organization, requestOrganization);

        Organization savedOrganization = organizationRepository.save(organization);

        log.info("Organization created successfully with UUID: {} and name: {}", organization.getUuid(), organization.getName());

        return organizationMapper.toOrganizationResponse(savedOrganization);
    }

    public OrganizationResponse getOrganizationByUuid(String uuid) {

        Organization organization = organizationRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found with uuid: " + uuid));

        log.info("Organization found successfully with UUID: {} and name: {}", organization.getUuid(), organization.getName());

        return organizationMapper.toOrganizationResponse(organization);
    }

    public OrganizationResponse getOrganizationByCode(String code) {

        Organization organization = organizationRepository.findByCode(code)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found with code: " + code));

        log.info("Organization found successfully with code: {} and name: {}", organization.getUuid(), organization.getName());

        return organizationMapper.toOrganizationResponse(organization);

    }

    public OrganizationResponse updateOrganization(String uuid, OrganizationRequest requestOrganization) {

        Organization organization = organizationRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found with uuid: " + uuid));

        organization.setName(requestOrganization.name());
        organization.setCode(requestOrganization.code());
        organization.setDescription(requestOrganization.description());

        Organization savedOrganization = organizationRepository.save(organization);

        log.info("Organization updated successfully with UUID: {} and name: {}", organization.getUuid(), organization.getName());

        return organizationMapper.toOrganizationResponse(savedOrganization);
    }

    public void deleteOrganization(String uuid) {

        Organization organization = organizationRepository.findByUuid(uuid)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found with uuid: " + uuid));

        organizationRepository.delete(organization);

        log.info("Organization deleted successfully with UUID: {} and name: {}", organization.getUuid(), organization.getName());

    }

    public List<OrganizationResponse> getAllOrganizations() {
        log.info("Fetching all organizations from database");
        List<OrganizationResponse> result = organizationRepository.findAll()
                .stream()
                .map(organizationMapper::toOrganizationResponse)
                .toList();

        log.info("Found {} organizations", result.size());

        return result;

    }

}
