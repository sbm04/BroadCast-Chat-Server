package com.sample1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sample1.Repository.PrivateChatReadRepository;
import com.sample1.Repository.PrivateChatRepository;
import com.sample1.model.ChatMessage;

@Service
public class ReadPrivateChatService {

	@Autowired
	private final PrivateChatReadRepository repository;
	@Autowired
	private final PrivateChatRepository privaterepository;
	
	public ReadPrivateChatService(PrivateChatReadRepository repository, PrivateChatRepository privaterepository) {
		super();
		this.repository = repository;
		this.privaterepository = privaterepository;
	}
	public void InsertMessage(String sender,String message,int chatid) {
		ChatMessage messagemodel=new ChatMessage(sender,message,chatid);
		repository.save(messagemodel);
		privaterepository.UpdateLastMessage(message, chatid);
	}
	public String ReadMessage(int chatid,int currentid) {
		int maxid=repository.privatechatMaxid(chatid);
		if(maxid>currentid) {
			List<ChatMessage> list=repository.ReadMessage(chatid);
			List<ChatMessage> list1=new ArrayList<ChatMessage>();
			for(int i=currentid;i<list.size();i++) {
				list1.add(list.get(i));
			}
			Gson json=new Gson();
			return json.toJson(list1);
		}
		return "";
	}
}
