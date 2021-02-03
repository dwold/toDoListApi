package com.dagnachew.toDoListApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String hash;
	
	@JsonIgnore
	private Set<Task> tasks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getid() {
		return id;
	}
	
	public void setid(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "planning", 
		joinColumns = @JoinColumn(name = "taskId", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
}
