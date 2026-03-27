package com.security.controller;

import com.security.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final CustomUserDetailsService userDetailsService;

    public TestController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/test/users")
    public String testUsers() {
        StringBuilder result = new StringBuilder();
        result.append("=== TEST DES UTILISATEURS ===\n\n");

        String[] users = {"super_admin", "team_lead", "employee"};

        for (String username : users) {
            try {
                UserDetails user = userDetailsService.loadUserByUsername(username);
                result.append("OK - ").append(username).append(" / ").append(user.getPassword()).append("\n");
                result.append("     Rôles: ").append(user.getAuthorities()).append("\n\n");
            } catch (Exception e) {
                result.append("ERREUR - ").append(username).append(": ").append(e.getMessage()).append("\n\n");
            }
        }

        return result.toString();
    }
}