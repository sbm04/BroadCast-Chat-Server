package com.sample1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat_history")
public class ChatHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int chat_id;
	private String senderPhone;
	private String receiverPhone;
	private String lastMessage;
	public ChatHistory() {
		super();
	}
	public ChatHistory(int chat_id, String senderPhone, String receiverPhone, String lastMessage) {
		super();
		this.chat_id = chat_id;
		this.senderPhone = senderPhone;
		this.receiverPhone = receiverPhone;
		this.lastMessage = lastMessage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getLastMessage() {
		return lastMessage;
	}
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}
	

}
