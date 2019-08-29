package com.organizer.organizerapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.organizer.organizerapp.entity.Task;

public interface TaskService {
	
	public List<Task> findAll(Specification<Task> specification, Pageable pageable);
	
	public Task findById(int id);
	
	public void save(Task task);
	
	public void merge(Map<String, Object> update, int taskId);
	
	public void deleteById(int id);
}
