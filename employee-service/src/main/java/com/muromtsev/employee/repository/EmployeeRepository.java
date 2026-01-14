package com.muromtsev.employee.repository;

import com.muromtsev.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> getEmployeeByOrganizationUuid(String organizationUuid);

}
