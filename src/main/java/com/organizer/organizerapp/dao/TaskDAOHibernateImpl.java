package com.organizer.organizerapp.dao;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.organizer.organizerapp.entity.Task;

@Repository
public class TaskDAOHibernateImpl implements TaskDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Task> getAll() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Task> query = session.createQuery("from Task", Task.class);
		
		List<Task> tasks = query.getResultList();

		return tasks;
	}

	@Override
	public Task getById(int id) {

		Session session = entityManager.unwrap(Session.class);
		
		Task task = session.get(Task.class, id);
		
		return task;
	}
	
	@Override
	public void save(Task task) {

		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(task);
	}

	@Override
	public void deleteById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Task> query = session.createQuery("delete from Task where id=:taskId");
		
		query.setParameter("taskId", id);
		
		query.executeUpdate();
	}

	@Override
	public List<Task> getByCompleted(boolean isCompleted) {

		Session session = entityManager.unwrap(Session.class);
		
		Query<Task> query = session.createQuery("from Task where isCompleted=:status", Task.class);
		
		query.setParameter("status", isCompleted);
		
		List<Task> tasks = query.getResultList();
		
		return tasks;
	}

	@Override
	public void merge(Map<String, Object> update, int taskId) {

		Task task = this.getById(taskId);
		
		Field[] fieldsOfTask = Task.class.getDeclaredFields();
		
		for(Field field: fieldsOfTask) {
			
			String fieldName = field.getName();
			
			if( update.get(fieldName) != null) {
				
				field.setAccessible(true);
				
				try {
					
					field.set(task, update.get(fieldName));
					
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				}
				
			}
		}
		
	}

}
