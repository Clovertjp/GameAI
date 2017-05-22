package com.tjp.game.ai.message.fsm;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.inter.GameObjectInter;
import com.tjp.game.ai.profile.fsm.Message;

public class MessageQueue implements GameObjectInter {
	
	public static MessageQueue messageQueue=new MessageQueue();
	public QueueCompare compare=new QueueCompare();
	
	public static final Message dieMessage=new Message(MessageType.DIE, Integer.MAX_VALUE);
	
	public static MessageQueue getInstance()
	{
		return messageQueue;
	}
	
	public static class MessageType
	{
		public static final int DIE=0;
	}
	
	private Map<Long, Queue<Message>> queue=new ConcurrentHashMap<Long, Queue<Message>>();

	public void onEnter() {
		// TODO Auto-generated method stub

	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		runQueue();
	}

	public void onExit() {
		// TODO Auto-generated method stub

	}
	
	private void runQueue()
	{
		long frame=GameEngine.getInstance().getFrame()-1;
		if(!queue.containsKey(frame))
		{
			return;
		}
		Queue<Message> msq=queue.get(frame);
		while(!msq.isEmpty())
		{
			Message ms=msq.poll();
			System.out.println("remove "+frame+"  "+ms);
			switch(ms.getMessageType())
			{
			case MessageType.DIE:
				GameEngine.getInstance().gameEnd();
				return;
			}
		}
	}
	
	public void addMessage(Message messgae)
	{
		long frame=GameEngine.getInstance().getFrame();
		if(!queue.containsKey(frame))
		{
			Queue<Message> mesQue=new PriorityQueue<Message>(compare);
			queue.put(frame, mesQue);
		}
		System.out.println("add "+frame+"  "+messgae);
		queue.get(frame).add(messgae);
	}

}
