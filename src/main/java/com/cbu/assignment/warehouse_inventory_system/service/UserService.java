package com.cbu.assignment.warehouse_inventory_system.service;

import java.util.List;
import java.util.Optional;

import com.cbu.assignment.warehouse_inventory_system.Model.User;
import com.cbu.assignment.warehouse_inventory_system.web.dto.UserRegistrationDto;

public interface UserService {
    // save user details
    User save(UserRegistrationDto userRegistrationDto); 

    // return all users
    List<User> getAllUsers();

    // find user by email
     Boolean findUserByEmail(String email);

     User getUserByEmail(String email);
}
