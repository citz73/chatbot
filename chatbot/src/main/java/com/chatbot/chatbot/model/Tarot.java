package com.chatbot.chatbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tarot")
public class Tarot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "conversation")
	private String conversation;
	
	@Column(name = "choice")
	private String choice;
	
	public Tarot() {}
	
	public Tarot(String name, String onversation, String choice) {
		this.name = name;
		this.conversation = conversation;
		this.choice = choice;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getConversation() {
		return conversation;
	}

	public String getChoice() {
		return choice;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setConversation(String conversation) {
		this.conversation = conversation;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	
	

}
