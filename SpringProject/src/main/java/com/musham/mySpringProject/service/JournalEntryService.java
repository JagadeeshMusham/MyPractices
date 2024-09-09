package com.musham.mySpringProject.service;

import com.musham.mySpringProject.entity.JournalEntry;
import com.musham.mySpringProject.entity.User;
import com.musham.mySpringProject.repository.JournalEntryRespository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRespository journalEntryRespository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    public List<JournalEntry> findAll() {
        return journalEntryRespository.findAll();
    }

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        return journalEntryRespository.save(journalEntry);
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        logger.info("Started Save entry for journal entry: " + journalEntry.getTitle());
        User user = userService.findByUserName(userName);
        if (user == null) {
            logger.warn("User Name not found");
            return;
        }

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedEntry = journalEntryRespository.save(journalEntry);

        user.getJournalEntries().add(savedEntry);
        userService.saveEntry(user);
        logger.debug("Journal entry: {} saved for user: {}",
                journalEntry.getTitle(), userName);
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRespository.deleteById(id);
//        return journalEntry;
    }

    @Transactional
    public void deleteEntryById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return;
        }

        user.getJournalEntries().removeIf(x -> x.getId().equals(id));

        userService.saveEntry(user);

        journalEntryRespository.deleteById(id);
    }

    public Optional<JournalEntry> findEntryById(ObjectId id) {
        return journalEntryRespository.findById(id);
    }
}
