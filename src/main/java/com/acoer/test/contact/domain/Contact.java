package com.acoer.test.contact.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NonNull;

@Data
@Document(collection = "contacts") 
public class Contact implements Comparable<Contact> {
	
	@Id
    public	String id;
	
	public String firstName;
	
	public String lastName;
	
	public String phoneNumber;
	  
	public String code;

	@Override
	public int compareTo(Contact o) {
		return this.firstName.compareTo(o.firstName);

	}
 
}
