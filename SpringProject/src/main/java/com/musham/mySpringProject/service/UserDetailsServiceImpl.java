package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsServiceImpl/* implements UserDetailsService */{
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username);
//        if (user != null) {
//            //String[0] means, we have given a String of Size 0, if requires it will resize to appropriate size
//
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getUserName())
//                    .password(user.getPassword())
//                    .roles(user.getRoles().toArray(new String[0])) //String[0] means, we have given a String of Size 0, if requires it will resize to appropriate size
//                    .build();
//        }
//
//        throw new UsernameNotFoundException("User not found with userName: " + username);
//    }
//
//    public List<User> findAll()
//    {
//        return userRepository.findAll();
//    }
}
