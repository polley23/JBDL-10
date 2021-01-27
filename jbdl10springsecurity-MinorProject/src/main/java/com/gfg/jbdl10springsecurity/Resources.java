package com.gfg.jbdl10springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class Resources {
    @Autowired
    UserManager userManager;

    @Autowired
    EmployeeManager employeeManager;

    @GetMapping("/secured/home")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String home(){
        return "Hello you are in home";
    }

    @GetMapping("/secured/secret")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String secret(){
        return "Hello you are in the secret page";
    }


    @PostMapping("/signUp")
    public void signUp(@RequestBody SignUpRequest signupRequest){
       userManager.create(signupRequest);
    }

    @PostMapping("/employee")
    @PreAuthorize("hasAnyAuthority('HR','ADMIN')")
    public void createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest){
        employeeManager.create(employeeCreationRequest);
    }

    @PostMapping("/employee/rating/{userid}/{rating}")
    @PreAuthorize("hasAnyAuthority('HR','ADMIN','MANAGER')")
    public void giveRating(@PathVariable("userid") String userId, @PathVariable("rating") Integer rating){
        try {
            employeeManager.giveRating(userId,rating);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @PutMapping("/employee/{employee_id}/manager/{manager_id}")
    @PreAuthorize("hasAnyAuthority('HR','ADMIN')")
    public void addManager(@PathVariable("employee_id") String employeeId,
                           @PathVariable("manager_id") String managerId) throws Exception {
        employeeManager.assignSubordinate(managerId,employeeId);
    }
}
