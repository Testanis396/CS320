package main;

import java.util.Date;

/**
 * A simple class to hold Appointment information 
 * 
 * <p>
 * Notice the overloaded constructor that requires:
 * 
 * id,
 * date,
 * description,
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

public class Appointment {
	
	/*
	 * Holds the unique Appointment identifier.
	 */
	private String id;
	
	/*
	 * Holds the Appointment date.
	 */
	private Date date;
	
	/*
	 * Holds the Appointment description.
	 */
	private String description;
	
	/**
	 * Private constructor for Appointment.
	 * Prevents instantiation from outside the class itself.
	 * 
	 */
	private Appointment() {
		
	}
	
	/**
	 * Constructor with all fields required for creation 
	 * 
	 * @param id, date, description
	 */
	public Appointment(String id, Date date, String description) {
		this();
		
		// validate fields before creation. 
		if (isValid(id, date, description)) {
			this.id = id;
			this.date = date;
			this.description = description;
		}
		else {
			throw new IllegalArgumentException("Fields need to be non-null; Description max length 50; Date in the future; ID max length 10.");
		}
	}
	
	/**
	 * Checks if fields are valid
	 * 
	 * @param id, date, description
	 * @return true/false valid
	 */
	public Boolean isValid(String id, Date date, String description) {
 
		// return true if all fields are valid, false if any are invalid
		return (
		id != null &&
	    date != null &&
		description != null &&
										
		id.length() <= 10 &&
		date.after(new Date()) &&
		description.length() <= 50);
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * set the new date
	 * 
	 * @param newDate
	 */
	public void setDate(Date newDate) {
		if (isValid(getId(), newDate, getDescription())) {
			date = newDate;
		}
		else {
			throw new IllegalArgumentException("Date must be non-null and in the future.");
		}
	}
	
	/**
	 * set the new description
	 * 
	 * @param newDescription
	 */
	public void setDescription(String newDescription) {
		if (isValid(getId(), getDate(), newDescription)) {
			description = newDescription;
		}
		else {
			throw new IllegalArgumentException("Description needs to be non-null and max length 50.");
		}
	}
	
	@Override
	public String toString() {
		return "Appointment [id=" + getId() + ", date=" + getDate() + ", description=" + getDescription() + "]";
	}
}
