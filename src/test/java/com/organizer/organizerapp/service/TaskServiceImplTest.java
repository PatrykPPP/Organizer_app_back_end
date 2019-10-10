package com.organizer.organizerapp.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.organizer.organizerapp.entity.Task;
import com.organizer.organizerapp.entity.Weather;
import com.organizer.organizerapp.exception.TaskNotFoundException;
import com.organizer.organizerapp.repository.TaskRepository;
import com.organizer.organizerapp.repository.WeatherRepository;

@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

	
	@TestConfiguration
	static class TaskServiceImplTestContextConfig {

		@Bean
		public TaskService taskService() {
			return new TaskServiceImpl();
		}
	}
	

	@Autowired
	private TaskService taskService;

	@MockBean
	private TaskRepository taskRepository;

	@MockBean
	@Qualifier("weatherRepositoryImpl")
	private WeatherRepository weatherRepository;

	@Test
	public void whenFindById0_thenReturnTask() {
		
		Task task = new Task();
		Weather weather = new Weather();
		
		Mockito.when(taskRepository.findById(0)).thenReturn(Optional.of(task));
		Mockito.when(weatherRepository.getByDateAndTime(task.getLocalDateTime())).thenReturn(Optional.of(weather));

		Task taskTest = taskService.findById(0);

		assertThat(taskTest).isEqualTo(task);
	}

	@Test(expected = TaskNotFoundException.class)
	public void whenFindById1_thenThrowTaskNotFoundException() {
		
		Mockito.when(taskRepository.findById(0)).thenReturn(Optional.ofNullable(null));

		taskService.findById(0);
	}
	
	@Test
	public void whenFindAll_thenReturnListOfTasks() {
		
		Task task1 = new Task();
		Task task2 = new Task();
		Task task3 = new Task();
		
		List<Task> tasksFromRepository = new ArrayList<>();
		tasksFromRepository.add(task1);
		tasksFromRepository.add(task2);
		tasksFromRepository.add(task3);
		
		Weather weather = new Weather();
		weather.setTempInC(20);
		
		Specification<Task> spec = Specification.where(null);
		Pageable pageable = Pageable.unpaged();
		Page<Task> tasksPage = new PageImpl<>(tasksFromRepository);
		
		Mockito.when(taskRepository.findAll(spec, pageable)).thenReturn(tasksPage);
		
		Mockito.when(weatherRepository.getByDateAndTime(task1.getLocalDateTime())).thenReturn(Optional.of(weather));
		Mockito.when(weatherRepository.getByDateAndTime(task2.getLocalDateTime())).thenReturn(Optional.of(weather));
		Mockito.when(weatherRepository.getByDateAndTime(task3.getLocalDateTime())).thenReturn(Optional.of(weather));
		
		List<Task> foundTasks = taskService.findAll(spec, pageable);

		Task lastTask = foundTasks.get(2);
		
		assertThat(lastTask.getWeather().getTempInC()).isEqualTo(weather.getTempInC());	
	}
	
	@Test(expected = TaskNotFoundException.class)
	public void whenFindAll_thenReturnTaskNotFoundException() {
		
		List<Task> tasksFromRepository = new ArrayList<>();
		
		Specification<Task> spec = Specification.where(null);
		Pageable pageable = Pageable.unpaged();
		Page<Task> tasksPage = new PageImpl<>(tasksFromRepository);
		
		Mockito.when(taskRepository.findAll(spec, pageable)).thenReturn(tasksPage);
		
		taskService.findAll(spec, pageable);
	}
}
