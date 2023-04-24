package com.sample1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sample1.Repository.ChatRepository;
import com.sample1.Repository.PrivateChatRepository;
import com.sample1.Repository.RegistrationRepository;
import com.sample1.model.Chat;
import com.sample1.model.ChatHistory;
import com.sample1.model.RegistrationModel;

@Service
public class PrivateChatService {

	@Autowired
	private final PrivateChatRepository privatechatrepository;

	@Autowired
	private final RegistrationRepository registrationrepository;

	@Autowired
	private final ChatRepository chatrepository;

	public PrivateChatService(PrivateChatRepository privatechatrepository,
			RegistrationRepository registrationrepository, ChatRepository chatrepository) {
		super();
		this.privatechatrepository = privatechatrepository;
		this.registrationrepository = registrationrepository;
		this.chatrepository = chatrepository;
	}

	public int PrivateChatAdd(String senderId, String reciverId) throws NullPointerException {
		RegistrationModel registrationmodel2;
		RegistrationModel registrationmodel1 = new RegistrationModel();
		ChatHistory ch = privatechatrepository.SearchPerson(senderId, reciverId);
		if (ch == null) {
			registrationmodel1.setMobilenumber(null);
		}
		if (ch != null) {
			String receiverphone = ch.getReceiverPhone();
			registrationmodel1.setMobilenumber(receiverphone);
		}
		registrationmodel2 = registrationrepository.UserExistorNot(reciverId);
		if (registrationmodel2 == null) {
			return 0;
		}
		if (registrationmodel1.getMobilenumber() == null && registrationmodel2.getMobilenumber() != null) {
			Chat chat = new Chat(senderId, reciverId);
			chatrepository.save(chat);
			int chatid = chatrepository.GetchatId(senderId, reciverId);
			ChatHistory c1 = new ChatHistory(chatid, senderId, reciverId, "");
			ChatHistory c2 = new ChatHistory(chatid, reciverId, senderId, "");
			privatechatrepository.save(c1);
			privatechatrepository.save(c2);
			return 2;
		} else if (registrationmodel1.getMobilenumber() != null) {
			return 1;
		}
		return 0;
	}

	public int chathistoryMaxid(String senderId) {
		return privatechatrepository.chathistoryMaxid(senderId);
	}

	public String GetHistory(String senderId) {
		List<ChatHistory> list = privatechatrepository.chatHistory(senderId);
		Gson json = new Gson();
		return json.toJson(list);
	}

}
