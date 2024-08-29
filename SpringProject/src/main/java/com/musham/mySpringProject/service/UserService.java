package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRespository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> findAll() {
        return userRespository.findAll();
    }

    public User saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
