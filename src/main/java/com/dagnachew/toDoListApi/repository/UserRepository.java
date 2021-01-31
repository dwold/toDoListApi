package com.dagnachew.toDoListApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.dagnachew.toDoListApi.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmail(String email);
	
}
