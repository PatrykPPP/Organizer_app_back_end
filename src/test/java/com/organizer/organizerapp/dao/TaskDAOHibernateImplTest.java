package com.organizer.organizerapp.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.organizer.organizerapp.entity.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.organizer.organizerapp.dao")
@Transactional
public class TaskDAOHibernateImplTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	@Qualifier("taskDAOHibernateImpl")
	private TaskDAO taskDAO;

	@Test
	public void whenGetById_thenReturnTask() {
		
		Task task = new Task();
		task.setTitle("task");
		
		entityManager.persist(task);
		entityManager.flush();

		Task taskFound = taskDAO.getById(task.getId());

		assertThat(task).isEqualTo(taskFound);
	}
	
	@Test
	public void whenGetAll_thenReturnTasks() {
		
		Task task1 = new Task();
		Task task2 = new Task();
		
		task1.setTitle("task1");
		task2.setTitle("task2");
		
		entityManager.persist(task1);
		entityManager.persist(task2);
		entityManager.flush();
		
		List<Task> tasks = new ArrayList<>();
					tasks.add(task1);
					tasks.add(task2);
		
		List<Task> tasksFound = taskDAO.getAll();
		
		assertThat(tasks).isEqualTo(tasksFound);
	}
	
	@Test
	public void whenSave_thenThereIsTaskInDatabase() {
		
		Task taskSaved = new Task();
		
		taskSaved.setTitle("taskSaved");
		
		taskDAO.save(taskSaved);
		
		Task taskFound = entityManager.find(Task.class, taskSaved.getId());
		
		assertThat(taskFound).isEqualTo(taskSaved);
	}
	
	/*
	@Test
	public void whenDeleteById_thenThereIsNoTaskInDatabase() {
	}
	*/
	
	@Test
	public void whenGetByCompletedWithTrue_thenGetCompletedTasks() {
		
		Task task = new Task();
			task.setCompleted(true);
			
		entityManager.persist(task);
		entityManager.flush();
					
		List<Task> tasksCompleted = new ArrayList<>();
					tasksCompleted.add(task);
		
		List<Task> tasksCompletedFound = taskDAO.getByCompleted(true);
		
		assertThat(tasksCompletedFound).isEqualTo(tasksCompleted);
	}
	
	
	@Test
	public void whenMerge_thenUpdateTaskInDatabase() {
		
		Map<String, Object> inputParameters = new HashMap<>();
							inputParameters.put("title", "Send a postcard");
							inputParameters.put("description", "Lorem ipsum");
		
		Task savedTask = new Task();						
		entityManager.persist(savedTask);
		entityManager.flush();
							
		Task comparedTask = new Task();
		comparedTask.setId(savedTask.getId());
		comparedTask.setTitle((String) inputParameters.get("title"));
		comparedTask.setDescription((String) inputParameters.get("description"));
		
		
		taskDAO.merge(inputParameters, savedTask.getId());
		
		Task foundTask = entityManager.find(Task.class, savedTask.getId());
		
		assertThat(foundTask).isEqualToComparingFieldByFieldRecursively(comparedTask);
		
	}
	
	
}
