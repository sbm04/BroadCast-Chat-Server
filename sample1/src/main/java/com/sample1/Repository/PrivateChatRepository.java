package com.sample1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sample1.model.ChatHistory;
import com.sample1.model.RegistrationModel;

public interface PrivateChatRepository extends JpaRepository<ChatHistory, Integer> {

	@Query("select u from ChatHistory u where u.senderPhone=:senderId and u.receiverPhone=:reciverId")
	public ChatHistory SearchPerson(@Param("senderId") String senderId,@Param("reciverId") String reciverId);
	
	@Query("select count(u) as maxid from ChatHistory u where senderPhone=:senderId")
	public int chathistoryMaxid(@Param("senderId") String senderId);
	
	
	  @Query("select  u from ChatHistory u  where u.senderPhone=:senderId ")
	  public List<ChatHistory> chatHistory(@Param("senderId") String senderId);
	  
	  @Transactional
	  @Modifying
	  @Query("update ChatHistory set lastMessage=:lastmsg where chat_id=:chatid")
	  public void UpdateLastMessage(@Param("lastmsg")String lastmsg,@Param("chatid") int chatid);
	 
}
