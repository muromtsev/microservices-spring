package com.muromtsev.employee.service;

import com.muromtsev.employee.config.ServiceConfig;
import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.model.dto.ResponseOrganization;
import com.muromtsev.employee.repository.EmployeeRepository;
import com.muromtsev.employee.service.client.OrganizationRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ServiceConfig serviceConfig;
    private final OrganizationRestTemplateClient organizationRestTemplateClient;

    public EmployeeService(EmployeeRepository employeeRepository, ServiceConfig serviceConfig, OrganizationRestTemplateClient organizationRestTemplateClient) {
        this.employeeRepository = employeeRepository;
        this.serviceConfig = serviceConfig;
        this.organizationRestTemplateClient = organizationRestTemplateClient;
    }

    public Employee getEmployee(int employeeId, int organizationId, String clientType) {
        Employee employee = employeeRepository.findByOrganizationIdAndEmployeeId(organizationId, employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
        }

        ResponseOrganization org = retrieveOrganizationInfo(organizationId, clientType);

        if (org != null) {
            employee.setOrganizationName(org.name());
            employee.setContactName(org.contactName());
            employee.setContactEmail(org.contactEmail());
            employee.setContactPhone(org.contactPhone());
        }

        return employee.withCommit(serviceConfig.getProperty());
    }

    private ResponseOrganization retrieveOrganizationInfo(int organizationId, String clientType) {
        ResponseOrganization org = null;
        switch(clientType) {
            case "client":
                org = organizationRestTemplateClient.getOrganization(organizationId);
                break;
            default:
                org = organizationRestTemplateClient.getOrganization(organizationId);
        }
        return org;

    }

    public Employee createEmployee(Employee employee) {
        employeeRepository.save(employee);

        return employee.withCommit(serviceConfig.getProperty());
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepository.save(employee);

        return employee.withCommit(serviceConfig.getProperty());
    }

    public String deleteEmployee(int employeeId) {
        String responseMessage = null;

        Employee employee = employeeRepository.getEmployeeById(employeeId);

        employeeRepository.delete(employee);
        responseMessage = String.format("Employee deleted successfully: %s", employeeId);
        return responseMessage;
    }

}
