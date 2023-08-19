package com.cbu.assignment.warehouse_inventory_system.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cbu.assignment.warehouse_inventory_system.Model.Role;
import com.cbu.assignment.warehouse_inventory_system.Model.User;
import com.cbu.assignment.warehouse_inventory_system.repository.UserRepository;
import com.cbu.assignment.warehouse_inventory_system.web.dto.UserRegistrationDto;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    protected Log logger = LogFactory.getLog(this.getClass());

    // contsructor
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        logger.info(userRegistrationDto.getRole());
        // create user
        User user = new User(
            userRegistrationDto.getFirstName(), 
            userRegistrationDto.getLastName(),
             userRegistrationDto.getEmail(),
            this.passwordEncoder.encode(userRegistrationDto.getPassword()),
            new Role(userRegistrationDto.getRole())
        );
        
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public Boolean findUserByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email).isPresent() ? true : false ;
    }

    @Override
    public User getUserByEmail(String email) {
         return userRepository.findByEmail(email).get();
    }
    
}
