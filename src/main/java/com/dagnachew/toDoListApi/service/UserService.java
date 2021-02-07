package com.dagnachew.toDoListApi.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dagnachew.toDoListApi.entity.Task;
import com.dagnachew.toDoListApi.entity.User;
import com.dagnachew.toDoListApi.repository.TaskRepository;
import com.dagnachew.toDoListApi.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	public User assignUser(Set<Long> taskIds, Long userId) throws Exception {
		try {
			User user = repo.findOne(userId);
			user.setTasks(convertToTaskSet(taskRepo.findAll(taskIds)));
			return repo.save(user);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to create new plan for user: " + userId, e);
			throw e;
		}
	}
	
	public User getUserById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve user: " + id, e);
			throw e;
		}
	}
	
	public Iterable<User> getUsers() {
		return repo.findAll();
	}

	public void deleteUser(Long id) {
		repo.delete(id);
	}
	
	public User updateUser(Long id, User user) {
		User foundUser = repo.findOne(id);
		if (foundUser != null) {
			foundUser.setFirstName(user.getFirstName());
			foundUser.setLastName(user.getLastName());
			foundUser.setEmail(user.getEmail());
			repo.save(foundUser);
		}
		return foundUser;
	}
	
	private Set<Task> convertToTaskSet(Iterable<Task> iterable) {
		Set<Task> set = new HashSet<Task>();
		for (Task task : iterable) {
			set.add(task);
		}
		return set;
	}

}
