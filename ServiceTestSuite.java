package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	ContactTest.class, ContactServiceTest.class, 
	TaskTest.class, TaskServiceTest.class,
	AppointmentTest.class, AppointmentServiceTest.class
	})

/**
 * CS320 6-1 Project One
 * 
 * Moved tests for null entries to before successful entries. 
 * 
 * Added try{Integer.parseInt(phone)}/catch block, to check if string is numeric, in Contact.isValid().
 * Added corresponding tests to both Contact and ContactService for non-numeric phone string arguments. 
 * 
 * ServiceTestSuite runs all class objects and services.
 * 100% code coverage for all Contact, Task, Appointment, 
 * ContactService, TaskService, and AppointmentService.java.
 * All test coverage is as expected. Asserts are executed. 
 * 
 * @author Tomas Estanislao
 * 
 */

public class ServiceTestSuite {

}
