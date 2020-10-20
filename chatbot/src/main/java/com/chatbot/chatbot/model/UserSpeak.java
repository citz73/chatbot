package com.chatbot.chatbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_speak")
public class UserSpeak {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "command", unique = true)
	private String command;
	
	public UserSpeak() {}
	
	public UserSpeak(String sentence) {
		this.command = sentence;
	}

	public String getSentence() {
		return command;
	}
	
	public void setSentence(String sentence) {
		this.command = sentence;
	}
	
	
	
	
	
	

}
