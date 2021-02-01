package com.gfg.jbdl10springsecurity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreationRequest {
    private String firstName;
    private String lastName;
    private EmployeeType employeeType;
}
