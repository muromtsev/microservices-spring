package com.muromtsev.employee.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String organizationId;
    private String employeeType;
    private String commit;

    @Transient
    private String organizationName;
    @Transient
    private String contactName;
    @Transient
    private String contactEmail;
    @Transient
    private String contactPhone;

    public Employee withCommit(String commit) {
        this.setCommit(commit);
        return this;
    }

}
