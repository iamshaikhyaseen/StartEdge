package com.yaseen.StartEdge.service;

import com.yaseen.StartEdge.model.User;
import com.yaseen.StartEdge.model.UserPrinciple;
import com.yaseen.StartEdge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(username);
        if (user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found with username: ");
        }
        return new UserPrinciple(user);
    }
}
