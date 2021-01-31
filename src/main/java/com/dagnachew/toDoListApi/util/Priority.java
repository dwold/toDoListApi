package com.dagnachew.toDoListApi.util;

public enum Priority {

	PRIORITY1 ("URGENT AND IMPORTANT"),
	PRIORITY2 ("URGENT BUT NOT IMPORTANT"),
	PRIORITY3 ("NOT URGENT BUT IMPORTANT"),
	PRIORITY4 ("NOT URGENT AND NOT IMPORTANT");

	private String priority;
	
	Priority(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return priority;
	}
	
}
