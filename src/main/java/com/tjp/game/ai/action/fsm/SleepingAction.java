package com.tjp.game.ai.action.fsm;

import java.util.Random;

import com.tjp.game.ai.config.Contants;
import com.tjp.game.ai.engine.GameEngine;
import com.tjp.game.ai.inter.fsm.FSMStateInter;
import com.tjp.game.ai.message.fsm.MessageQueue;
import com.tjp.game.ai.profile.fsm.UserProfile;

public class SleepingAction implements FSMStateInter {
	
	public static final String ACTION_STR=" sleeping action ";
	
	private UserProfile userProfile;
	private Random random;
	
	public SleepingAction() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	public SleepingAction(UserProfile userProfile)
	{
		this.userProfile=userProfile;
		init();
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}



	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
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
		
		userProfile.incBlood(20*random.nextFloat());
		userProfile.decHungry(20*random.nextFloat());
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
		
		if(userProfile.getBlood()>=Contants.USER_VALUE_MAX)
		{
			return userProfile.getHungry()>userProfile.getEnergy() ? userProfile.getActionWithAdd(FightingAction.ACTION_STR) : 
																	 userProfile.getActionWithAdd(EatingAction.ACTION_STR) ;
		}else
		{
			if(userProfile.getHungry()<=10.0f)
			{
				return userProfile.getActionWithAdd(EatingAction.ACTION_STR);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return ACTION_STR;
	}

}
