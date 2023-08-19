package com.cbu.assignment.warehouse_inventory_system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbu.assignment.warehouse_inventory_system.service.UserService;
import com.cbu.assignment.warehouse_inventory_system.web.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class AuthenicationController {
     private UserService userService;

    public AuthenicationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String GetEmployeeFormPage(){
        return "register";
    }

    @PostMapping("register")
    public String AddEmployee(@ModelAttribute("user") UserRegistrationDto userRegistrationDto){
        if(userService.findUserByEmail(userRegistrationDto.getEmail())){
             return "redirect:/register?errorEmail";
    }

        userRegistrationDto.setRole("ROLE_CUSTOMER");
         this.userService.save(userRegistrationDto);
         return "redirect:/register?Success";
    }

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

       
    
}
