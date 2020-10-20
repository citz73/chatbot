package com.chatbot.chatbot.service;

import java.util.List;
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
		Optional<List<Integer>> id_list = Optional.ofNullable(tarotRepository.findTarotIdList());
		if (id_list.isPresent() == false) {
			return false;
		}
		int size = id_list.get().size();
		int index = rand.nextInt(size);
		Optional<Tarot> pickedTarot = tarotRepository.findById(id_list.get().get(index));
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
