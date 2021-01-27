package com.gfg.jbdl10springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

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
        return employeeRepository.findByEmployeeId(s).orElseThrow(()->new UsernameNotFoundException("employeeid not found"));
    }

    public void create(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getByEmployeeId(String s){
        return employeeRepository.findByEmployeeId(s).orElseThrow(()->new UsernameNotFoundException("employeeid not found"));
    }



    public void create(EmployeeCreationRequest employeeCreationRequest){

            Employee employee =Employee.builder()
                    .firstName(employeeCreationRequest.getFirstName())
                    .lastName(employeeCreationRequest.getLastName())
                    .employeeId(employeeCreationRequest.getFirstName()+employeeCreationRequest.getLastName())
                    .username(employeeCreationRequest.getFirstName()+employeeCreationRequest.getLastName())
                    .password(bCryptPasswordEncoder.encode(employeeCreationRequest.getLastName()))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            Authority authority;
            try {
                authority = authorityRepository.findByAuthority(employeeCreationRequest.getEmployeeType().name()).orElseThrow(() -> new Exception());
                if(authority.getUsers()==null){
                    authority.setUsers(Arrays.asList());
                }
            }catch (Exception e){
                authority = Authority.builder()
                        .authority(employeeCreationRequest.getEmployeeType().name())
                        .users(Arrays.asList(employee))
                        .build();
            }
        employee.setAuthorityList(Arrays.asList(authority));
            employeeRepository.save(employee);
        }

        public void giveRating(String userId, Integer rating) throws Exception {
            Employee employee = employeeRepository.findByEmployeeId(userId).orElseThrow(() -> new Exception());
            employee.setRating(rating);
            employeeRepository.save(employee);
    }

    public void assignSubordinate(String ManagerId, String employeeId) throws Exception {
        Manager manager = managerRepository.findByEmployeeId(ManagerId).orElseThrow(() -> new Exception());
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new Exception());
        if(manager.getSubordinates() == null){
            manager.setSubordinates(new ArrayList<>());
        }
        manager.getSubordinates().add(employee);
        employee.setManager(manager);
    }

}
