package main;
/**
 * A simple class to hold Task information 
 * 
 * <p>
 * Notice the overloaded constructor that requires:
 * 
 * id,
 * name,
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

public class Task {
	
	/*
	 * Holds the unique Task identifier.
	 */
	private String id;
	
	/*
	 * Holds the Task name.
	 */
	private String name;
	
	/*
	 * Holds the Task description.
	 */
	private String description;
	
	/**
	 * Private constructor for Task.
	 * Prevents instantiation from outside the class itself.
	 * 
	 */
	private Task() {
		
	}
	
	/**
	 * Constructor with all fields required for creation 
	 * 
	 * @param id, name, description
	 */
	public Task(String id, String name, String description) {
		this();
		
		// validate fields before creation. 
		if (isValid(id, name, description)) {
			this.id = id;
			this.name = name;
			this.description = description;
		}
		else {
			throw new IllegalArgumentException("Fields need to be non-null; Description max length 50; Name max length 20; ID max length 10");
		}
	}
	
	/**
	 * Checks if fields are valid
	 * 
	 * @param id, name, description
	 * @return true/false valid
	 */
	public Boolean isValid(String id, String name, String description) {
 
		// return true if all fields are valid, false if any are invalid
		return (
		id != null &&
	    name != null &&
		description != null &&
										
		id.length() <= 10 &&
		name.length() <= 20 &&
		description.length() <= 50);
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * set the new name
	 * 
	 * @param newName
	 */
	public void setName(String newName) {
		if (isValid(getId(), newName, getDescription())) {
			name = newName;
		}
		else {
			throw new IllegalArgumentException("Name needs to be non-null and max length 20");
		}
	}
	
	/**
	 * set the new description
	 * 
	 * @param newDescription
	 */
	public void setDescription(String newDescription) {
		if (isValid(getId(), getName(), newDescription)) {
			description = newDescription;
		}
		else {
			throw new IllegalArgumentException("Description needs to be non-null and max length 50");
		}
	}
	
	@Override
	public String toString() {
		return "Task [id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + "]";
	}
}
