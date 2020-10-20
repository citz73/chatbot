package com.chatbot.chatbot.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bot_communication")

public class BotCommunication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "conversation")
	private String conversation;
	
	@Column(name = "choice")
	private String choice;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_speak_id", referencedColumnName = "id")
	private UserSpeak userSpeak;

		
	public BotCommunication() {}
	
	public BotCommunication(String conversation, String choice) {
		this.conversation = conversation;
		this.choice = choice;
	}
	

	public String getChoice() {
		return choice;
	}


	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConversation() {
		return conversation;
	}

	public UserSpeak getUserSpeak() {
		return userSpeak;
	}

	public void setConversation(String conversation) {
		this.conversation = conversation;
	}

	public void setUserSpeak(UserSpeak userSpeak) {
		this.userSpeak = userSpeak;
	}
	
	
	
	
	
}
