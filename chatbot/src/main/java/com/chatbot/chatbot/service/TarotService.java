package com.chatbot.chatbot.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatbot.chatbot.model.Tarot;
import com.chatbot.chatbot.repository.TarotRepository;

@Service
public class TarotService {
	
	@Autowired
	TarotRepository tarotRepository;
	
	public boolean  pickRandomTarot(Map<String, Object> tarotResponse) {
		Random rand = new Random();
		int rand_int = rand.nextInt(3) + 1;
		Optional<Tarot> pickedTarot = tarotRepository.findById(rand_int);
		if (pickedTarot.isPresent() == false) {
			return false;
		}
		String name = pickedTarot.get().getName();
		String conversation = pickedTarot.get().getConversation();
		String choice = pickedTarot.get().getChoice();
		
		tarotResponse.put("name", name);
		tarotResponse.put("conversation", parseString(conversation));
		tarotResponse.put("choice", parseString(choice));
		return true;
		
	}
	
	public String[] parseString(String conversation) {
		String[] conversationList = conversation.split("//");
		return conversationList;
	}
	
	

}
