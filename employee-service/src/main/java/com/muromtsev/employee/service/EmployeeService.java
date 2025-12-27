package com.muromtsev.employee.service;

import com.muromtsev.employee.config.ServiceConfig;
import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ServiceConfig serviceConfig;

    public EmployeeService(EmployeeRepository employeeRepository, ServiceConfig serviceConfig) {
        this.employeeRepository = employeeRepository;
        this.serviceConfig = serviceConfig;
    }

    public Employee getEmployee(String employeeId, String organizationId) {
        Employee employee = employeeRepository.findByOrganizationIdAndEmployeeId(organizationId, employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
        }
        return employee.withCommit(serviceConfig.getProperty());
    }

    public Employee createEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.save(employee);

        return employee.withCommit(serviceConfig.getProperty());
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepository.save(employee);

        return employee.withCommit(serviceConfig.getProperty());
    }

    public String deleteEmployee(String employeeId) {
        String responseMessage = null;
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employeeRepository.delete(employee);
        responseMessage = String.format("Employee deleted successfully: %s", employeeId);
        return responseMessage;
    }

}
