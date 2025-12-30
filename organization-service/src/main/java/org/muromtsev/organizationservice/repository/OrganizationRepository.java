package org.muromtsev.organizationservice.repository;

import org.muromtsev.organizationservice.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

    Optional<Organization> findByUuid(String uuid);
    Optional<Organization> findByCode(String code);

}
