package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRespository;

//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> findAll() {
        return userRespository.findAll();
    }

    public User saveEntry(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Save entry for user: {}", user.getUserName());
        user.setRoles(List.of("ADMIN"));
        return userRespository.save(user);
    }

    public void deleteEntryById(ObjectId id) {
        userRespository.deleteById(id);
//        return journalEntry;
    }

    public Optional<User> findEntryById(ObjectId id) {
        return userRespository.findById(id);
    }

    public User findByUserName(String userName) {
        return userRespository.findByUserName(userName);
    }
}
