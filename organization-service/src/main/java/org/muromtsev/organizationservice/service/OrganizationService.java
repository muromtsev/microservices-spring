package org.muromtsev.organizationservice.service;


import org.muromtsev.organizationservice.dto.RequestOrganization;
import org.muromtsev.organizationservice.model.Organization;
import org.muromtsev.organizationservice.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization findById(Integer id){
        Organization organization = organizationRepository.findById(id).orElse(null);
        return organization;
    }

    public Organization create(RequestOrganization requestOrganization) {
        Organization organization = new Organization();
        organization.setName(requestOrganization.name());
        organization.setContactName(requestOrganization.contactName());
        organization.setContactEmail(requestOrganization.email());
        organization.setContactPhone(requestOrganization.phone());
        return organizationRepository.save(organization);
    }

    public Organization update(Organization organization){
        return organizationRepository.save(organization);
    }

    public void deleteById(Integer id){
        organizationRepository.deleteById(id);
    }

}
