package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Task;
import main.TaskService;

class TaskServiceTest {

	
	TaskService Service = TaskService.getInstance();
	
	@AfterEach
	void tearDown() throws Exception {
		Service.deleteAllTasks();
	}

	@Test
	void testAddTask() {
		// add task to service
		Service.addTask("C123456789", "Do Homework", "CS305 milestone 2, CS320 milestone 2");    
	    assertEquals(Service.getAllTasks(),"{C123456789=Task [id=C123456789, name=Do Homework, description=CS305 milestone 2, CS320 milestone 2]}");
	    
	    // add duplicate task, expect no change
	    Service.addTask("C123456789", "Do Homework", "CS305 milestone 2, CS320 milestone 2");    
	    assertEquals(Service.getAllTasks(),"{C123456789=Task [id=C123456789, name=Do Homework, description=CS305 milestone 2, CS320 milestone 2]}");
	    
	    // add duplicate id task, expect no change
	    Service.addTask("C123456789", "Clean House", "Wash Dishes, Vacuum, Sweep, Clean Bathroom");
	    assertEquals(Service.getAllTasks(),"{C123456789=Task [id=C123456789, name=Do Homework, description=CS305 milestone 2, CS320 milestone 2]}");
	    
	    // add new id task, expect addition 
	    Service.addTask("D123456789", "Clean House", "Wash Dishes, Vacuum, Sweep, Clean Bathroom");
	    assertEquals(Service.getAllTasks(),"{D123456789=Task [id=D123456789, name=Clean House, description=Wash Dishes, Vacuum, Sweep, Clean Bathroom], "
	    		+ "C123456789=Task [id=C123456789, name=Do Homework, description=CS305 milestone 2, CS320 milestone 2]}");
	}
	
	@Test
	void testDeleteTask() {
		// add tasks to service
		Service.addTask("C123456789", "Do Homework", "CS305 milestone 2, CS320 milestone 2");    
	    Service.addTask("D123456789", "Clean House", "Wash Dishes, Vacuum, Sweep, Clean Bathroom");
	    
	    // delete wrong id, no change
	    assertEquals(Service.deleteTask("A123456789"),"{D123456789=Task [id=D123456789, name=Clean House, description=Wash Dishes, Vacuum, Sweep, Clean Bathroom], "
	    		+ "C123456789=Task [id=C123456789, name=Do Homework, description=CS305 milestone 2, CS320 milestone 2]}");
	    
	    // delete Cid, returns new total tasks 
	    assertEquals(Service.deleteTask("C123456789"),"{D123456789=Task [id=D123456789, name=Clean House, description=Wash Dishes, Vacuum, Sweep, Clean Bathroom]}");
	}
	
	@Test
	void testUpdateTask() {
		// add tasks to service
		Service.addTask("C123456789", "Do Homework", "CS305 milestone 2, CS320 milestone 2");    
		Service.addTask("D123456789", "Clean House", "Wash Dishes, Vacuum, Sweep, Clean Bathroom");
			    
	    // update wrong id, no change, expect null
	    assertEquals(Service.updateTask("A123456789", "name", "Schoolwork"), null);
	    assertEquals(Service.getAllTasks(),"{D123456789=Task [id=D123456789, name=Clean House, description=Wash Dishes, Vacuum, Sweep, Clean Bathroom], "
	    		+ "C123456789=Task [id=C123456789, name=Do Homework, description=CS305 milestone 2, CS320 milestone 2]}");
	    
	    // update task name , returns new updated task. 
	    assertTrue(Service.updateTask("C123456789", "name", "Schoolwork").equals(Service.getTask("C123456789")));
	    
	    // update invalid name, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateTask("C123456789", "name", "abcdefghijklmnopqrstu");});
	    
	    // update task description, returns new updated task. 
	    assertTrue(Service.updateTask("C123456789", "description", "CS305 journal 2, CS320 journal 2").equals(Service.getTask("C123456789")));
	   
	    // update invalid description, expect exception
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	Service.updateTask("C123456789", "description", "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwx");});

	    // ensure all changes updated
	    assertEquals(Service.getAllTasks(),"{D123456789=Task [id=D123456789, name=Clean House, description=Wash Dishes, Vacuum, Sweep, Clean Bathroom], "
	    		+ "C123456789=Task [id=C123456789, name=Schoolwork, description=CS305 journal 2, CS320 journal 2]}");   
	}	
}
