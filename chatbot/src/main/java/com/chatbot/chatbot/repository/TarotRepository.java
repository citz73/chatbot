package com.chatbot.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chatbot.chatbot.model.Tarot;

public interface TarotRepository extends CrudRepository<Tarot, Integer> {
	@Query(value = "SELECT id from Tarot", nativeQuery = true)
	List<Integer> findTarotIdList(); 
}
