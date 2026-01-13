package com.muromtsev.employee.service;

import com.muromtsev.employee.exception.EmployeeNotFoundException;
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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeoutException;


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

    /**
     * Метод возвращает список сотрудников по UUID организации
     * Использует Circuit Breaker для устойчивости к ошибкам.
     * @param organizationUuid уникальный идентификатор организации в формате UUID
     * @return список объектов EmployeeResponse, принадлежащих указанной организации
     * @throws TimeoutException если превышено время ожидания
     */

    @CircuitBreaker(
            name = "employeeService",
            fallbackMethod = "buildFallbackEmployeeList"
    )
    public List<EmployeeResponse> getEmployeesByOrganizationUuid(String organizationUuid) throws TimeoutException {
        randomlyRunLong();
        List<EmployeeResponse> responses = new ArrayList<>();
        List<Employee> employees = employeeRepository.getEmployeeByOrganizationUuid(organizationUuid);
        for (Employee employee : employees) {
            responses.add(employeeMapper.toEmployeeResponse(employee));
        }
        return responses;
    }

    /**
     * Fallback метод для Circuit Breaker
     * @param organizationUuid уникальный идентификатор организации в формате UUID
     * @param throwable содержит информацию о том, почему сработал fallback
     * @return список объектов EmployeeResponse, принадлежащих указанной организации
     */

    private List<EmployeeResponse> buildFallbackEmployeeList(String organizationUuid, Throwable throwable) {
        List<EmployeeResponse> fallbackList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("Извините, информации о сотруднике сейчас нет.");
        employee.setOrganizationUuid(organizationUuid);
        fallbackList.add(employeeMapper.toEmployeeResponse(employee));
        return fallbackList;
    }

    /**
     * Метод с 70% шансом генерирует задержку в 5000 мс
     * @throws TimeoutException если превышено время ожидания
     */

    private void randomlyRunLong() throws TimeoutException {
        Random random = new Random();
        int randomNum =  random.nextInt(10) + 1;
        if (randomNum <= 7) sleep();
    }

    /**
     * Метод задержки на 5000 мс
     * @throws TimeoutException если превышено время ожидания
     */

    private void sleep() throws TimeoutException{
        try {
            System.out.println("SLEEPING");
            Thread.sleep(5000);
            throw new TimeoutException();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
