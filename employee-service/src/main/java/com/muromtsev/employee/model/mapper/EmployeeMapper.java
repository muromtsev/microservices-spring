package com.muromtsev.employee.model.mapper;

import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.model.dto.EmployeeRequest;
import com.muromtsev.employee.model.dto.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    void toEmployee(@MappingTarget Employee employee, EmployeeRequest employeeRequest);
    EmployeeRequest toEmployeeRequest(Employee employee);
    EmployeeResponse toEmployeeResponse(Employee employee);

}
