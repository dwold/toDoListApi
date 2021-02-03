package com.dagnachew.toDoListApi.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.dagnachew.toDoListApi.util.Category;
import com.dagnachew.toDoListApi.util.Priority;
import com.dagnachew.toDoListApi.util.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {

	private long id;
	private String activity;
//	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
//	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@JsonIgnore
	private Category category;
	
	@JsonIgnore
	private Priority priority;
	
	@JsonIgnore
	private TaskStatus status;
	
	@JsonIgnore
	private Set<User> users;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getid() {
		return id;
	}
	
	public void setid(long id) {
		this.id = id;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	@ManyToMany(mappedBy = "tasks")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
