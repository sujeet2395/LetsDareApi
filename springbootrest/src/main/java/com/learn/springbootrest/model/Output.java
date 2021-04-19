package com.learn.springbootrest.model;

public class Output {
	private int success;
	private String message;
	private int id;
	public Output() {
		super();
	}
	
	public Output(int success, String message, int id) {
		super();
		this.success = success;
		this.message = message;
		this.id = id;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Output [success=" + success + ", message=" + message + ", id=" + id + "]";
	}
}
