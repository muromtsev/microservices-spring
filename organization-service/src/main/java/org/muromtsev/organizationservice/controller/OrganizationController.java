package org.muromtsev.organizationservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.muromtsev.organizationservice.model.dto.OrganizationRequest;
import org.muromtsev.organizationservice.model.dto.OrganizationResponse;
import org.muromtsev.organizationservice.service.OrganizationService;
import org.muromtsev.organizationservice.validation.ValidationPatterns;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/organizations")
@Tag(name = "Сервис Организаций", description = "Методы CRUD для организаций")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    @Operation(
            method = "POST",
            summary = "Создать новую организацию",
            description = "Создает новую организацию в системе. При успешном создании возвращает данные созданной организации.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Организация успешно создана"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "409", description = "Организация с таким кодом уже существует"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<OrganizationResponse> createOrganization(@Valid @RequestBody OrganizationRequest organizationRequest) {
        return ResponseEntity.ok(organizationService.createOrganization(organizationRequest));
    }

    @GetMapping(value = "/{uuid}")
    @Operation(
            method = "GET",
            summary = "Получить организацию по UUID",
            description = "Получает организацию по заданному UUID. При успешном запросе возвращает заданную организацию.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Организация успешно найдена"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<OrganizationResponse> getOrganizationByUuid(
            @NotBlank @Pattern(regexp = ValidationPatterns.UUID) @PathVariable("uuid") String uuid){
        return ResponseEntity.ok(organizationService.getOrganizationByUuid(uuid));
    }

    @GetMapping(value = "/code/{code}")
    @Operation(
            method = "GET",
            summary = "Получить организацию по code",
            description = "Получает организацию по заданному code. При успешном запросе возвращает заданную организацию.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Организация успешно найдена"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<OrganizationResponse> getOrganizationByCode(
            @NotBlank @Pattern(regexp = ValidationPatterns.ORGANIZATION_CODE) @PathVariable("code") String code){
        return ResponseEntity.ok(organizationService.getOrganizationByCode(code));
    }

    @PostMapping(value = "/update/{uuid}")
    @Operation(
            method = "POST",
            summary = "Обновляет организацию по UUID",
            description = "Обновляет организацию по UUID и возвращает обновленную организацию",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Организация успешно обновлена"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
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
    @Operation(
            method = "DELETE",
            summary = "Удаляет организацию по UUID",
            description = "Удаляет организацию по UUID и ничего не возвращает",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Организация успешно удалена"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public void deleteOrganization(@NotBlank @Pattern(regexp = ValidationPatterns.UUID) @PathVariable("uuid") String uuid){
        organizationService.deleteOrganization(uuid);
    }

    @GetMapping(value = "/all")
    @Operation(
            method = "GET",
            summary = "Возвращает все организации",
            description = "Возвращает все организации",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Организации успешно найдены"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<List<OrganizationResponse>> getAllOrganizations(){
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }

}
