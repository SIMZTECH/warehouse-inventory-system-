package com.cbu.assignment.warehouse_inventory_system.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedUser {
    private final String userEmail;


    public AuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.userEmail = authentication.getName();
    }


    public String getUserEmail() {
        return this.userEmail;
    }

}
