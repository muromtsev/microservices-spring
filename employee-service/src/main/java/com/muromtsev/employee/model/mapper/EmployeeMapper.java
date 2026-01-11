package com.muromtsev.employee.model.mapper;

import com.muromtsev.employee.model.Employee;
import com.muromtsev.employee.model.OrganizationInfo;
import com.muromtsev.employee.model.dto.EmployeeRequest;
import com.muromtsev.employee.model.dto.EmployeeResponse;
import com.muromtsev.employee.model.dto.EmployeeWithOrganizationResponse;
import com.muromtsev.employee.model.dto.ResponseOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    void toEmployee(@MappingTarget Employee employee, EmployeeRequest employeeRequest);
    EmployeeRequest toEmployeeRequest(Employee employee);
    EmployeeResponse toEmployeeResponse(Employee employee);
    OrganizationInfo toOrganizationInfo(ResponseOrganization responseOrganization);

    EmployeeWithOrganizationResponse toEmployeeWithOrganizationResponse(EmployeeResponse employeeResponse,
                                                                        OrganizationInfo organizationInfo);


}
