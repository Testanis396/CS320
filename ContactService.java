package main;

import java.util.*;


/**
 * A singleton Contact Service.
 * 
 * CS320 3-2 Milestone
 * 
 * Used my previous CS 230 project work for template. 
 * 
 * 3 main functions:
 * addContact(all params), 
 * updateContact(id, field, newValue), 
 * deleteContact(id).
 * 
 * Helper functions for:
 * deleteAllContacts(), 
 * getContact(id), 
 * getAllContacts().
 *
 * ContactServiceTestSuite runs both ContactTest and ContactServiceTest.
 * 100% code coverage for both Contact.java and ContactService.java.
 * 
 * @author Tomas Estanislao
 * 
 */

public class ContactService {
	
	/**
	 * A map of unique ID's and their associated contact objects.
	 */
	private Map<String, Contact> contacts = new HashMap<>();
	
	/*
	 * Static instance of the ContactService class.
	 * Only one copy of ContactService can exist at any time.
	 * Can be accessed globally, covering the entire application.
	 * Handles all Contacts.
	 */
	private static ContactService instance = null;
	
	/**
	 * Private constructor for ContactService.
	 * Prevents instantiation from outside the class itself.
	 * 
	 */
	private ContactService() {
		
	}
	
	/**
	 * Creates and returns a new ContactService instance, or the current existing instance.
	 * 
	 * @return the ContactService instance (new or existing)
	 */
	public static ContactService getInstance() {
		if (instance == null) {
			instance = new ContactService();
			System.out.println("New Service created.");
		} else {
			System.out.println("Service already opened.");
		}
		return instance;
	}
	
	/**
	 * Construct a new contact.
	 * 
	 * @param id, firstName, lastName, phone, address
	 * @return the contact instance (new or existing)
	 */
	public Contact addContact(String id, String firstName, String lastName, String phone, String address) {

		// a local contact instance, either existing or null
		Contact contact = this.getContact(id);

		// if not found, make a new contact instance and add to map of contacts
		if (contact == null) {
			// validity checker in Contact class
			contact = new Contact(id, firstName, lastName, phone, address);
			contacts.put(id, contact);
		}

		// return the new/existing contact instance to the caller
		return contact;
	}
	
	/**
	 * Update a contact.
	 * 
	 * @param id, field{"firstName","lastName","phone","address"}, new value
	 * @return the new updated contact instance or null if it doesn't exist
	 */
	public Contact updateContact(String id, String field, String newVal) {

		// a local contact instance, either existing or null
		Contact contact = this.getContact(id);
		
		if (contact != null) {
			if (field == "firstName") {
				contact.setFirstName(newVal);
			}
			else if (field == "lastName") {
				contact.setLastName(newVal);
			}	
			else if (field == "phone") {
				contact.setPhone(newVal);
			}
			else if (field == "address") {
				contact.setAddress(newVal);
			}
		}
		
		// return the updated contact or null if id doesn't exist
		return contact;
	}
	
	/**
	 * Delete an existing contact.
	 * 
	 * @param id
	 * @return the updated contacts map
	 */
	public String deleteContact(String id) {

		// removes contact if id exists
		contacts.remove(id);
		
		return this.getAllContacts();
	}
	
	/**
	 * Delete all contacts.
	 * 
	 */
	public void deleteAllContacts() {

		contacts.clear();
	}
	
	/**
	 * Get string representation of all contacts.
	 * 
	 * @return String contacts 
	 */	
	public String getAllContacts(){
		
		return (contacts.toString());
	}
	
	/**
	 * Get contact with key = id.
	 * 
	 * @param id
	 * @return contact if exists, else null
	 */	
	public Contact getContact(String id){
		
		return (contacts.get(id));
	}
}
