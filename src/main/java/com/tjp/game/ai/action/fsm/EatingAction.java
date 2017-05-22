package com.tjp.game.ai.action.fsm;

import java.util.Random;

import com.tjp.game.ai.config.Contants;
import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.inter.fsm.FSMStateInter;
import com.tjp.game.ai.message.fsm.MessageQueue;
import com.tjp.game.ai.profile.fsm.Message;
import com.tjp.game.ai.profile.fsm.UserProfile;

public class EatingAction implements FSMStateInter {
	
	public static final String ACTION_STR=" eating action ";
	
	private UserProfile userProfile;
	private Random random;
	
	public EatingAction(UserProfile userProfile)
	{
		this.userProfile=userProfile;
		init();
	}

	public void onEnter() {
		// TODO Auto-generated method stub
		System.out.println(ACTION_STR+"  onEnter");
	}

	public void onUpdate() {
		// TODO Auto-generated method stub
		
		if(userProfile.isDie())
		{
			MessageQueue.getInstance().addMessage(MessageQueue.dieMessage);
			return ;
		}
		
		userProfile.incHungry(20*random.nextFloat());
		userProfile.decEnergy(10*random.nextFloat());
		System.out.println(userProfile);

		
		FSMStateInter action=getFSMState();
		if(action!=null)
		{
			this.onExit();
			userProfile.setAction(action);
			action.onEnter();
		}
	}

	public void onExit() {
		// TODO Auto-generated method stub
		System.out.println(ACTION_STR+"  onExit");
	}

	public void init() {
		// TODO Auto-generated method stub
		random=new Random();
	}

	public FSMStateInter getFSMState() {
		// TODO Auto-generated method stub
		
		if(userProfile.getHungry()>=Contants.USER_VALUE_MAX)
		{
			return userProfile.getBlood()>userProfile.getEnergy() ? new FightingAction(userProfile) : 
																	new SleepingAction(userProfile) ;
		}else
		{
			if(userProfile.getEnergy()<=10.0f)
			{
				return new FightingAction(userProfile);
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return ACTION_STR;
	}
	
	

}
