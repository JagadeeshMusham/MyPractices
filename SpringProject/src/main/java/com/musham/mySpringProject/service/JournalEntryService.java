package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.repository.JournalEntryRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRespository journalEntryRespository;

    public List<JournalEntry> findAll() {
        return journalEntryRespository.findAll();
    }

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRespository.save(journalEntry);
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRespository.deleteById(id);
//        return journalEntry;
    }

    public Optional<JournalEntry> findEntryById(ObjectId id) {
        return journalEntryRespository.findById(id);
    }
}
