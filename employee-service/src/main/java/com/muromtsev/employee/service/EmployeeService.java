package com.muromtsev.employee.service;

import com.muromtsev.employee.exception.EmployeeNotFoundException;
import com.muromtsev.employee.exception.OrganizationNotFoundException;
import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.model.OrganizationInfo;
import com.muromtsev.employee.model.dto.EmployeeRequest;
import com.muromtsev.employee.model.dto.EmployeeResponse;
import com.muromtsev.employee.model.dto.EmployeeWithOrganizationResponse;
import com.muromtsev.employee.model.dto.ResponseOrganization;
import com.muromtsev.employee.model.mapper.EmployeeMapper;
import com.muromtsev.employee.repository.EmployeeRepository;
import com.muromtsev.employee.service.client.OrganizationFeignClient;
import com.muromtsev.employee.service.client.OrganizationRestTemplateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final OrganizationRestTemplateClient organizationRestTemplateClient;
    private final OrganizationFeignClient organizationFeignClient;

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        Employee employee = new Employee();
        employee.setCreateAt(LocalDateTime.now());
        employee.setUpdateAt(LocalDateTime.now());

        employeeMapper.toEmployee(employee, employeeRequest);

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee created successfully with id {}", savedEmployee.getId());

        return employeeMapper.toEmployeeResponse(savedEmployee);
    }

    public EmployeeResponse getEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        log.info("Employee retrieved successfully with id {}", employeeId);

        return employeeMapper.toEmployeeResponse(employee);
    }

    public EmployeeResponse updateEmployee(int employeeId, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        employee.setFirstName(employeeRequest.firstName());
        employee.setLastName(employeeRequest.lastName());
        employee.setEmail(employeeRequest.email());
        employee.setPosition(employeeRequest.position());
        employee.setOrganizationUuid(employeeRequest.organizationUuid());

        log.info("Employee updated successfully with id {}", employeeId);

        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        employeeRepository.delete(employee);

        log.info("Employee deleted successfully with id {}", employeeId);
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<EmployeeResponse> employees = employeeRepository.findAll()
                        .stream()
                        .map(employeeMapper::toEmployeeResponse)
                        .toList();

        log.info("All employees retrieved successfully");

        return employees;
    }

    public EmployeeWithOrganizationResponse getEmployeeWithOrganization(int employeeId, String clientType) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);

        String organizationUuid = employee.getOrganizationUuid();
        OrganizationInfo organizationInfo = null;
        if (organizationUuid != null) {
            try {
                Optional<OrganizationInfo> orgOptional = retrieveOrganizationInfo(organizationUuid,  clientType);

                if (orgOptional.isPresent()) {
                    organizationInfo = orgOptional.get();
                } else {
                    log.warn("Organization uuid {} not found", organizationUuid);
                }

            } catch (Exception e) {
                log.error("Error occurred while trying to retrieve organization uuid {}", organizationUuid, e);
            }
        } else {
            log.info("Employee {} has no organization assigned", employeeId);
        }
        return employeeMapper.toEmployeeWithOrganizationResponse(employeeResponse, organizationInfo);
    }

    private Optional<OrganizationInfo> retrieveOrganizationInfo(String organizationUuid, String clientType) {
        try {
            if ("feign".equals(clientType)) {
                ResponseOrganization responseOrganization = organizationFeignClient.getOrganization(organizationUuid);
                log.info("[Organization Service Feign Client] Organization retrieved successfully with id {}", organizationUuid);
                return Optional.ofNullable(employeeMapper.toOrganizationInfo(responseOrganization));
            } else {
                return organizationRestTemplateClient.getOrganization(organizationUuid);
            }
        } catch (Exception e) {
            log.error("Failed to retrieve organization info for {}", organizationUuid, e);
            return Optional.empty();
        }
    }
}
