package org.muromtsev.organizationservice.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.muromtsev.organizationservice.model.dto.OrganizationRequest;
import org.muromtsev.organizationservice.model.dto.OrganizationResponse;
import org.muromtsev.organizationservice.service.OrganizationService;
import org.muromtsev.organizationservice.validation.ValidationPatterns;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponse> createOrganization(@Valid @RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.ok(organizationService.createOrganization(organizationRequest));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<OrganizationResponse> getOrganizationByUuid(
            @NotBlank @Pattern(regexp = ValidationPatterns.UUID) @PathVariable("uuid") String uuid){
        return ResponseEntity.ok(organizationService.getOrganizationByUuid(uuid));
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<OrganizationResponse> getOrganizationByCode(
            @NotBlank @Pattern(regexp = ValidationPatterns.ORGANIZATION_CODE) @PathVariable("code") String code){
        return ResponseEntity.ok(organizationService.getOrganizationByCode(code));
    }

    @PostMapping(value = "/update/{uuid}")
    public ResponseEntity<OrganizationResponse> updateOrganization(
            @NotBlank
            @Pattern(message = "Не соответствует шаблону UUID =(",
                    regexp = ValidationPatterns.UUID)
            @PathVariable
            String uuid,
            @Valid @RequestBody OrganizationRequest organizationRequest){
        return ResponseEntity.ok(organizationService.updateOrganization(uuid, organizationRequest));
    }

    @DeleteMapping(value = "/{uuid}")
    public void deleteOrganization(@NotBlank @Pattern(regexp = ValidationPatterns.UUID) @PathVariable("uuid") String uuid){
        organizationService.deleteOrganization(uuid);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrganizationResponse>> getAllOrganizations(){
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }

}
