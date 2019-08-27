package com.organizer.organizerapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="task")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description_task")
	private String description;
	
	@Column(name="is_completed")
	private boolean isCompleted = false;
	
	@Column(name="date_task")
	private LocalDateTime localDateTime;
	
	@Transient
	@Autowired
	private Weather weather;

	public Task() {
	}
	
	public Task(String title, String description, boolean isCompleted, LocalDateTime localDateTime, Weather weather) {
		this.title = title;
		this.description = description;
		this.isCompleted = isCompleted;
		this.localDateTime = localDateTime;
		this.weather = weather;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", isCompleted=" + isCompleted
				+ ", localDateTime=" + localDateTime + ", weather=" + weather + "]";
	}
}
