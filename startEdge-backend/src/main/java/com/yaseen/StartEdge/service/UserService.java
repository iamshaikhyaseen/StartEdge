package com.yaseen.StartEdge.service;

import com.yaseen.StartEdge.model.User;
import com.yaseen.StartEdge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserFromEmail(String email){
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
