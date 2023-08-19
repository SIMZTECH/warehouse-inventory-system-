package com.cbu.assignment.warehouse_inventory_system.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbu.assignment.warehouse_inventory_system.repository.UserRepository;

@Service
public class EndToEndUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;


    public EndToEndUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email)
        
        .map(EndToEndUserDetails::new)
        .orElseThrow(()->new UsernameNotFoundException("user not found"));
    }

};
