package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal/v1")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

   @GetMapping
    public List<JournalEntry> findAll() {
       return journalEntryService.findAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
       myEntry.setDate(LocalDateTime.now());
        return journalEntryService.saveEntry(myEntry);
    }

    @GetMapping("/id/{myId}")
    public JournalEntry findEntryById(@PathVariable ObjectId myId) {
       return journalEntryService.findEntryById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
       journalEntryService.deleteEntryById(myId);
       return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {

       JournalEntry oldEntry = journalEntryService.findEntryById(id).orElse(null);

       if(oldEntry != null) {
           oldEntry.setTitle(
                   (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) ?
                           newEntry.getTitle() : oldEntry.getTitle()
           );

           oldEntry.setContent(
                   (newEntry.getContent() != null && !newEntry.getTitle().isEmpty()) ?
                           newEntry.getContent() : oldEntry.getContent()
           );
       }

       return journalEntryService.saveEntry(oldEntry);
    }

}
