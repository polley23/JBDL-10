package com.gfg.jbdl10springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
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

    @PatchMapping("/employee/rating/{userid}/{rating}")
    @PreAuthorize("hasAnyAuthority('HR','ADMIN','MANAGER')")
    public void giveRating(@PathVariable("userid") String userId, @PathVariable("rating") Integer rating){
        try {
            employeeManager.giveRating(userId,rating);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @GetMapping("/employee/rating/{userid}")
    @PreAuthorize("hasAnyAuthority('HR','ADMIN','MANAGER','EMPLOYEE')")
    public ResponseEntity getRating(@PathVariable("userid") String userId, Authentication authentication){
        List<Authority> grantedAuthority = (List<Authority>) authentication.getAuthorities();
        if(grantedAuthority.get(0).getAuthority().toString().equals("EMPLOYEE")){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),null);
            log.info(usernamePasswordAuthenticationToken.getName());
            if(!usernamePasswordAuthenticationToken.getName().equals(userId)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        try {
            return ResponseEntity.ok(employeeManager.getRating(userId));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @PutMapping("/employee/{employee_id}/manager/{manager_id}")
    @PreAuthorize("hasAnyAuthority('HR','ADMIN')")
    public void addManager(@PathVariable("employee_id") String employeeId,
                           @PathVariable("manager_id") String managerId) throws Exception {
        employeeManager.assignSubordinate(managerId,employeeId);
    }
}
