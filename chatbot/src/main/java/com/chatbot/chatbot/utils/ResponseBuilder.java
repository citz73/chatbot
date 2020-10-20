package com.chatbot.chatbot.utils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ResponseBuilder {
	private Map<String,	Object> body = new LinkedHashMap<>();
	private Map<String,	Object> jsonBody = new LinkedHashMap<>();
	private HttpStatus status = HttpStatus.OK;
	
	public ResponseBuilder() {}
	
	
	public ResponseBuilder setData(Object data) {
		if (data != null) {
			body.put("data", data);
		}
		return this;
	}
	
	public ResponseBuilder setData(Map<String, Object> map) {
		body = map;
		return this;
	}
	
	public ResponseBuilder setDefaultStatus() {
		body.put("status", status.value());
		return this;
	}
	
	public ResponseBuilder setStatus(HttpStatus status) {
		this.status = status;
		body.put("status", status.value());
		return this;
	}
	
	public ResponseBuilder setMessage(String message) {
		body.put("message", message);
		return this;
	}

	
	public ResponseEntity<Object> build() {
		if (body.containsKey("status") == false) {
			body.put("status", status.value());
		}
		
		if (this.jsonBody.isEmpty() == false) {
			body.put("data", jsonBody);
		}
		return new ResponseEntity<>(body, status);
	}
	
	public ResponseEntity<Object> ok() {
		return this.setDefaultStatus().build();
	}
	
	public ResponseEntity<Object> badRequest(String message) {
		boolean hasMessage = true;
		if (message == null) {
			hasMessage = false;
		}
		message = hasMessage ? message : "please provide all required fields"; 
		return this.setStatus(HttpStatus.BAD_REQUEST)
				.setMessage(message)
				.build();
	}
	
	public ResponseEntity<Object> notAcceptable(String message, boolean includeTime) {
		message = message != null ? message : "request not accepted..."; 
		return this.setStatus(HttpStatus.NOT_ACCEPTABLE)
				.setMessage(message)
				.build();
	}
	
}

