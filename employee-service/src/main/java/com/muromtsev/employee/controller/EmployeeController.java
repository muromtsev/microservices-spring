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
            @PathVariable("organizationId") int organizationId,
            @PathVariable("employeeId") int employeeId) {

        Employee employee = employeeService.getEmployee(employeeId, organizationId, "");
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee requestEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(requestEmployee));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee requestEmployee) {
        return ResponseEntity.ok(employeeService.createEmployee(requestEmployee));
    }
    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") int employeeId) {
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
    }
}
