package com.muromtsev.employee.controller;

import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("employeeId") String employeeId) {

        Employee employee = employeeService.getEmployee(employeeId, organizationId);
        return ResponseEntity.ok().body(employee);

    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(
            @PathVariable("organizationId") String organizationId,
            @RequestBody Employee requestEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(requestEmployee, organizationId));

    }

    @PostMapping
    public ResponseEntity<String> createEmployee(
            @PathVariable("organizationId") String organizationId,
            @RequestBody Employee requestEmployee) {
        return ResponseEntity.ok(employeeService.createEmployee(requestEmployee, organizationId));

    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("employeeId")  String employeeId) {
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId, organizationId));

    }

}
