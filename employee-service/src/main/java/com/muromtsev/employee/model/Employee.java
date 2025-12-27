package com.muromtsev.employee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String organizationId;
    private String employeeType;
    private String commit;

    public Employee withCommit(String commit) {
        this.setCommit(commit);
        return this;
    }

}
