package com.organizer.organizerapp.dao;

import java.util.List;
import java.util.Map;

import com.organizer.organizerapp.entity.Task;

public interface TaskDAO {
	
	public List<Task> getAll();
	
	public Task getById(int id);
	
	public List<Task> getByCompleted(boolean isCompleted);
	
	public void save(Task task);
	
	public void merge(Map<String, Object> update, int taskId);
	
	public void deleteById(int id);

}
