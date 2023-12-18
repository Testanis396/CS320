package main;

import java.util.*;


/**
 * A singleton Task Service.
 * 
 * CS320 4-1 Milestone
 * 
 * Refactored 3-2 Milestone to complete this one. 
 * 
 * Used my previous CS 230 project work for template. 
 * 
 * 3 main functions:
 * addTask(id, name, description), 
 * updateTask(id, field, newValue), 
 * deleteTask(id).
 * 
 * Helper functions for:
 * deleteAllTasks(), 
 * getTask(id), 
 * getAllTasks().
 *
 * TaskServiceTestSuite runs both TaskTest and TaskServiceTest.
 * 100% code coverage for both Task.java and TaskService.java.
 * 
 * @author Tomas Estanislao
 * 
 */

public class TaskService {
	
	/**
	 * A map of unique ID's and their associated task objects.
	 */
	private Map<String, Task> tasks = new HashMap<>();
	
	/*
	 * Static instance of the TaskService class.
	 * Only one copy of TaskService can exist at any time.
	 * Can be accessed globally, covering the entire application.
	 * Handles all Tasks.
	 */
	private static TaskService instance = null;
	
	/**
	 * Private constructor for TaskService.
	 * Prevents instantiation from outside the class itself.
	 * 
	 */
	private TaskService() {
		
	}
	
	/**
	 * Creates and returns a new TaskService instance, or the current existing instance.
	 * 
	 * @return the TaskService instance (new or existing)
	 */
	public static TaskService getInstance() {
		if (instance == null) {
			instance = new TaskService();
			System.out.println("New Service created.");
		} else {
			System.out.println("Service already opened.");
		}
		return instance;
	}
	
	/**
	 * Construct a new task.
	 * 
	 * @param id, name, description
	 * @return the task instance (new or existing)
	 */
	public Task addTask(String id, String name, String description) {

		// a local task instance, either existing or null
		Task task = this.getTask(id);

		// if not found, make a new task instance and add to map of tasks
		if (task == null) {
			// validity checker in Task class
			task = new Task(id, name, description);
			tasks.put(id, task);
		}

		// return the new/existing task instance to the caller
		return task;
	}
	
	/**
	 * Update a task.
	 * 
	 * @param id, field{"name" ,"description"}, new value
	 * @return the new updated task instance or null if it doesn't exist
	 */
	public Task updateTask(String id, String field, String newVal) {

		// a local task instance, either existing or null
		Task task = this.getTask(id);
		
		if (task != null) {
			if (field == "name") {
				task.setName(newVal);
			}
			else if (field == "description") {
				task.setDescription(newVal);
			}
		}
		
		// return the updated task or null if id doesn't exist
		return task;
	}
	
	/**
	 * Delete an existing task.
	 * 
	 * @param id
	 * @return the updated tasks map
	 */
	public String deleteTask(String id) {

		// removes task if id exists
		tasks.remove(id);
		
		return this.getAllTasks();
	}
	
	/**
	 * Delete all tasks.
	 * 
	 */
	public void deleteAllTasks() {

		tasks.clear();
	}
	
	/**
	 * Get string representation of all tasks.
	 * 
	 * @return String tasks 
	 */	
	public String getAllTasks(){
		
		return (tasks.toString());
	}
	
	/**
	 * Get task with key = id.
	 * 
	 * @param id
	 * @return task if exists, else null
	 */	
	public Task getTask(String id){
		
		return (tasks.get(id));
	}
}
