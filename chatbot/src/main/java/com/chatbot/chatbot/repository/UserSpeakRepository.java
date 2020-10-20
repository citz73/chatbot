package com.chatbot.chatbot.repository;

import org.springframework.data.repository.CrudRepository;

import com.chatbot.chatbot.model.UserSpeak;

public interface UserSpeakRepository extends CrudRepository<UserSpeak, Integer> {
	
}
