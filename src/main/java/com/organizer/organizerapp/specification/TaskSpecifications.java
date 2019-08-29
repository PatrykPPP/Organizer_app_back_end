package com.organizer.organizerapp.specification;

import org.springframework.data.jpa.domain.Specification;

import com.organizer.organizerapp.entity.Task;

public class TaskSpecifications{

	public static Specification<Task> isCompleted(Boolean isCompleted){
		return (root, query, cb) -> {
			if (isCompleted == null) {
				return cb.isTrue(cb.literal(true));
			}
			return cb.equal(root.get("isCompleted"), isCompleted);
		};
	}
	
	public static Specification<Task> likeTitle(String title){
		return (root, query, cb) -> {
			if (title == null) {
				return cb.isTrue(cb.literal(true));
			}
			return cb.like(root.get("title"), "%" + title + "%");
		};
	}

}
