package com.organizer.organizerapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizer.organizerapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>, CustomizedTaskRepository {
	
	List<Task> findByIsCompleted(Boolean isCompleted);
	
	

}
