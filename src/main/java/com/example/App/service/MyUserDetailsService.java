package com.example.App.service;


import com.example.App.model.User;
import com.example.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component 
public class MyUserDetailsService implements UserDetailsService {

    //Injecterar Dependencies
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        //Fetch User från DB
        Optional<User> userRespository = userRepository.findByEmail(email);
        
        //Om User ej hittas
        if(userRespository.isEmpty())
            throw new UsernameNotFoundException("Hittade ingen användare med email = " + email);
        
            //Returnera en User Details object genom den fetchade User informationen
        User user = userRespository.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))); //Sätter specifik role till User
    }
}