/**
 * 
 */
package com.acoer.test.contact.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.acoer.test.contact.domain.Contact;
import com.acoer.test.contact.repository.ContactRepository;

@Service
public class ContactService implements ICrudOperations<Contact> {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getAll() {		
		return contactRepository.findAll();		 
	}

	@Override
	public Contact search(String searchTerm) {		 
		return  this.contactRepository.findById(searchTerm).orElseThrow(() -> new IllegalArgumentException("No contact found with id: " + searchTerm));
	}

	@Override
	public List<Contact> findByPhoneNumber(String phoneNumber) {
		 return contactRepository.findByPhoneNumber(phoneNumber);
		 
	}
	@Override
	public List<Contact> find(String search){
		return this.contactRepository.multiFieldSearch(search);

	}

	@Override
	public Contact add(Contact item) {		  
		return this.contactRepository.save(item);
	}

	@Override
	public Contact update(Contact item) {		
		return this.contactRepository.save(item);
	}

	@Override
	public void delete(Contact item) {		 
		this.contactRepository.delete(item);

	}

}
