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

@Service
public class ContactService implements ICrudOperations<Contact> {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private MongoOperations mongoOps;

	@Override
	public List<Contact> getAll() {
		// TODO Add the implementation code
		return null;
	}

	@Override
	public List<Contact> search(String searchTerm) {
		// TODO Add the implementation code
		return null;
	}

	@Override
	public Contact add(Contact item) {
		// TODO Add the implementation code
		return null;
	}

	@Override
	public Contact update(Contact item) {
		// TODO Add the implementation code
		return null;
	}

	@Override
	public void delete(Contact item) {
		// TODO Add the implementation code

	}

}
