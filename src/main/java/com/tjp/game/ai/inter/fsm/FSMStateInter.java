package com.tjp.game.ai.inter.fsm;

import com.tjp.game.ai.inter.GameObjectInter;

public interface FSMStateInter extends GameObjectInter {
	
	public void init();
	public FSMStateInter getFSMState();

}
