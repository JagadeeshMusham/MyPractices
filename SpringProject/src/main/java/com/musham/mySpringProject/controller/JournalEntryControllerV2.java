package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> findAll() {
        return journalEntryService.findAll();
    }

    @GetMapping("/id/{myId}")
    public JournalEntry findEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findEntryById(myId).orElse(null);
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        return journalEntryService.saveEntry(myEntry);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {

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
        }

        return journalEntryService.saveEntry(journalEntryInDB);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteEntryById(myId);
        return true;
    }

}
