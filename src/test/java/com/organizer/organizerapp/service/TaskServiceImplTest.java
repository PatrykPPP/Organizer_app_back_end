package com.organizer.organizerapp.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
		
		@Bean
		public Task task() {
			return new Task();
		}
		
		@Bean
		public Weather weather() {
			return new Weather();
		}
	}
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private Task task;
	
	@Autowired
	private Weather weather;

	@MockBean
	private TaskRepository taskRepository;
	
	@MockBean
	@Qualifier("weatherRepositoryImpl")
	private WeatherRepository weatherRepository;
	
	
	@Before
	public void setUp() {
		Mockito.when(taskRepository.findById(0)).thenReturn(Optional.of(task));
		Mockito.when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		
		Mockito.when(weatherRepository.getByDateAndTime(task.getLocalDateTime())).thenReturn(Optional.of(weather));
	}
	
	@Test
	public void whenFindById0_thenReturnTask() {

		Task taskTest = taskService.findById(0);
		
		assertThat(taskTest).isEqualTo(task);
	}
	
	@Test(expected = TaskNotFoundException.class)
	public void whenFindById1_thenThrowTaskNotFoundException() {
		
		taskService.findById(1);
	}
}
