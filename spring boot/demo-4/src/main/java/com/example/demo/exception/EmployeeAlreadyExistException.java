package com.example.demo.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
	
	public EmployeeAlreadyExistException(String msg) {
		super(msg);
	}

}
