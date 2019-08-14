package com.organizer.organizerapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.organizer.organizerapp.dao.TaskRepository;
import com.organizer.organizerapp.dao.WeatherDAO;
import com.organizer.organizerapp.entity.Task;
import com.organizer.organizerapp.entity.Weather;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	@Qualifier("weatherDAOImpl")
	private WeatherDAO weatherDAO;

	@Override
	public List<Task> getAll() {

		return taskRepository.findAll();
	}

	@Override
	public Task getById(int id) {

		Optional<Task> result = taskRepository.findById(id);
		
		Task task = null;
		
		if(result.isPresent()) {
			task = result.get();
		}
		else {
			throw new RuntimeException("Did not find task.");
		}
		
		return task;
	}

	@Override
	public void save(Task task) {
		
		taskRepository.save(task);
	}

	@Override
	public void deleteById(int id) {

		taskRepository.deleteById(id);
	}
/*
	@Override
	public List<Task> getByCompleted(boolean isCompleted) {
		
		List<Task> tasks = taskRepository.getByCompleted(isCompleted);
		
		for(Task task: tasks) {
			
			LocalDateTime taskDateTime = task.getLocalDateTime();
			
			Weather weather = weatherDAO.getByDateAndTime(taskDateTime);
			
			task.setWeather(weather);
		}
		
		return tasks;
	}

	@Override
	public void merge(Map<String, Object> update , int taskId) {
		
		taskRepository.merge(update, taskId);
		
	}
	*/

	@Override
	public List<Task> getByCompleted(boolean isCompleted) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void merge(Map<String, Object> update, int taskId) {
		// TODO Auto-generated method stub
		
	} 
}
