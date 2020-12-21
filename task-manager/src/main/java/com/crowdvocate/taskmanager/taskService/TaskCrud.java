package com.crowdvocate.taskmanager.taskService;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.crowdvocate.taskmanager.data.TaskEntity;

public interface TaskCrud extends PagingAndSortingRepository<TaskEntity, Long>{
	
	public Set<TaskEntity> findAll();
	
//	public Task findById(@Param("id") Task id);
	
	
}