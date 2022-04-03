package com.acoer.test.contact.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

import com.acoer.test.contact.domain.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

    
    @Query("{'phoneNumber': ?0}")
    public List<Contact> findByPhoneNumber(String phoneNumber);
    
     
    @Query("{'$or': [{'firstName': {'$regex': ?0, '$options': 'i'}}, {'lastName': {'$regex': ?0, '$options': 'i'}}, {'id': ?0}, {'phoneNumber': ?0}]}")
    public List<Contact> multiFieldSearch(String searchTerm);
}
