package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal/v3")
public class JournalEntryControllerV3 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // it will allow only ADMIN privileged user to access this
    public ResponseEntity<?> findAll() {
        List<JournalEntry> journalEntries = journalEntryService.findAll();

        if (journalEntries == null || journalEntries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('ROLE_USER')") // it will allow only ADMIN privileged user to access this
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> findEntryById(@PathVariable ObjectId myId) {
        JournalEntry journalEntry = journalEntryService.findEntryById(myId).orElse(null);

        if (journalEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            return new ResponseEntity<>(journalEntryService.saveEntry(myEntry), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id,
                                             @RequestBody JournalEntry newEntry) {

        JournalEntry journalEntryInDB = journalEntryService.findEntryById(id).orElse(null);

        if (journalEntryInDB != null) {
            journalEntryInDB.setTitle(
                    (newEntry.getTitle() == null || newEntry.getTitle().isEmpty()) ?
                            journalEntryInDB.getTitle() : newEntry.getTitle()
            );

            journalEntryInDB.setContent(
                    (newEntry.getContent() == null || newEntry.getContent().isEmpty()) ?
                            journalEntryInDB.getContent() : newEntry.getContent()
            );

            journalEntryService.saveEntry(journalEntryInDB);
            return new ResponseEntity<>(journalEntryInDB, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
        if (!journalEntryService.findEntryById(myId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        journalEntryService.deleteEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
