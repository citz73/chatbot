package com.chatbot.chatbot.service;

import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatbot.chatbot.model.BotCommunication;
import com.chatbot.chatbot.repository.BotCommunicationRepository;

@Service
public class BotCommunicationService {
	
	@Autowired
	BotCommunicationRepository botCommunicationRepository;
	
	
	public Optional<BotCommunication> createBotCommunication(BotCommunication botCommunication) {		
		Optional<BotCommunication> newBotCommunication = Optional.empty();
		newBotCommunication = Optional.ofNullable(botCommunicationRepository.save(botCommunication));
		return newBotCommunication;
	} 
	
	public Optional<BotCommunication> findCommunicationByUserCommand(String user_command) {
		Optional<BotCommunication> botResponse = Optional.ofNullable(botCommunicationRepository.findBotCommunicationByCommand(user_command));
		if (botResponse.isPresent() == false) {
			return Optional.empty();
		}
		return botResponse;
	}
	
	public void parse(BotCommunication botCommunication, Map<String, Object> botConversation, String lover_name) {
		String conversation = botCommunication.getConversation();
		String choice = botCommunication.getChoice();
		
		if (conversation.contains("__")) {
			conversation = conversation.replaceAll("__", lover_name);
		}
		
		String[] conversationList = conversation.split("//");
		String[] choiceList = choice.split("//");
		
		
		botConversation.put("conversation", conversationList);
		botConversation.put("choiceList", choiceList);
	}

	
	
}
