package com.gfg.jbdl10springsecurity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Employee{
    @OneToMany(mappedBy ="manager" ,cascade = CascadeType.ALL)
    private List<Employee> subordinates;
}
