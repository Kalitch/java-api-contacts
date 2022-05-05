package com.acoer.test.contact.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acoer.test.contact.domain.Contact;
import com.acoer.test.contact.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/contacts")
@Api(tags = { "Contacts API" })
public class ContactController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all contacts", notes = "Get all contacts")
	@ResponseBody
	public ResponseEntity<List<Contact>> getContacts() {
		logger.info("Retrieving list of contacts");
		List<Contact> result = new ArrayList<Contact>();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/***
	 * TODO: Implement the endpoints to create/update/delete/search
	 */
}
