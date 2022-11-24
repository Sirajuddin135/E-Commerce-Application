package com.app.exception;

import java.time.LocalDateTime;

public class MyErrorDetail {
	private LocalDateTime timeStamp;
	private String message;
	private String description;
	
	public MyErrorDetail() {}
	
	public MyErrorDetail(LocalDateTime timeStamp, String message, String description) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.description = description;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MyErrorDetail [timeStamp=" + timeStamp + ", message=" + message + ", description=" + description + "]";
	}
	
}
