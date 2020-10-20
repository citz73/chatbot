package com.chatbot.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@TestMethodOrder(value = OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TallkManageControllerIntegrationTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@Order(1)
	public void createDialogueFlowTest() {
		Map<String, Object> request = new HashMap<>();
		request.put("user_conversation", "썸 연애운");
		request.put("bot_conversation", "내꺼인 듯 내꺼 아닌 내꺼 같은 그 분// "
				+ "과연 사귀게 될 건지//사귀면 언제 사귈 건지//filename/.../tarot03.jpg//타로로 점 쳐 볼까?");
		request.put("choice", "응 볼래//아니 나중에");
		
		ResponseEntity<Object> response = testRestTemplate.postForEntity("/dialogue/create", request, Object.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	
	
	@Test
	@Order(2)
	@Sql("/initial.sql")
	public void getConversationTest() throws JsonMappingException, JsonProcessingException  {
		Map<String, Object> request = new HashMap<>();
		
		request.put("user_command", "썸 연애운");
		
		ResponseEntity<Object> response = testRestTemplate.postForEntity("/dialogue/conversation", request, Object.class);
		
		String choice = ((Map<String, Object>) response.getBody()).get("choiceList").toString();
		String conversation = ((Map<String, Object>) response.getBody()).get("conversation").toString();
		String[] choiceList = choice.replaceAll("[\\[\\]]", "").split(",");
		String[] conversationList = conversation.replaceAll("[\\[\\]]", "").split(",");
		
		assertEquals("응 볼래",choiceList[0]);
		assertEquals("내꺼인 듯 내꺼 아닌 내꺼 같은 그 분", conversationList[0]);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

}
