package com.sample1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sample1.Repository.MessageRepository;
import com.sample1.model.MessageModel;

@Service
public class GlobalService {

	@Autowired
	private final MessageRepository messagerepository;

	public GlobalService(MessageRepository messagerepository) {
		super();
		this.messagerepository = messagerepository;
		
	}
	public int GetMaxid() {
		return messagerepository.maxid();
	}
	public String GetnewMessage(int currentId,int maxid) {
		List<MessageModel> list=messagerepository.consoleResult(currentId,maxid);
		Gson json=new Gson();
		return json.toJson(list);
	}
	public void InsertMessage(String name,String message,String mobilenumber) {
		MessageModel messagedetails=new MessageModel(name,message,mobilenumber);
		messagerepository.save(messagedetails);
	}
}
