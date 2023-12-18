package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.ContactService;

class ContactServiceTest {

	
	ContactService Service = ContactService.getInstance();
	
	@AfterEach
	void tearDown() throws Exception {
		Service.deleteAllContacts();
	}

	@Test
	void testAddContact() {
		// add contact to service
		Service.addContact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville");	    
	    assertEquals(Service.getAllContacts(),"{C123456789=Contact [id=C123456789, first name=John, last name=Doe, phone number=1234567890, address=123 Main St, Cityville]}");
	    
	    // add duplicate contact, expect no change
	    Service.addContact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville");  
	    assertEquals(Service.getAllContacts(),"{C123456789=Contact [id=C123456789, first name=John, last name=Doe, phone number=1234567890, address=123 Main St, Cityville]}");
	    
	    // add duplicate id contact, expect no change
	    Service.addContact("C123456789", "Ralph", "Lauren", "0987654321", "123 Main St, Cityville"); 
	    assertEquals(Service.getAllContacts(),"{C123456789=Contact [id=C123456789, first name=John, last name=Doe, phone number=1234567890, address=123 Main St, Cityville]}");
	    
	    // add new id contact, expect addition 
	    Service.addContact("D123456789", "Ralph", "Lauren", "0987654321", "123 Main St, Cityville");	    
	    assertEquals(Service.getAllContacts(),"{D123456789=Contact [id=D123456789, first name=Ralph, last name=Lauren, phone number=0987654321, address=123 Main St, Cityville], "
	    		+ "C123456789=Contact [id=C123456789, first name=John, last name=Doe, phone number=1234567890, address=123 Main St, Cityville]}");
	}
	
	@Test
	void testDeleteContact() {
		// add contact to service
		Service.addContact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville");
	    Service.addContact("D123456789", "Ralph", "Lauren", "0987654321", "123 Main St, Cityville");
	    
	    // delete wrong id, no change
	    assertEquals(Service.deleteContact("A123456789"),"{D123456789=Contact [id=D123456789, first name=Ralph, last name=Lauren, phone number=0987654321, address=123 Main St, Cityville], "
	    		+ "C123456789=Contact [id=C123456789, first name=John, last name=Doe, phone number=1234567890, address=123 Main St, Cityville]}");
	    
	    // delete Cid, returns new total contacts 
	    assertEquals(Service.deleteContact("C123456789"),"{D123456789=Contact [id=D123456789, first name=Ralph, last name=Lauren, phone number=0987654321, address=123 Main St, Cityville]}");
	}
	
	@Test
	void testUpdateContact() {
		// add contact to service
		Service.addContact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville");
	    Service.addContact("D123456789", "Ralph", "Lauren", "0987654321", "123 Main St, Cityville");
	    
	    // update wrong id, no change, expect null
	    assertEquals(Service.updateContact("A123456789", "firstName", "Debra"), null);
	    assertEquals(Service.getAllContacts(),"{D123456789=Contact [id=D123456789, first name=Ralph, last name=Lauren, phone number=0987654321, address=123 Main St, Cityville], "
	    		+ "C123456789=Contact [id=C123456789, first name=John, last name=Doe, phone number=1234567890, address=123 Main St, Cityville]}");
	    
	    // update contact first name , returns new updated contact. 
	    assertTrue(Service.updateContact("C123456789", "firstName", "Debra").equals(Service.getContact("C123456789")));
	    
	    // update invalid first name, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "firstName", "abcdefghijk");});
	    
	    // update contact last name , returns new updated contact. 
	    assertTrue(Service.updateContact("C123456789", "lastName", "Wells").equals(Service.getContact("C123456789")));
	    
	    // update invalid last name, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "lastName", "abcdefghijk");});
	    
	    // update contact phone number, returns new updated contact. 
	    assertTrue(Service.updateContact("C123456789", "phone", "1112223333").equals(Service.getContact("C123456789")));
	    
	    // update invalid phone number, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "phone", "123456789");});
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "phone", "12345678900");});
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "phone", "123456789O");});
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "phone", "ABCDEFGHIJ");});
	    
	    // update contact address, returns new updated contact. 
	    assertTrue(Service.updateContact("C123456789", "address", "456 W Main St. Rems").equals(Service.getContact("C123456789")));
	   
	    // update invalid address, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateContact("C123456789", "address", "123 Main St, Cityville 12345678");});
	    
	    // ensure all changes updated
	    assertEquals(Service.getAllContacts(),"{D123456789=Contact [id=D123456789, first name=Ralph, last name=Lauren, phone number=0987654321, address=123 Main St, Cityville],"
	    		+ " C123456789=Contact [id=C123456789, first name=Debra, last name=Wells, phone number=1112223333, address=456 W Main St. Rems]}");   
	}	
}
