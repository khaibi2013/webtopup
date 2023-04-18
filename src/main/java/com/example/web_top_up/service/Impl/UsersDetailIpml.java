package com.example.web_top_up.service.Impl;


import com.example.web_top_up.model.entities.UserEntity;
import com.example.web_top_up.repositories.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailIpml implements UserDetailsService {


    @Autowired
    private UserReponsitory userReponsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userReponsitory.findByEmail(username);

        if (user == null){
            throw new UsernameNotFoundException("Not found");
        }

        return new CustomUserDetails(user);
    }
}
