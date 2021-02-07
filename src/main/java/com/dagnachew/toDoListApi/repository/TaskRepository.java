package com.dagnachew.toDoListApi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.dagnachew.toDoListApi.entity.Task;
import com.dagnachew.toDoListApi.util.Priority;


public interface TaskRepository extends CrudRepository<Task, Long> {

	public List<Task> findByStartTimeBetween(Date start, Date end);
	public List<Task> findByPriority(Priority priority);

}
 