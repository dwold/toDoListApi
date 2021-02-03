package com.dagnachew.toDoListApi.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.dagnachew.toDoListApi.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
//	public Task findAllByDateBetween(Date startTime);
	public Iterable<Task> findAll(Date startTime);
	

}
