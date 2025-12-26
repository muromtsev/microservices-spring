package com.muromtsev.employee.service;

import com.muromtsev.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployeeService {

    public Employee getEmployee(String employeeId, String organizationId) {
        Employee employee = new Employee();
        employee.setId(new Random().nextInt(1000));
        employee.setEmployeeId(employeeId);
        employee.setOrganizationId(organizationId);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmployeeType("Employee");
        return employee;
    }

    public String createEmployee(Employee employee, String organizationId) {
        String responseMessage = null;
        if (employee != null) {
            employee.setOrganizationId(organizationId);
            responseMessage = String.format("Employee created successfully: %s", employee.toString());
        }
        return responseMessage;
    }

    public String updateEmployee(Employee employee, String organizationId) {
        String responseMessage = null;
        if (employee != null) {
            employee.setOrganizationId(organizationId);
            responseMessage = String.format("Employee updated successfully: %s", employee.toString());
        }
        return responseMessage;
    }

    public String deleteEmployee(String employeeId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format("Employee deleted successfully: %s for organization %s", employeeId, organizationId);
        return responseMessage;
    }

}
