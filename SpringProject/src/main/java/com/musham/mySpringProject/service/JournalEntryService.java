package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.JournalEntryRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRespository journalEntryRespository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> findAll() {
        return journalEntryRespository.findAll();
    }

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        return journalEntryRespository.save(journalEntry);
    }

    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedEntry = journalEntryRespository.save(journalEntry);

        user.getJournalEntries().add(savedEntry);
        userService.saveEntry(user);
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRespository.deleteById(id);
//        return journalEntry;
    }

    public void deleteEntryById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));

        userService.saveEntry(user);

        journalEntryRespository.deleteById(id);
    }

    public Optional<JournalEntry> findEntryById(ObjectId id) {
        return journalEntryRespository.findById(id);
    }
}
