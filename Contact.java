package main;
/**
 * A simple class to hold Contact information 
 * 
 * <p>
 * Notice the overloaded constructor that requires:
 * 
 * id,
 * firstName,
 * lastName,
 * phone, 
 * address 
 * 
 * to be passed when creating.
 * 
 * Also note that no mutator (setter) defined for id, so 
 * it cannot be updated.
 * </p>
 * 
 * @author Tomas Estanislao
 *
 */

public class Contact {
	
	/*
	 * Holds the unique Contact identifier.
	 */
	private String id;
	
	/*
	 * Holds the Contact first name.
	 */
	private String firstName;
	
	/*
	 * Holds the Contact last name.
	 */
	private String lastName;
	
	/*
	 * Holds the Contact phone number.
	 */
	private String phone;
	
	/*
	 * Holds the Contact address.
	 */
	private String address;
	
	/**
	 * Private constructor for Contact.
	 * Prevents instantiation from outside the class itself.
	 * 
	 */
	private Contact() {
		
	}
	
	/**
	 * Constructor with all fields required for creation 
	 * 
	 * @param id, firstName, lastName, phone, address
	 */
	public Contact(String id, String firstName, String lastName, String phone, String address) {
		this();
		
		// validate fields before creation. 
		if (isValid(id, firstName, lastName, phone, address)) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.address = address;
		}
		else {
			throw new IllegalArgumentException("Fields need to be non-null; Address max length 30; Phone length 10; Everything else max length 10.");
		}
	}
	
	/**
	 * Checks if fields are valid
	 * 
	 * @param id, firstName, lastName, phone, address
	 * @return true/false valid
	 */
	public Boolean isValid(String id, String firstName, String lastName, String phone, String address) {
 
		// return true if all fields are valid, false if any are invalid
		// check if phone number string is numeric first
		try {
	        Integer.parseInt(phone);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
		
		return (
		id != null &&
	    firstName != null &&
		lastName != null &&
		phone != null &&
		address != null &&
										
		id.length() <= 10 &&
		firstName.length() <= 10 &&
		lastName.length() <= 10 &&
		phone.length() == 10 &&
		address.length() <= 30);
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @return the phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * set the new first name
	 * 
	 * @param newFirst
	 */
	public void setFirstName(String newFirst) {
		if (isValid(getId(), newFirst, getLastName(), getPhone(), getAddress())) {
			firstName = newFirst;
		}
		else {
			throw new IllegalArgumentException("First name needs to be non-null and max length 10.");
		}
	}
	
	/**
	 * set the new last name
	 * 
	 * @param newLast
	 */
	public void setLastName(String newLast) {
		if (isValid(getId(), getFirstName(), newLast, getPhone(), getAddress())) {
			lastName = newLast;
		}
		else {
			throw new IllegalArgumentException("Last name needs to be non-null and max length 10.");
		}
	}
	
	/**
	 * set the new phone number
	 * 
	 * @param newPhone
	 */
	public void setPhone(String newPhone) {
		if (isValid(getId(), getFirstName(), getLastName(), newPhone, getAddress())) {
			phone = newPhone;
		}
		else {
			throw new IllegalArgumentException("Phone number needs to be non-null and length 10.");
		}
	}
	
	/**
	 * set the new address
	 * 
	 * @param newAddress
	 */
	public void setAddress(String newAddress) {
		if (isValid(getId(), getFirstName(), getLastName(), getPhone(), newAddress)) {
			address = newAddress;
		}
		else {
			throw new IllegalArgumentException("Address needs to be non-null and max length 30.");
		}
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + getId() + ", first name=" + getFirstName()  + ", last name=" + getLastName() + ", phone number=" + getPhone() + ", address=" + getAddress() + "]";
	}
}
