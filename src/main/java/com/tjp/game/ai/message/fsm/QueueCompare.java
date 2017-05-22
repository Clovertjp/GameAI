package com.tjp.game.ai.message.fsm;

import java.util.Comparator;

import com.tjp.game.ai.profile.fsm.Message;

public class QueueCompare implements Comparator<Message> {

	public int compare(Message o1, Message o2) {
		// TODO Auto-generated method stub
		return o1.getPriority()-o2.getPriority();
	}

}
