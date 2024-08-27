package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.JournalEntryRespository;
import com.musham.mySpringProject.repository.UserRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRespository userRespository;

    public List<User> findAll() {
        return userRespository.findAll();
    }

    public User saveEntry(User user) {
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
