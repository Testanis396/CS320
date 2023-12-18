package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import main.Appointment;

public class AppointmentTest {

	Date randomFutureDate = new Date();
	Date randomPastDate = new Date();
	
	
	@BeforeEach
	public void initialize() {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

        calendar.setTime(currentDate);
        calendar1.setTime(currentDate);

        Random random = new Random();
        int randomDays = random.nextInt(365); // Up to 1 year
        int randomMonths = random.nextInt(12); // Up to 12 months
        int randomYears = random.nextInt(10); // Up to 10 years
        calendar.add(Calendar.DAY_OF_MONTH, randomDays);
        calendar.add(Calendar.MONTH, randomMonths);
        calendar.add(Calendar.YEAR, randomYears);
        calendar1.add(Calendar.DAY_OF_MONTH, -randomDays);
        calendar1.add(Calendar.MONTH, -randomMonths);
        calendar1.add(Calendar.YEAR, -randomYears);
        
        randomFutureDate = calendar.getTime();
        randomPastDate = calendar1.getTime();
	}

	@Test
	void testValidAppointment() {
		Appointment appointment = new Appointment("C1234", randomFutureDate, "Dentist Appointment");
		assertTrue(appointment.getId().equals("C1234"));
		assertTrue(appointment.getDate().equals(randomFutureDate));
		assertTrue(appointment.getDescription().equals("Dentist Appointment"));
	}

	@Test
	void testID() {
		// check for required unique ID

		// check for max length 10 char
		// len == 11 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("C1234567890", randomFutureDate, "Dentist Appointment");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment(null, randomFutureDate, "Dentist Appointment");
		});
		
		// len == 10 expect success
		Appointment appointment10 = new Appointment("C123456789", randomFutureDate, "Dentist Appointment");
		assertTrue(appointment10.getId().equals("C123456789"));
		assertTrue(appointment10.getId().length() == 10);
		
		// check for immutable
		// id is private, no setter method to change value
		Appointment appointment = new Appointment("D123456789", randomFutureDate, "Dentist Appointment");
		String con = appointment.getId();
		con = "B123456789";
		assertTrue(appointment.getId().equals("D123456789"));
	}

	@Test
	void testDate() {
		// check for required Date

		// check for past expect exception
		assertTrue(randomPastDate.before(new Date()));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("C123456789", randomPastDate, "Dentist Appointment");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("C123456789", null, "Dentist Appointment");;
		});
		
		// check for future expect success
		Appointment appointment20 = new Appointment("C123456789", randomFutureDate, "Dentist Appointment");
		assertTrue(appointment20.getDate().after(new Date()));
	}

	@Test
	void testDescription() {
		// check for required Description

		// check for max length 50 char
		// len == 51 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("C1234", randomFutureDate, "ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWX");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("C1234", randomFutureDate, null);
		});
		
		// len == 50 expect success
		Appointment appointment50 = new Appointment("C1234", randomFutureDate, "ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVW");
		assertTrue(appointment50.getDescription().equals("ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVW"));
		assertTrue(appointment50.getDescription().length() == 50);
	}
}
