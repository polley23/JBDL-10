package com.gfg.jbdl10springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class EmployeeManager implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ManagerRepository managerRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return employeeRepository.findByEmployeeId(s).orElseThrow(() -> new UsernameNotFoundException("employeeid not found"));
    }

    public void create(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getByEmployeeId(String s) {
        return employeeRepository.findByEmployeeId(s).orElseThrow(() -> new UsernameNotFoundException("employeeid not found"));
    }


    public void create(EmployeeCreationRequest employeeCreationRequest) {
        if (employeeCreationRequest.getEmployeeType().equals(EmployeeType.MANAGER)) {
            createManager(employeeCreationRequest);
        }
        else if (employeeCreationRequest.getEmployeeType().equals(EmployeeType.EMPLOYEE)){
            createEmployee(employeeCreationRequest);
        }
    }


    public void giveRating(String userId, Integer rating) throws Exception {
        Employee employee = employeeRepository.findByEmployeeId(userId).orElseThrow(() -> new Exception());
        employee.setRating(rating);
        employeeRepository.save(employee);
    }

    private void createManager(EmployeeCreationRequest employeeCreationRequest) {
        Manager manager = Manager.builder()
                .firstName(employeeCreationRequest.getFirstName())
                .lastName(employeeCreationRequest.getLastName())
                .employeeId(employeeCreationRequest.getFirstName() + employeeCreationRequest.getLastName())
                .username(employeeCreationRequest.getFirstName() + employeeCreationRequest.getLastName() + UUID.randomUUID().getLeastSignificantBits())
                .password(bCryptPasswordEncoder.encode(employeeCreationRequest.getLastName()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        Authority authority;
        try {
            authority = authorityRepository.findByAuthority(employeeCreationRequest.getEmployeeType().name()).orElseThrow(() -> new Exception());
            if (authority.getUsers() == null) {
                authority.setUsers(Arrays.asList());
            }else{
                authority.getUsers().add(manager);
            }
        } catch (Exception e) {
            authority = Authority.builder()
                    .authority(employeeCreationRequest.getEmployeeType().name())
                    .users(Arrays.asList(manager))
                    .build();
        }
        manager.setAuthorityList(Arrays.asList(authority));
        managerRepository.save(manager);

    }

    private void createEmployee(EmployeeCreationRequest employeeCreationRequest) {
        Employee employee = Employee.builder()
                .firstName(employeeCreationRequest.getFirstName())
                .lastName(employeeCreationRequest.getLastName())
                .employeeId(employeeCreationRequest.getFirstName() + employeeCreationRequest.getLastName())
                .username(employeeCreationRequest.getFirstName() + employeeCreationRequest.getLastName()+  UUID.randomUUID().getLeastSignificantBits())
                .password(bCryptPasswordEncoder.encode(employeeCreationRequest.getLastName()))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        Authority authority;
        try {
            authority = authorityRepository.findByAuthority(employeeCreationRequest.getEmployeeType().name()).orElseThrow(() -> new Exception());
            if (authority.getUsers() == null) {
                authority.setUsers(Arrays.asList());
            }else{
               authority.getUsers().add(employee);
            }
        } catch (Exception e) {
            authority = Authority.builder()
                    .authority(employeeCreationRequest.getEmployeeType().name())
                    .users(Arrays.asList(employee))
                    .build();
        }

        employee.setAuthorityList(Arrays.asList(authority));
        employeeRepository.save(employee);
    }
    public void assignSubordinate(String ManagerId, String employeeId) throws Exception {
        Manager manager = managerRepository.findByEmployeeId(ManagerId).orElseThrow(() -> new Exception());
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new Exception());
        if (manager.getSubordinates() == null) {
            manager.setSubordinates(new ArrayList<>());
        }
        manager.getSubordinates().add(employee);
        employee.setManager(manager);
        employeeRepository.save(employee);
        managerRepository.save(manager);
    }

    public Integer getRating(String userId) throws Exception {
        Employee e = employeeRepository.findByEmployeeId(userId).orElseThrow(() -> new Exception());
        return e.getRating();
    }

}
