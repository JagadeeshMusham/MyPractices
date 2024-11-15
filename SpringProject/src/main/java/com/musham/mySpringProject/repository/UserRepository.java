package com.musham.mySpringProject.repository;

import com.musham.mySpringProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    List<User> findAll();
}
