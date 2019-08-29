package com.organizer.organizerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.organizer.organizerapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>, CustomizedTaskRepository, JpaSpecificationExecutor<Task> {
	
	List<Task> findByIsCompleted(Boolean isCompleted);
}
