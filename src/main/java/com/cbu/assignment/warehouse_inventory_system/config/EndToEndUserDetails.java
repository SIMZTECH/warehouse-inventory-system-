package com.cbu.assignment.warehouse_inventory_system.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cbu.assignment.warehouse_inventory_system.Model.Role;
import com.cbu.assignment.warehouse_inventory_system.Model.User;

public class EndToEndUserDetails implements UserDetails {

    private String password;
    private String userName;
    private List<GrantedAuthority>authorities;

     protected Log logger = LogFactory.getLog(this.getClass());


    public EndToEndUserDetails(User user) {
        this.password = user.getPassword();
        this.userName = user.getEmail();

        List<GrantedAuthority> authorities = new ArrayList<>();
      
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        
        this.authorities = authorities;
        
        // Arrays.stream(authorities.toString().split(","))
        // .map(SimpleGrantedAuthority::new).
        // collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
       return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
