package com.organizer.organizerapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizer.organizerapp.dao.TaskDAO;
import com.organizer.organizerapp.dao.WeatherDAO;
import com.organizer.organizerapp.entity.Task;
import com.organizer.organizerapp.entity.Weather;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	@Qualifier("taskDAOHibernateImpl")
	private TaskDAO taskDAO;
	
	@Autowired
	@Qualifier("weatherDAOImpl")
	private WeatherDAO weatherDAO;

	@Override
	@Transactional
	public List<Task> getAll() {

		return taskDAO.getAll();
	}

	@Override
	@Transactional
	public Task getById(int id) {

		return taskDAO.getById(id);
	}

	@Override
	@Transactional
	public void save(Task task) {
		
		taskDAO.save(task);
	}

	@Override
	@Transactional
	public void deleteById(int id) {

		taskDAO.deleteById(id);
	}

	@Override
	@Transactional
	public List<Task> getByCompleted(boolean isCompleted) {
		
		List<Task> tasks = taskDAO.getByCompleted(isCompleted);
		
		for(Task task: tasks) {
			
			LocalDateTime taskDateTime = task.getLocalDateTime();
			
			Weather weather = weatherDAO.getByDateAndTime(taskDateTime);
			
			task.setWeather(weather);
		}
		
		return tasks;
	}

	@Override
	@Transactional
	public void merge(Map<String, Object> update , int taskId) {
		
		taskDAO.merge(update, taskId);
		
	} 
}
