package com.crowdvocate.taskmanager.taskService;


import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crowdvocate.taskmanager.Boundary.TaskBoundary;
import com.crowdvocate.taskmanager.data.StatusEnum;
import com.crowdvocate.taskmanager.data.TaskConverter;
import com.crowdvocate.taskmanager.data.TaskEntity;
import com.crowdvocate.taskmanager.exeption.TaskNotFoundExeption;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;



@Service
public class TaskServiceImp implements  TaskService{

	private TaskCrud taskCrud;
	private TaskConverter taskConverter;
		
	@Autowired
	public TaskServiceImp(TaskCrud taskCrud, TaskConverter taskConverter) {		
		this.taskCrud = taskCrud;
		this.taskConverter = taskConverter;
	}
		
	
		
//	public Set<TaskBoundary> getAllTasks() {
//		return this.taskCrud
//				.findAll(PageRequest.of(0, 10, Direction.ASC, "id"))
//				.getContent()
//				.stream()
//				.map(this.taskConverter::fromEntity)
//				.collect(Collectors.toSet());
//
//	}
	public Set<TaskBoundary> getAllTasks() {
		Set<TaskEntity> b = this.taskCrud.findAll();
		return b.stream()
				.map(this.taskConverter::fromEntity)
				.collect(Collectors.toSet());

	}



	public TaskBoundary getTaskById(Long id) {
		Optional<TaskEntity> existing = this.taskCrud.findById(id);
		if (!existing.isPresent()) 
			throw new TaskNotFoundExeption("Could not find task by id: " + id);
		TaskEntity taskEntity = existing.get();
		return taskConverter.fromEntity(taskEntity);
	}


    //@Transactional(readOnly = true)
	public TaskBoundary createTask(Map<String, String> task_ditales) throws ParseException {
		TaskEntity taskEntity = new TaskEntity();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		
		taskEntity.setName(task_ditales.get("name"));
		taskEntity.setDescription(task_ditales.get("description"));
		
		StatusEnum e = StatusEnum.fromString(task_ditales.get("status"));
		taskEntity.setStatus(e);
		
		taskEntity.setStartDate(formatter.parse(task_ditales.get("startDate")));    	
    	
		taskEntity.setEndDate(formatter.parse(task_ditales.get("endDate")));
		
		return this.taskConverter.fromEntity(
				this.taskCrud.save(taskEntity));
	}


	@Override
	public void updateTask(Map<String, String> task_ditales, Long id) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd"); 
		
		Optional<TaskEntity> existing = this.taskCrud.findById(id);
		if (!existing.isPresent()) 
			throw new TaskNotFoundExeption("Could not find task by id: " + id);
		TaskEntity taskEntity = existing.get();
		
		taskEntity.setName(task_ditales.get("name"));
		taskEntity.setDescription(task_ditales.get("description"));
		
		System.out.println(task_ditales);
		StatusEnum e = StatusEnum.fromString(task_ditales.get("status"));
		taskEntity.setStatus(e);
		
		taskEntity.setStartDate(formatter.parse(task_ditales.get("startDate")));
		taskEntity.setEndDate(formatter.parse(task_ditales.get("endDate")));
		
		this.taskCrud.save(taskEntity);
	}


	@Override
	public void deleteTask(Long id) {
		this.taskCrud.deleteById(id);		
	}



	@Override
	public Set<TaskBoundary> getAllTasksPage(int page, int size) {
		System.out.println("hear");
		System.out.println(page+"hear"+size);
		Set<TaskBoundary> boundaries = this.taskCrud
				.findAll(PageRequest.of(page, size, Direction.ASC, "id"))
				.getContent()
				.stream()
				.map(this.taskConverter::fromEntity)
				.collect(Collectors.toSet());
		System.out.println(boundaries);
		return boundaries;

	}


	
	
//	    private ResultSetExtractor<List<String>> handleResultSet() {
//	        return rs -> {
//	            List<String> response = new ArrayList<>();
//	            while (rs.next()) {
//	                response.add(rs.getString("name"));
//	            }
//	            return response;
//	        };
//	    }

	}

