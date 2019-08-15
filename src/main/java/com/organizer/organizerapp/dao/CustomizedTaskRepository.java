package com.organizer.organizerapp.dao;

import java.util.Map;

public interface CustomizedTaskRepository {
	
	public void merge(Map<String, Object> update, int taskId);

}
