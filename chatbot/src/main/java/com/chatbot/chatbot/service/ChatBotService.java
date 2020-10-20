package com.chatbot.chatbot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatbot.chatbot.model.BotCommunication;
import com.chatbot.chatbot.model.UserSpeak;
import com.chatbot.chatbot.repository.UserSpeakRepository;
import com.chatbot.chatbot.utils.ResponseBuilder;

@Service
public class ChatBotService {
	
	@Autowired
	UserSpeakRepository userSpeakRepository;
	
	@Autowired
	BotCommunicationService botCommunicationService;
	
	@Autowired
	TarotService tarotService;
	
	
	public ResponseEntity<Object> createDialogueFlow(Map<String, Object> payload) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		
		if(Stream.of("user_conversation", "bot_conversation", "choice").
				allMatch(payload::containsKey) == false) {
			return responseBuilder.badRequest(null);
		}
		
		String userConversation = ((String)payload.get("user_conversation")).trim();
		String botConversation = (String) payload.get("bot_conversation");
		String choiceSet = (String) payload.get("choice");
		
		Optional<UserSpeak> addedUserSpeak = Optional.empty();
		
		UserSpeak userSpeak = new UserSpeak(userConversation);
		BotCommunication botCommunication  = new BotCommunication(botConversation, choiceSet);
		
		addedUserSpeak = Optional.ofNullable(userSpeakRepository.save(userSpeak));
		if(addedUserSpeak.isPresent() == false) {
			return responseBuilder.notAcceptable("유저 명령어 생성에 실패하였습니다." ,false);
		}
		botCommunication.setUserSpeak(addedUserSpeak.get());
		Optional<BotCommunication> savedBotCommunication = botCommunicationService.createBotCommunication(botCommunication);
		if (savedBotCommunication.isPresent() == false) {
			return responseBuilder.notAcceptable("봇의 대화 내용 생성 및 선택지를 만드는 데 실패하였습니다.", false);
		}
		responseBuilder.setStatus(HttpStatus.OK);
		return responseBuilder.build();
		
	}
	
	public ResponseEntity<Object> getConversation(Map<String, Object> payload) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		Map<String, Object> result = new HashMap<>();
		String user_command = "";
		String lover_name = "";
		if(Stream.of("user_command").
				allMatch(payload::containsKey) == false) {
			return responseBuilder.badRequest(null);
		}
		user_command = (String) payload.get("user_command");
		if (payload.containsKey("lover_name")) {
			lover_name = (String) payload.get("lover_name");
		}
		if (user_command.contains("tarot")) {
			tarotService.pickRandomTarot(result);
		} else {
			Optional<BotCommunication> queryBot =  botCommunicationService.findCommunicationByUserCommand(user_command);
			if (queryBot.isPresent() == false) {
				return responseBuilder.notAcceptable("주어진 명령에 대한 봇의 행동이 정해지지 않았습니다.", false);
			}
			botCommunicationService.parse(queryBot.get(), result, lover_name);
		}
		
		responseBuilder.setData(result);
		return responseBuilder.build();
		
		
	}

	

		
		
		
		


	
}
