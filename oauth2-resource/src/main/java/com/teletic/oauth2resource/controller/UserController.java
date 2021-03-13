package com.teletic.oauth2resource.controller;

import com.teletic.oauth2resource.entity.User;
import com.teletic.oauth2resource.model.CustomPrincipal;
import com.teletic.oauth2resource.model.Employee;
import com.teletic.oauth2resource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PreAuthorize("hasAuthority('role_admin')")
    @GetMapping("/admins")
    public String context() {
        CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return principal.getUsername() + " " + principal.getEmail();
    }

    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @GetMapping("/users")
    public List<Employee> getUsers() {
        return Arrays.asList(
                new Employee(1, "Mohamed", "mog@teletic.dz"),
                new Employee(2, "Houari ZEGAI", "h.zegai@teletic.dz"),
                new Employee(2, "Teletic user", "user@teletic.dz")
        );
    }

    @GetMapping("/common")
    public String general() {
        return "common api success";
    }

    @PreAuthorize("hasAuthority('role_admin')")
    @GetMapping("/user")
    public User findByUsername(@RequestParam String username) {
        return userService.findByUsername(username).get();
    }

    @PreAuthorize("hasAuthority('can_update_user')")
    @GetMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        userOptional.ifPresent(user -> {
            user.setEmail("houari@gmail.com");
            userService.save(user);
        });
        return ResponseEntity.ok("User updated!");
    }
}
