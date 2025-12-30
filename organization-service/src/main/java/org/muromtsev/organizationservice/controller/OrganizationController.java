package org.muromtsev.organizationservice.controller;


import org.muromtsev.organizationservice.dto.RequestOrganization;
import org.muromtsev.organizationservice.model.Organization;
import org.muromtsev.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(value = "{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") Integer orgaizationId){
        return ResponseEntity.ok(organizationService.findById(orgaizationId));
    }

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody RequestOrganization organization){
        return ResponseEntity.ok(organizationService.create(organization));
    }

    @PutMapping
    public ResponseEntity<Organization> updateOrganization(@RequestBody Organization organization){
        return ResponseEntity.ok(organizationService.update(organization));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@RequestBody Organization organization){
        organizationService.deleteById(organization.getId());
    }

}
