package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Contact;

public class ContactTest {

	@Test
	void testValidContact() {
		Contact contact = new Contact("C1234", "John", "Doe", "1234567890", "123 Main St, Cityville");
		assertTrue(contact.getId().equals("C1234"));
		assertTrue(contact.getFirstName().equals("John"));
		assertTrue(contact.getLastName().equals("Doe"));
		assertTrue(contact.getPhone().equals("1234567890"));
		assertTrue(contact.getAddress().equals("123 Main St, Cityville"));
	}

	@Test
	void testID() {
		// check for required unique Id

		// check for max length 10 char
		// len == 11 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C1234567890", "John", "Doe", "1234567890", "123 Main St, Cityville");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "John", "Doe", "1234567890", "123 Main St, Cityville");
		});
		
		// len == 10 expect success
		Contact contact10 = new Contact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville");
		assertTrue(contact10.getId().equals("C123456789"));
		assertTrue(contact10.getId().length() == 10);

		// check for immutable
		// id is private, no setter method to change value
		Contact contact = new Contact("D123456789", "Ralph", "Lauren", "1112223333", "123 Main St, Cityville");
		String con = contact.getId();
		con = "B123456789";
		assertTrue(contact.getId().equals("D123456789"));
	}

	@Test
	void testFirstName() {
		// check for required firstName

		// check for max length 10 char
		// len == 11 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "ABCDEFGHIJK", "Doe", "1234567890", "123 Main St, Cityville");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", null, "Doe", "1234567890", "123 Main St, Cityville");
		});
		
		// len == 10 expect success
		Contact contact10 = new Contact("C123456789", "ABCDEFGHIJ", "Doe", "1234567890", "123 Main St, Cityville");
		assertTrue(contact10.getFirstName().equals("ABCDEFGHIJ"));
		assertTrue(contact10.getFirstName().length() == 10);
	}

	@Test
	void testLastName() {
		// check for required lastName

		// check for max length 10 char
		// len == 11 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "ABCDEFGHIJK", "1234567890", "123 Main St, Cityville");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Contact("C123456789", "John", null, "1234567890", "123 Main St, Cityville");
		});
		
		// len == 10 expect success
		Contact contact10 = new Contact("C123456789", "John", "ABCDEFGHIJ", "1234567890", "123 Main St, Cityville");
		assertTrue(contact10.getLastName().equals("ABCDEFGHIJ"));
		assertTrue(contact10.getLastName().length() == 10);
	}

	@Test
	void testPhone() {
		// check for required phone

		// check for length == 10
		// len == 11 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "Doe", "12345678901", "123 Main St, Cityville");
		});
		
		// len == 9 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "Doe", "123456789", "123 Main St, Cityville");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "Doe", null, "123 Main St, Cityville");
		});
			
		// phone is non-numeric expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "Doe", "123456789O", "123 Main St, Cityville");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "Doe", "ABCDEFGHIJ", "123 Main St, Cityville");
		});
		
		// len == 10 expect success
		Contact contact10 = new Contact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville");
		assertTrue(contact10.getPhone().equals("1234567890"));
		assertTrue(contact10.getPhone().length() == 10);	
	}

	@Test
	void testAddress() {
		// check for required address

		// check for max length 30 char
		// len == 31 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville 12345678");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		new Contact("C123456789", "John", "Doe", "1234567890", null);
		});
		
		// len == 30 expect success
		Contact contact10 = new Contact("C123456789", "John", "Doe", "1234567890", "123 Main St, Cityville 1111111");
		assertTrue(contact10.getAddress().equals("123 Main St, Cityville 1111111"));
		assertTrue(contact10.getAddress().length() == 30);
	}
}
