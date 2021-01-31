package com.dagnachew.toDoListApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.dagnachew.toDoListApi.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
