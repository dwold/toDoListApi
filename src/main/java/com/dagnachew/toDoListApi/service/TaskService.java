package com.dagnachew.toDoListApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dagnachew.toDoListApi.entity.Task;
import com.dagnachew.toDoListApi.repository.TaskRepository;
import com.dagnachew.toDoListApi.util.Priority;
import com.dagnachew.toDoListApi.util.TaskStatus;

@Service
public class TaskService {

private static final Logger logger = LogManager.getLogger(TaskService.class);
	
	@Autowired
	private TaskRepository repo;
	
	public Task getTaskById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve task: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Task> getTasks() {
		return repo.findAll();
	}
	
	public Task creatTask(Task task) {
		return repo.save(task);
	}
	
	public Task updateTask(Task task, Long id) throws Exception {
		try {
			Task oldTask = repo.findOne(id);
			oldTask.setActivity(task.getActivity());
			oldTask.setStartTime(task.getStartTime());
			oldTask.setEndTime(task.getEndTime());
			return repo.save(oldTask);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update task: " + id, e);
			throw new Exception("Unable to update task.");
		}
	}
	
	public Task updateTaskStatus(Long id, TaskStatus status) throws Exception {
		try {
			Task oldTask = repo.findOne(id);
			oldTask.setStatus(status);
			return repo.save(oldTask);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update task status: " + id, e);
			throw new Exception("Unable to update task status.");
		}
	}
	
	public Task updateTaskPriority(Long id, Priority priority) throws Exception {
		try {
			Task oldTask = repo.findOne(id);
			oldTask.setPriority(priority);
			return repo.save(oldTask);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update task priority: " + id, e);
			throw new Exception("Unable to update task priority.");
		}
	}
	
	public void removeTask(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete task: " + id, e);
			throw new Exception("Unable to delete task.");
		}
	}
	
}
