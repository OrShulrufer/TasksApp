package com.crowdvocate.taskmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crowdvocate.taskmanager.Boundary.TaskBoundary;
import com.crowdvocate.taskmanager.taskService.TaskService;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final JdbcTemplate jdbcTemplate;
    private TaskService taskService;

    @Autowired
    public TaskController(JdbcTemplate jdbcTemplate, TaskService taskService) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskService = taskService;
    }

    @CrossOrigin
    @GetMapping("/task")
    public Set<TaskBoundary> getAllTasks(
            @RequestParam (name = "page", required = false, defaultValue = "0") int page,
            @RequestParam (name = "pageSize", required = false, defaultValue = "10") int pageSize
    		) {
    	System.out.println(page);
    	System.out.println(pageSize);
        log.info("Get all tasks");
        if(page!=-0)
        	return this.taskService.getAllTasks();
        else {
        	
        	 return this.taskService.getAllTasksPage(page, pageSize);
		}
    }
    
    
    @CrossOrigin
    @GetMapping("/task/{id}")
    public TaskBoundary getTaskById(
    		@PathVariable("id") Long id) {
    	return  this.taskService.getTaskById(id);
    }

    @CrossOrigin
    @PostMapping("/task")
    public TaskBoundary createTask(
    		@RequestBody Map<String, String> task_ditales) throws ParseException { 
    	log.info("Create task");
    	System.out.println(task_ditales.get("startDate"));

       return  this.taskService.createTask(task_ditales);
    }
    
    @CrossOrigin
    @PutMapping("/task/{id}")
    public void updateTask(
    		@PathVariable("id") Long id,
    		@RequestBody Map<String, String> task_ditales) throws ParseException { 
    	log.info("Create task");
        this.taskService.updateTask(task_ditales, id);
    }
    
    @CrossOrigin
    @DeleteMapping("/task/{id}")
    public void deleteTask(
    		@PathVariable("id") Long id) {
    	this.taskService.deleteTask(id);
    }
    
    
        
    @ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleException (ParseException e){
		return Collections.singletonMap("error", 
				(e.getMessage() == null)?
						"Enum is incorect":
						e.getMessage());
	}
    

    
}
