package com.tjp.game.ai.main;

import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.message.fsm.MessageQueue;
import com.tjp.game.ai.profile.fsm.UserProfile;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameEngine.getInstance().addChild(MessageQueue.getInstance());
		GameEngine.getInstance().gameStart();
		GameEngine.getInstance().addChild(new UserProfile(" a man "));
		GameEngine.getInstance().onUpdate();
	}

}
