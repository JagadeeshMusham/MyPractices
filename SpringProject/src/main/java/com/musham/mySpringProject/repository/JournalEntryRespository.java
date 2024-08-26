package com.musham.mySpringProject.repository;

import com.musham.mySpringProject.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRespository extends MongoRepository<JournalEntry, ObjectId> {
}
