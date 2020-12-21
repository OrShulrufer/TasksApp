package com.crowdvocate.taskmanager.taskService;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;


import com.crowdvocate.taskmanager.Boundary.TaskBoundary;

public interface TaskService {
	
	public Set<TaskBoundary> getAllTasks();
	
	public TaskBoundary getTaskById(Long id);
	
	public TaskBoundary createTask(Map<String, String> task_ditales) throws ParseException;
	
	public void updateTask(Map<String, String> task_ditales, Long id) throws ParseException;

	public void deleteTask(Long id);

	public Set<TaskBoundary> getAllTasksPage(int page, int size);

}
