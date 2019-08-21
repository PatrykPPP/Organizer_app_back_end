package com.organizer.organizerapp.repository;

import java.util.Map;

public interface CustomizedTaskRepository {
	
	public void merge(Map<String, Object> update, int taskId);

}
