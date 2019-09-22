package com.organizer.organizerapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.organizer.organizerapp.entity.Task;
import com.organizer.organizerapp.entity.Weather;
import com.organizer.organizerapp.exception.TaskNotFoundException;
import com.organizer.organizerapp.repository.TaskRepository;
import com.organizer.organizerapp.repository.WeatherRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	@Qualifier("weatherRepositoryImpl")
	private WeatherRepository weatherRepository;
	
	@Override
	public List<Task> findAll(Specification<Task> specification, Pageable pageable) throws TaskNotFoundException {
		
		Page<Task> pageTasks = taskRepository.findAll(specification, pageable);
		 
		List<Task> tasks = pageTasks.getContent();
		
		if (tasks.isEmpty()) {
			throw new TaskNotFoundException("Did not find task.");
		}
		
		this.setWeatherInTasks(tasks);
		
		return tasks;
	}

	@Override
	public Task findById(int id) throws TaskNotFoundException {

		Optional<Task> result = taskRepository.findById(id);
		
		Task task = null;
		
		if(result.isPresent()) {
			task = result.get();
		}
		else {
			throw new TaskNotFoundException("Did not find task.");
		}
		
		this.setWeatherInTask(task);
		
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
	
	@Override
	public void merge(Map<String, Object> update , int taskId) {
		
		taskRepository.merge(update, taskId);
		
	}
	
	
	private void setWeatherInTasks(List<Task> tasks) {
		
		for(Task task: tasks) {
			
			this.setWeatherInTask(task);
		}
	}
	
	private void setWeatherInTask(Task task) {
		
		LocalDateTime taskDateTime = task.getLocalDateTime();
		
		Optional <Weather> result = weatherRepository.getByDateAndTime(taskDateTime);
		
		Weather weather;
		
		if (result.isPresent()) {
			weather = result.get();
		} else {
			weather = null;
		}
		
		task.setWeather(weather);
	}
}
