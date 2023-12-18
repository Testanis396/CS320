package main;

import java.util.*;


/**
 * A singleton Appointment Service.
 * 
 * CS320 5-1 Milestone
 * 
 * Refactored 4-1 Milestone to complete this one. 
 * 
 * Since neither date, nor description said, "shall not be updatable,"
 * I decided to allow updating by fields and tested for that as well. 
 * Used chatGPT to help generate code for random Date's for testing. 
 * 
 * Used my previous CS 230 project work for template. 
 * 
 * 3 main functions:
 * addAppointment(id, date, description), 
 * updateAppointment(id, field, newValue), 
 * deleteAppointment(id).
 * 
 * Helper functions for:
 * deleteAllAppointments(), 
 * getAppointment(id), 
 * getAllAppointments().
 *
 * AppointmentServiceTestSuite runs both AppointmentTest and AppointmentServiceTest.
 * 100% code coverage for both Appointment.java and AppointmentService.java.
 * 
 * @author Tomas Estanislao
 * 
 */

public class AppointmentService {
	
	/**
	 * A map of unique ID's and their associated task objects.
	 */
	private Map<String, Appointment> appointments = new HashMap<>();
	
	/*
	 * Static instance of the AppointmentService class.
	 * Only one copy of AppointmentService can exist at any time.
	 * Can be accessed globally, covering the entire application.
	 * Handles all Tasks.
	 */
	private static AppointmentService instance = null;
	
	/**
	 * Private constructor for AppointmentService.
	 * Prevents instantiation from outside the class itself.
	 * 
	 */
	private AppointmentService() {
		
	}
	
	/**
	 * Creates and returns a new AppointmentService instance, or the current existing instance.
	 * 
	 * @return the AppointmentService instance (new or existing)
	 */
	public static AppointmentService getInstance() {
		if (instance == null) {
			instance = new AppointmentService();
			System.out.println("New Service created.");
		} else {
			System.out.println("Service already opened.");
		}
		return instance;
	}
	
	/**
	 * Construct a new Appointment.
	 * 
	 * @param id, date, description
	 * @return the Appointment instance (new or existing)
	 */
	public Appointment addAppointment(String id, Date date, String description) {

		// a local appointment instance, either existing or null
		Appointment appointment = this.getAppointment(id);

		// if not found, make a new appointment instance and add to map of appointments
		if (appointment == null) {
			// validity checker in Appointment class
			appointment = new Appointment(id, date, description);
			appointments.put(id, appointment);
		}

		// return the new/existing appointment instance to the caller
		return appointment;
	}
	
	/**
	 * Update an appointment.
	 * 
	 * @param id, field{"date" ,"description"}, Object(either String or Date)new value
	 * @return the new updated appointment instance or null if it doesn't exist
	 */
	public Appointment updateAppointment(String id, String field, Object newVal) {

		// a local appointment instance, either existing or null
		Appointment appointment = this.getAppointment(id);
		
		if (appointment != null) {
			if (field == "date") {
				appointment.setDate((Date)newVal);
			}
			else if (field == "description") {
				appointment.setDescription((String)newVal);
			}
		}
		
		// return the updated appointment or null if id doesn't exist
		return appointment;
	}
	
	/**
	 * Delete an existing appointment.
	 * 
	 * @param id
	 * @return the updated appointments map
	 */
	public String deleteAppointment(String id) {

		// removes appointment if id exists
		appointments.remove(id);
		
		return this.getAllAppointments();
	}
	
	/**
	 * Delete all appointments.
	 * 
	 */
	public void deleteAllAppointments() {

		appointments.clear();
	}
	
	/**
	 * Get string representation of all appointments.
	 * 
	 * @return String appointments 
	 */	
	public String getAllAppointments(){
		
		return (appointments.toString());
	}
	
	/**
	 * Get appointment with key = id.
	 * 
	 * @param id
	 * @return appointment if exists, else null
	 */	
	public Appointment getAppointment(String id){
		
		return (appointments.get(id));
	}
}
