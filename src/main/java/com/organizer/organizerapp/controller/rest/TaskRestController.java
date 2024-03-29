package com.organizer.organizerapp.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.organizer.organizerapp.entity.Task;
import com.organizer.organizerapp.exception.TaskNotFoundException;
import com.organizer.organizerapp.service.TaskService;
import com.organizer.organizerapp.specification.TaskSpecifications;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskRestController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public List<Task> findAllTasks(@RequestParam(required = false) Boolean isCompleted,
			@RequestParam(required = false) String title, Pageable pageable) { 

		Specification<Task> spec = Specification
				.where(TaskSpecifications.isCompleted(isCompleted).and(TaskSpecifications.likeTitle(title)));
		
		List<Task> tasks;
		
		try {
			tasks = taskService.findAll(spec, pageable);
		} catch (TaskNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find any tasks", exc);
		}
		
		return tasks;
	}

	@GetMapping("tasks/{taskId}")
	public Task findTask(@PathVariable int taskId) {

		Task task;

		try {
			task = taskService.findById(taskId);
		} catch (TaskNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find task with id: " + taskId, exc);
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
	public Task patchTask(@RequestBody Map<String, Object> update, @PathVariable int taskId) {

		taskService.merge(update, taskId);

		Task task = taskService.findById(taskId);

		return task;
	}

	@DeleteMapping("/tasks/{taskId}")
	public int deleteTask(@PathVariable int taskId) {

		try {
			taskService.findById(taskId);
		} catch (TaskNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Cannot delete task with id: " + taskId + " because it does not exist.", exc);
		}

		taskService.deleteById(taskId);

		return taskId;
	}
}
