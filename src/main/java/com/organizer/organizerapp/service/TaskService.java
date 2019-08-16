package com.organizer.organizerapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.organizer.organizerapp.entity.Task;

public interface TaskService {

	public List<Task> findAll(Pageable pageable);
	
	public Task findById(int id);
	
	public List<Task> findByCompleted(boolean isCompleted);
	
	public void save(Task task);
	
	public void merge(Map<String, Object> update, int taskId);
	
	public void deleteById(int id);
}
