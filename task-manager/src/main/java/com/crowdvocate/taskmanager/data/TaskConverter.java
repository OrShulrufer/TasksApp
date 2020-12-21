package com.crowdvocate.taskmanager.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.crowdvocate.taskmanager.Boundary.TaskBoundary;

@Component
public class TaskConverter {
	
	public TaskBoundary fromEntity(TaskEntity entity) {
		TaskBoundary rv = new TaskBoundary();
		rv.setName(entity.getName());
		rv.setDescription(entity.getDescription());
		rv.setStatus(StatusEnum.toString(entity.getStatus()));
		rv.set$key(entity.getId());
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(entity.getStartDate());  
		rv.setStartDate(strDate);
		String endDate = dateFormat.format(entity.getEndDate());  
		rv.setEndDate(endDate);
		return rv;
	}

	public TaskEntity toEntity(TaskBoundary boundary) throws ParseException {
		TaskEntity rv = new TaskEntity();
		rv.setName(boundary.getName());
		rv.setDescription(boundary.getDescription());
		rv.setStatus(StatusEnum.fromString(boundary.getStatus()));
		rv.setId(boundary.get$key());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        Date strDate = dateFormat.parse(boundary.getStartDate());  
		rv.setStartDate(strDate);
	    Date endDate = dateFormat.parse(boundary.getEndDate());  
		rv.setEndDate(endDate);
		return rv;
	}

}
