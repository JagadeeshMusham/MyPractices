package com.musham.mySpringProject.controller;

import com.musham.mySpringProject.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal/v1")
public class JournalEntryController {

    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getEntryById(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId) {
        return journalEntries.remove(myId);
    }

}
