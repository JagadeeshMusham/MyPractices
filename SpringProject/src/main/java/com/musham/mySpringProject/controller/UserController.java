package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.service.JournalEntryService;
import com.musham.mySpringProject.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<User> user = userService.findAll();

        if (user == null || user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<User> findEntryById(@PathVariable ObjectId myId) {
        User user = userService.findEntryById(myId).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createEntry(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.saveEntry(user), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{usreName}")
    public ResponseEntity<?> updateEntryById(@PathVariable String userName, @RequestBody User newUser) {

        User userInDB = userService.findByUserName(userName);

        if (userInDB != null) {
            userInDB.setPassword(newUser.getPassword().isEmpty() ?
                    userInDB.getPassword() : newUser.getPassword()
            );

            userService.saveEntry(userInDB);
            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
        if (!userService.findEntryById(myId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}