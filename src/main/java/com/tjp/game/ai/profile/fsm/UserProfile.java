package com.tjp.game.ai.profile.fsm;

import com.tjp.game.ai.inter.FSMStateInter;
import com.tjp.game.ai.inter.GameObjectInter;

public class UserProfile implements GameObjectInter {
	
	private String name;
	
	private float blood;
	private float energy;
	private float hungry;
	
	private FSMStateInter action;
	
	public UserProfile(String name)
	{
		this.name=name;
		blood=100.0f;
		energy=100.0f;
		hungry=100.0f;
	}
	

	public void onEnter() {
		// TODO Auto-generated method stub

	}

	public void onUpdate() {
		// TODO Auto-generated method stub

	}

	public void onExit() {
		// TODO Auto-generated method stub

	}

}
