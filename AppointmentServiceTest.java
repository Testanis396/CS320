package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AppointmentService;

class AppointmentServiceTest {

	
	AppointmentService Service = AppointmentService.getInstance();
	
	Date randomFutureDate = new Date();
	Date randomFutureDate2 = new Date();
	Date randomPastDate = new Date();
	
	
	@BeforeEach
	public void initialize() {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

        calendar.setTime(currentDate);
        calendar1.setTime(currentDate);
        calendar2.setTime(currentDate);

        Random random = new Random();
        int randomDays = random.nextInt(365); // Up to 1 year
        int randomMonths = random.nextInt(12); // Up to 12 months
        int randomYears = random.nextInt(10); // Up to 10 years
        calendar.add(Calendar.DAY_OF_MONTH, randomDays);
        calendar.add(Calendar.MONTH, randomMonths);
        calendar.add(Calendar.YEAR, randomYears);
        randomDays = random.nextInt(365);
        randomMonths = random.nextInt(12);
        randomYears = random.nextInt(10);
        calendar1.add(Calendar.DAY_OF_MONTH, -randomDays);
        calendar1.add(Calendar.MONTH, -randomMonths);
        calendar1.add(Calendar.YEAR, -randomYears);
        randomDays = random.nextInt(365);
        randomMonths = random.nextInt(12);
        randomYears = random.nextInt(10);
        calendar2.add(Calendar.DAY_OF_MONTH, randomDays);
        calendar2.add(Calendar.MONTH, randomMonths);
        calendar2.add(Calendar.YEAR, randomYears);
        
        randomFutureDate = calendar.getTime();
        randomFutureDate2 = calendar2.getTime();
        randomPastDate = calendar1.getTime();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		Service.deleteAllAppointments();
	}

	@Test
	void testAddAppointment() {
		// add appointment to service
		Service.addAppointment("C1234", randomFutureDate, "Dentist Appointment");  
	    assertEquals(Service.getAllAppointments(),"{C1234=Appointment [id=C1234, date=" + Service.getAppointment("C1234").getDate().toString() + ", description=Dentist Appointment]}");
	    
	    // add duplicate appointment, expect no change
	    Service.addAppointment("C1234", randomFutureDate, "Dentist Appointment");  
	    assertEquals(Service.getAllAppointments(),"{C1234=Appointment [id=C1234, date=" + Service.getAppointment("C1234").getDate().toString() + ", description=Dentist Appointment]}");
	    
	    // add duplicate id appointment, expect no change
	    Service.addAppointment("C1234", randomFutureDate, "Haircut Appointment");  
	    assertEquals(Service.getAllAppointments(),"{C1234=Appointment [id=C1234, date=" + Service.getAppointment("C1234").getDate().toString() + ", description=Dentist Appointment]}");
	    
	    // add new id appointment, expect addition 
	    Service.addAppointment("D1234", randomFutureDate2, "Haircut Appointment"); 
	    assertEquals(Service.getAllAppointments(),"{C1234=Appointment [id=C1234, date=" + Service.getAppointment("C1234").getDate().toString() + ", description=Dentist Appointment], "
	    		+ "D1234=Appointment [id=D1234, date=" + Service.getAppointment("D1234").getDate().toString() + ", description=Haircut Appointment]}");
	    // check date
	    assertTrue(Service.getAppointment("D1234").getDate().after(new Date()));
	    assertTrue(Service.getAppointment("C1234").getDate().after(new Date()));
	}
	
	@Test
	void testDeleteAppointment() {
		// add appointments to service
		Service.addAppointment("C1234", randomFutureDate, "Dentist Appointment");  
	    Service.addAppointment("D1234", randomFutureDate2, "Haircut Appointment"); 
	    
	    // delete wrong id, no change
	    assertEquals(Service.deleteAppointment("A1234"),"{C1234=Appointment [id=C1234, date=" + Service.getAppointment("C1234").getDate().toString() + ", description=Dentist Appointment], "
	    		+ "D1234=Appointment [id=D1234, date=" + Service.getAppointment("D1234").getDate().toString() + ", description=Haircut Appointment]}");
	    
	    // delete Cid, returns new total appointments 
	    assertEquals(Service.deleteAppointment("C1234"),"{D1234=Appointment [id=D1234, date=" + Service.getAppointment("D1234").getDate().toString() + ", description=Haircut Appointment]}");
	}
	
	@Test
	void testUpdateAppointment() {
		// add appointments to service
		Service.addAppointment("C1234", randomFutureDate, "Dentist Appointment");  
	    Service.addAppointment("D1234", randomFutureDate, "Haircut Appointment"); 
			    
	    // update wrong id, no change, expect null
	    assertEquals(Service.updateAppointment("A1234", "date", randomFutureDate2 ), null);
	    assertEquals(Service.getAllAppointments(),"{C1234=Appointment [id=C1234, date=" + Service.getAppointment("C1234").getDate().toString() + ", description=Dentist Appointment], "
	    		+ "D1234=Appointment [id=D1234, date=" + Service.getAppointment("D1234").getDate().toString() + ", description=Haircut Appointment]}");
	    
	    // update appointment date , returns new updated appointment. 
	    assertTrue(Service.updateAppointment("C1234", "date", randomFutureDate2).equals(Service.getAppointment("C1234")));
	    
	    // check date
	    assertTrue(Service.getAppointment("C1234").getDate().after(new Date()));
	    
	    // update invalid date, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateAppointment("C1234", "date", randomPastDate);});
	    
	    // update appointment description, returns new updated appointment. 
	    assertTrue(Service.updateAppointment("C1234", "description", "Eye Appointment").equals(Service.getAppointment("C1234")));
	   
	    // update invalid description, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateAppointment("C1234", "description", "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwx");});

	    // ensure all changes updated
	    assertEquals(Service.getAllAppointments(),"{C1234=Appointment [id=C1234, date=" + randomFutureDate2.toString() + ", description=Eye Appointment], "
	    		+ "D1234=Appointment [id=D1234, date=" + Service.getAppointment("D1234").getDate().toString() + ", description=Haircut Appointment]}");
	}	
}
