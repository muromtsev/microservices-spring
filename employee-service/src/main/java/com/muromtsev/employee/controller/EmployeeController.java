package com.muromtsev.employee.controller;

import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.model.dto.EmployeeRequest;
import com.muromtsev.employee.model.dto.EmployeeResponse;
import com.muromtsev.employee.model.dto.EmployeeWithOrganizationResponse;
import com.muromtsev.employee.model.mapper.EmployeeMapper;
import com.muromtsev.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/employees/")
@Tag(name = "Сервис сотрудников", description = "Методы CRUD для сотрудников")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping
    @Operation(
            method = "POST",
            summary = "Создать нового сотрудника",
            description = "Создает нового сотрудника в системе. При успешном создании возвращает данные сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping(value = "/{employeeId}")
    @Operation(
            method = "GET",
            summary = "Получить сотрудника по Id",
            description = "Получает сотрудника по Id. При успешном запросе возвращает сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник успешно найден"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<EmployeeResponse> getEmployee(
            @PathVariable("employeeId") int employeeId) {
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    @GetMapping
    @Operation(
            method = "GET",
            summary = "Возвращает всех сотрудников",
            description = "Возвращает всех сотрудников",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудники успешно найдены"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{employeeId}")
    @Operation(
            method = "PUT",
            summary = "Обновляет сотрудника по Id",
            description = "Обновляет сотрудника по Id и возвращает обновленного сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник успешно обновлен"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("employeeId") int employeeId,
            @Valid @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeRequest));
    }


    @DeleteMapping(value = "/{employeeId}")
    @Operation(
            method = "DELETE",
            summary = "Удаляет сотрудника по Id",
            description = "Удаляет сотрудника по Id и ничего не возвращает",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник успешно удален"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping(value = "/{employeeId}/with-organization")
    public ResponseEntity<EmployeeWithOrganizationResponse> getEmployeeWithOrganization(
            @PathVariable("employeeId") int employeeId,
            @RequestParam(value = "clientType", required = false) String clientType
    ) {
        return ResponseEntity.ok(employeeService.getEmployeeWithOrganization(employeeId, clientType));
    }

    @GetMapping(value = "/organization")
    @Operation(
            method = "GET",
            summary = "Получить всех сотрудников по UUID организации",
            description = "Получает всех сотрудников по UUID организации. " +
                    "При успешном запросе возвращает всех сотрудников определенной организации",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудники успешно найдены"),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные данные"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByOrganizationUuid(
            @RequestParam(defaultValue = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
                    @Parameter(
                            description = "UUID организации",
                            example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890"
                    )
            String organizationUuid) throws TimeoutException {
        List<EmployeeResponse> responseList = employeeService.getEmployeesByOrganizationUuid(organizationUuid);
        return ResponseEntity.ok(responseList);

    }
}
