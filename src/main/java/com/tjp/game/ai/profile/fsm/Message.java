package com.tjp.game.ai.profile.fsm;

import com.tjp.game.ai.inter.fsm.MessageInter;
import com.tjp.game.ai.message.fsm.MessageQueue.MessageType;

public class Message {
	private int messageType;
	private int priority=1;
	private MessageInter messageParam;
	
	public Message(int messageType) {
		// TODO Auto-generated constructor stub
		this.messageType=messageType;
	}
	
	public Message(int messageType,int priority)
	{
		this(messageType);
		this.priority=priority;
	}
	
	public Message(int messageType,int priority,MessageInter messageParam)
	{
		this(messageType,priority);
		this.messageParam=messageParam;
	}
	
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public MessageInter getMessageParam() {
		return messageParam;
	}
	public void setMessageParam(MessageInter messageParam) {
		this.messageParam = messageParam;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "message : messageType:"+messageType+"   priority:"+priority+"  messageParam:"+messageParam;
	}
}
