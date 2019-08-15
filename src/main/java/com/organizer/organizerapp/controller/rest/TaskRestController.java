package com.organizer.organizerapp.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizer.organizerapp.entity.Task;
import com.organizer.organizerapp.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskRestController {
	
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public List<Task> getAllTask(){
		return taskService.getAll();
	}

	@GetMapping("tasks/{taskId}")
	public Task getTask(@PathVariable int taskId) {
		
		Task task = taskService.getById(taskId);
		
		if (task == null) {
			throw new RuntimeException("Task is not found - " + taskId);
		}
		
		return task;
	}
	
	@PostMapping("/tasks")
	public Task addTask(@RequestBody Task task) {
		
		task.setId(0);
		
		taskService.save(task);
		
		return task;
	}
	
	@PutMapping("/tasks")
	public Task updateTask(@RequestBody Task task) {
		
		taskService.save(task);
		
		return task;
	}
	
	@PatchMapping("/tasks/{taskId}")
	public Task patchTask(@RequestBody Map<String, Object> update, @PathVariable int taskId ){
		
		taskService.merge(update, taskId);
		
		Task task = taskService.getById(taskId);
		
		return task;
	}
	
	@DeleteMapping("/tasks/{taskId}")
	public int deleteTask(@PathVariable int taskId) {
		
		Task task = taskService.getById(taskId);
		
		if (task == null) {
			throw new RuntimeException("Task is not found - " + taskId);
		}
		
		taskService.deleteById(taskId);
		
		return taskId;
	}

	
}
