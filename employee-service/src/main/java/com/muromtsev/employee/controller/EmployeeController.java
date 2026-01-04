package com.muromtsev.employee.controller;

import com.muromtsev.employee.model.dto.EmployeeRequest;
import com.muromtsev.employee.model.dto.EmployeeResponse;
import com.muromtsev.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/employees/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(
            @PathVariable("employeeId") int employeeId) {
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("employeeId") int employeeId,
            @Valid @RequestBody EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeRequest));
    }


    @DeleteMapping(value = "/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
