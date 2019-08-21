package com.organizer.organizerapp.repository;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.organizer.organizerapp.entity.Task;

public class CustomizedTaskRepositoryImpl implements CustomizedTaskRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void merge(Map<String, Object> update, int taskId) {

		Task task = entityManager.find(Task.class, taskId);

		Field[] fieldsOfTask = Task.class.getDeclaredFields();

		for (Field field : fieldsOfTask) {

			String fieldName = field.getName();

			if (update.get(fieldName) != null) {

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
