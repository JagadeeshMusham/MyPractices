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

import java.io.PushbackReader;
import java.util.List;

/**
 * In this version of Controller mapping the JournalEntry with Users
 */
@RestController
@RequestMapping("/journal/v4")
public class JournalEntryControllerV4 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> findAllJournalEntriesOfUser(@PathVariable String userName) {

        User user = userService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

        List<JournalEntry> journalEntries = user.getJournalEntries();

        if (journalEntries == null || journalEntries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userName}/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable String userName,
                                             @PathVariable ObjectId id,
                                             @RequestBody JournalEntry newEntry) {

        JournalEntry journalEntryInDB = journalEntryService.findEntryById(id).orElse(null);

        if (journalEntryInDB != null) {
            journalEntryInDB.setTitle( newEntry.getTitle().isEmpty() ?
                            journalEntryInDB.getTitle() : newEntry.getTitle()
            );

            journalEntryInDB.setContent(
                    (newEntry.getContent() == null || newEntry.getContent().isEmpty()) ?
                            journalEntryInDB.getContent() : newEntry.getContent()
            );

            journalEntryService.saveEntry(journalEntryInDB, userName);
            return new ResponseEntity<>(journalEntryInDB, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String userName, @PathVariable ObjectId myId) {
        if (!journalEntryService.findEntryById(myId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        journalEntryService.deleteEntryById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
