package com.acoer.test.contact.controller;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import com.acoer.test.contact.domain.Contact;
import com.acoer.test.contact.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/contacts")
@Api(tags = { "Contacts API" })
public class ContactController {

	@Autowired
	private ContactService contactService;

	private static final Logger logger = LogManager.getLogger();

	// getall v2
	@RequestMapping(method = RequestMethod.GET, value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all contacts", notes = "Get all contacts")
	public ResponseEntity<List<Contact>> getContacts() {
		logger.info("Retrieving list of contacts");
		List<Contact> result = contactService.getAll();
		Collections.sort(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// create
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a contact", notes = "Add a contact")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		logger.info("Adding contact");
		List<Contact> find = contactService.findByPhoneNumber(contact.phoneNumber);
		if (find.size() >= 1) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		Contact result = contactService.add(contact);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// search [ID ONLY]
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/search/{id}", produces
	 * = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ApiOperation(value = "Search a contact", notes = "Search a contact")
	 * public ResponseEntity<Contact> searchContact(@PathVariable("id") String id) {
	 * logger.info("Searching contact");
	 * Contact result = contactService.search(id);
	 * if(result == null) {
	 * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 * }
	 * return new ResponseEntity<>( result, HttpStatus.OK);
	 * }
	 */

	// search [ID & PHONE NUMBER & FIRST NAME & LAST NAME]
	@RequestMapping(method = RequestMethod.GET, value = "/search/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search a contact", notes = "Search a contact")
	public ResponseEntity<List<Contact>> findContact(@PathVariable("value") String value) {
		logger.info("Searching contact");
		List<Contact> result = contactService.find(value);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Collections.sort(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// delete
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a contact", notes = "Delete a contact")
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") String id) {
		logger.info("Deleting contact");
		Contact find = contactService.search(id);
		if (find.phoneNumber == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		contactService.delete(find);
		return new ResponseEntity("Contact deleted", HttpStatus.OK);
	}

	// update
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a contact", notes = "Update a contact")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") String id, @RequestBody Contact contact) {
		logger.info("Updating contact");
		if (contact.phoneNumber == null || contact.id == null) {
			return new ResponseEntity("To update a contact, you must provide a valid id and phone number",
					HttpStatus.NOT_FOUND);
		}
		List<Contact> find = contactService.findByPhoneNumber(contact.phoneNumber);
		if (find.size() == 1) {
			if (find.get(0).id.equals(id)) {
				Contact result = contactService.update(contact);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity("Phone number already exists or the given id is incorrect",
						HttpStatus.CONFLICT);

			}
		} else if (find.size() > 1) {
			return new ResponseEntity("Phone number already exists", HttpStatus.CONFLICT);

		}

		Contact result = contactService.update(contact);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
