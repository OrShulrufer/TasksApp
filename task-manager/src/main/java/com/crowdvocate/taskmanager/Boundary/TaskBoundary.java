package com.crowdvocate.taskmanager.Boundary;


import java.util.Date;


public class TaskBoundary {
	
    private Long $key;
    private String name;
    private String description;
	private String status;
    private String startDate;
    private String endDate;

    public TaskBoundary() {

    }

    public Long get$key() {
        return this.$key;
    }
    
	public void set$key(Long id) {
		this.$key = id;		
	}


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getStartDate() {
        return this.startDate;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getEndDate() {
        return this.endDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
 		return description;
 	}

 	public void setDescription(String description) {
 		this.description = description;
 	}

}

