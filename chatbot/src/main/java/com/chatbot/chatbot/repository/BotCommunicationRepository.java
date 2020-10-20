package com.chatbot.chatbot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chatbot.chatbot.model.BotCommunication;

public interface BotCommunicationRepository extends CrudRepository<BotCommunication, Integer>{
	
	@Query(value = "SELECT bot.* FROM bot_communication AS bot "
	+ "JOIN user_speak ON bot.user_speak_id = user_speak.id "
	+ "WHERE user_speak.command = :command", nativeQuery = true)
	BotCommunication findBotCommunicationByCommand(
			@Param("command") final String command);
	
	

}
