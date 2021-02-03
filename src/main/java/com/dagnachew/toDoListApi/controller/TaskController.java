package com.dagnachew.toDoListApi.controller;

import java.util.Date;

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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getTask(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getTaskById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/date/{startTime}", method=RequestMethod.GET)
	public ResponseEntity<Object> getTask2(@PathVariable("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime) {
//	public ResponseEntity<Object> getTask2(@PathVariable Date startTime) {
		try {
			return new ResponseEntity<Object>(service.getTaskByDate(startTime), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
//	@RequestMapping(value="/date", method=RequestMethod.GET)
//	public ResponseEntity<Object> browseTaskByDate(@RequestBody Date startTime) {
//		try {
//			return new ResponseEntity<Object>(service.findAllByDateBetween(startTime), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getTasks() {
		return new ResponseEntity<Object>(service.getTasks(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateTask(task, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/status/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTaskStatus(@RequestBody TaskStatus status, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateTaskStatus(id, status), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/priority/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTaskPriority(@RequestBody Priority priority, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateTaskPriority(id, priority), HttpStatus.OK);
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
	
}
