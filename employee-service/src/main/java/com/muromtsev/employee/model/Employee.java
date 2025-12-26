package com.muromtsev.employee.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {

    private int id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String organizationId;
    private String employeeType;

}
