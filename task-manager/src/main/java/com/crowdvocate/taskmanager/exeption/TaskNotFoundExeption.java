package com.crowdvocate.taskmanager.exeption;

public class TaskNotFoundExeption extends RuntimeException{
	private static final long serialVersionUID = 0;
	
	public TaskNotFoundExeption() {
		super();
	}

	public TaskNotFoundExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskNotFoundExeption(String message) {
		super(message);
	}

	public TaskNotFoundExeption(Throwable cause) {
		super(cause);
	} 

}
