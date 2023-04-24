package com.sample1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample1.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	@Query("select u.chat_id from Chat u where u.senderPhone=:senderId and u.receiverPhone=:reciverId")
	public int GetchatId(@Param("senderId") String senderId,@Param("reciverId") String reciverId);
}
