package com.muromtsev.employee.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends BaseException {

    public EmployeeNotFoundException(int id) {
        super(
                HttpStatus.NOT_FOUND,
                "EMPLOYEE_NOT_FOUND",
                String.format("Employee with id %d not found", id)
        );
    }
}
