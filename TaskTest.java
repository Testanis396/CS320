package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Task;

public class TaskTest {

	@Test
	void testValidTask() {
		Task task = new Task("C1234", "Do Homework", "CS305 milestone 2, CS320 milestone 2");
		assertTrue(task.getId().equals("C1234"));
		assertTrue(task.getName().equals("Do Homework"));
		assertTrue(task.getDescription().equals("CS305 milestone 2, CS320 milestone 2"));
	}

	@Test
	void testID() {
		// check for required unique ID

		// check for max length 10 char
		// len == 11 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("C1234567890", "Do Homework", "CS305 milestone 2, CS320 milestone 2");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, "Do Homework", "CS305 milestone 2, CS320 milestone 2");
		});
		
		// len == 10 expect success
		Task task10 = new Task("C123456789", "Do Homework", "CS305 milestone 2, CS320 milestone 2");
		assertTrue(task10.getId().equals("C123456789"));
		assertTrue(task10.getId().length() == 10);

		// check for immutable
		// id is private, no setter method to change value
		Task task = new Task("D123456789", "Clean House", "Wash Dishes, Vacuum, Sweep, Clean Bathroom");
		String con = task.getId();
		con = "B123456789";
		assertTrue(task.getId().equals("D123456789"));
	}

	@Test
	void testName() {
		// check for required Name

		// check for max length 20 char
		// len == 21 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("C123456789", "ABCDEFGHIJKLMNOPQRSTU", "CS305 milestone 2, CS320 milestone 2");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("C123456789", null, "CS305 milestone 2, CS320 milestone 2");
		});
		
		// len == 20 expect success
		Task task20 = new Task("C123456789", "ABCDEFGHIJKLMNOPQRST", "CS305 milestone 2, CS320 milestone 2");
		assertTrue(task20.getName().equals("ABCDEFGHIJKLMNOPQRST"));
		assertTrue(task20.getName().length() == 20);
	}

	@Test
	void testDescription() {
		// check for required Description

		// check for max length 50 char
		// len == 51 expect exception
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("C123456789", "Do Homework", "ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVWX");
		});
		
		// check for non-null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("C123456789", "Do Homework", null);
		});
				
		// len == 50 expect success
		Task task50 = new Task("C123456789", "Do Homework", "ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVW");
		assertTrue(task50.getDescription().equals("ABCDEFGHIJKLMNOPQRSTUVWXYZ ABCDEFGHIJKLMNOPQRSTUVW"));
		assertTrue(task50.getDescription().length() == 50);
	}
}
