package com.gfg.jbdl10springsecurity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private Integer rating;
    private Long Salary;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
