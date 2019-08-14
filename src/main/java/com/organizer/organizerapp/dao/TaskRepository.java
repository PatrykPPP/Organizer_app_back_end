package com.organizer.organizerapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizer.organizerapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
