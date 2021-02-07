package com.dagnachew.toDoListApi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dagnachew.toDoListApi.entity.Task;
import com.dagnachew.toDoListApi.service.TaskService;
import com.dagnachew.toDoListApi.util.Priority;
import com.dagnachew.toDoListApi.util.TaskStatus;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createTask(@RequestBody Task task) {
		return new ResponseEntity<Object>(service.creatTask(task), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateTask(task, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
		try {
			service.removeTask(id);
			return new ResponseEntity<Object>("Successfully deleted user with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getTask(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getTaskById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getTasks() {
		return new ResponseEntity<Object>(service.getTasks(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/date/{start}/{end}", method=RequestMethod.GET)
	public List<Task> getTaskByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date start, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date end) {
			return service.getTaskByDate(start, end);
	}

	@RequestMapping(value="/priority/{id}/{priority}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTaskPriority(@PathVariable Long id, @PathVariable Priority priority) {
		try {
			return new ResponseEntity<Object>(service.updateTaskPriority(id, priority), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/priority/{priority}", method=RequestMethod.GET)
	public List<Task> getTaskByPriority(@PathVariable Priority priority) {
			return service.getTaskByPriority(priority);
	}
	
	@RequestMapping(value="/status/{id}/{status}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTaskStatus(@PathVariable Long id, @PathVariable TaskStatus status) {
		try {
			return new ResponseEntity<Object>(service.updateTaskStatus(id, status), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
