package com.chatbot.chatbot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.chatbot.service.ChatBotService;

@RestController
@RequestMapping(path = "/dialogue")

public class TalkManageController {
	final private String json = "application/json";
	
	@Autowired
	private ChatBotService service;
	
	@PostMapping(path = "/create", consumes = json, produces = json)
	public ResponseEntity<Object> create (
			@RequestBody Map<String, Object> payload) {
		return service.createDialogueFlow(payload);
	}
	
	@PostMapping(path = "/conversation", produces = json) 
	public ResponseEntity<Object> conversation(
			@RequestBody Map<String, Object> payload) {
		return service.getConversation(payload);
	}
}
