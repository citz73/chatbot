package com.chatbot.chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; 
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.chatbot.chatbot.controller.TalkManageController;
import com.chatbot.chatbot.model.BotCommunication;
import com.chatbot.chatbot.service.BotCommunicationService;
import com.chatbot.chatbot.service.ChatBotService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Matchers.*;

@WebMvcTest(TalkManageController.class)
public class TalkManagerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ChatBotService chatBotService;
	
	@MockBean
	private BotCommunicationService service;
	
@Test
public void getConversationTest() throws Exception {
	Map<String, Object> user_speak = new HashMap<>();
	String user_speak_test = "응 볼래";
	user_speak.put("user_command", "응 볼래");
	String conversation = "좋아. 우선…//하트코행성 여행자 너랑 썸인지 뭔지를 타고 있는 그 분을 내가 뭐라고 부를까?";
	String choice = "그 사람을 __ 라고 불러줘";
	
	BotCommunication botCommunicationTest = new BotCommunication(conversation,choice);
	Optional<BotCommunication> botCommunicationContainer = Optional.ofNullable(botCommunicationTest);
	
	
	Mockito.when(service.findCommunicationByUserCommand(user_speak_test)).thenReturn(botCommunicationContainer);
	
	mvc.perform(MockMvcRequestBuilders.post("/dialogue/conversation")
			.characterEncoding("utf-8")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(user_speak)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.conversation").value("좋아. 우선…//하트코행성 여행자 너랑 썸인지 뭔지를 타고 있는 그 분을 내가 뭐라고 부를까?"));
			
	
}

	
}
